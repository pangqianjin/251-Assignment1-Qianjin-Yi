import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Utils {

    // show a file chooser to choose a file and return it
    public static File chooseAFile(String message, FileOperation operation){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(message);
        // add filters for extension file name
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JAVA", "*.java"),
                new FileChooser.ExtensionFilter("PY", "*.py"),
                new FileChooser.ExtensionFilter("CPP", "*.cpp"),
                new FileChooser.ExtensionFilter("C","*.c"),
                new FileChooser.ExtensionFilter("TXT","*.txt"),
                new FileChooser.ExtensionFilter("OpenDocument Text","*.odt")
        );
        return operation==FileOperation.OPEN?fileChooser.showOpenDialog(new Stage()):
                fileChooser.showSaveDialog(new Stage());
    }

    // read odt file and return a String
    public static String getOdtString(File file) throws Exception {
        OdfDocument document = OdfDocument.loadDocument(file);
        OfficeTextElement officeTextElement = (OfficeTextElement) document.getContentRoot();
        return officeTextElement.getTextContent().
                replaceAll("\r","\r\n");
    }

    // read String from a file
    public static String getFileString(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] fileContent = new byte[(int)file.length()];
        in.read(fileContent);
        in.close();
        return new String(fileContent, StandardCharsets.UTF_8);
    }

    // write String into a File named filename
    public static File writeFileString(String filename, String content) throws IOException {
        File file = new File(filename);
        FileWriter fw = new FileWriter(file);
        fw.write(content);
        fw.close();
        return file;
    }

    // show the read view to read code
    public static void showReadView(String filename, WebView webView){
        StackPane root = new StackPane(webView);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(filename);
        stage.setScene(scene);
        stage.show();
    }

    // return a html structure String
    public static String getRenderedText(String src){
        int STATE_TEXT = 1;
        int STATE_DOUBLE_QUOTE = 2;
        int STATE_SINGLE_QUOTE = 3;
        int STATE_MULTI_LINE_COMMENT = 4;
        int STATE_LINE_COMMENT = 5;
        int lineNumber = 0;

        String[] literalArray = { "null", "true", "false", "None", "True",
                "False"};
        String[] keywordArray = { "abstract", "break", "case", "catch", "class",
                "Class", "in", "del", "friend", "inline", "const",
                "const", "continue", "default", "do", "else", "extends", "final",
                "finally", "for", "goto", "if", "implements", "import",
                "instanceof", "interface", "native", "new", "package", "private",
                "protected", "public", "return", "static", "strictfp", "super",
                "switch", "synchronized", "this", "throw", "throws", "transient",
                "try", "volatile", "while" };
        String[] primitiveTypeArray = { "boolean", "char", "byte", "short", "int",
                "long", "float", "double", "void" };

        Set<String> literalSet = new HashSet<String>(Arrays.asList(literalArray));
        Set<String> keywordSet = new HashSet<String>(Arrays.asList(keywordArray));
        Set<String> primitiveTypeSet = new HashSet<String>(Arrays
                .asList(primitiveTypeArray));

        int currentState = STATE_TEXT;
        int identifierLength = 0;
        int currentIndex = -1;
        char currentChar = 0;
        String identifier = "";
        StringBuilder out = new StringBuilder("<link rel=\"stylesheet\" href=\"keywords.css\">\n");

        out.append("<div></div>");
        out.append("<span class=\"lineNumberStyle\">"
                + (++lineNumber) + " </span>");

        while (++currentIndex < src.length() - 1) {
            currentChar = src.charAt(currentIndex);
            if (Character.isJavaIdentifierPart(currentChar)) {
                out.append(currentChar);
                ++identifierLength;
                continue;
            }
            if (identifierLength > 0) {
                identifier = out.substring(out.length() - identifierLength);
                if (currentState == STATE_TEXT) {
                    if (literalSet.contains(identifier)) {
                        out.insert(out.length() - identifierLength,
                                "<span class=\"literalStyle\">");
                        out.append("</span>");
                    } else if (keywordSet.contains(identifier)) {
                        out.insert(out.length() - identifierLength,
                                "<span class=\"keywordStyle\">");
                        out.append("</span>");
                    } else if (primitiveTypeSet.contains(identifier)) {
                        out.insert(out.length() - identifierLength,
                                "<span class=\"primitiveTypeStyle\">");
                        out.append("</span>");
                    } else if (identifier.equals(identifier.toUpperCase())
                            && !Character.isDigit(identifier.charAt(0))) {
                        out.insert(out.length() - identifierLength,
                                "<span class=\"constantStyle\">");
                        out.append("</span>");
                    } else if (Character.isUpperCase(identifier.charAt(0))) {
                        out.insert(out.length() - identifierLength,
                                "<span class=\"nonPrimitiveTypeStyle\">");
                        out.append("</span>");
                    }
                }
            }

            switch (currentChar) {
                case '\"':
                    out.append('\"');
                    if (currentState == STATE_TEXT) {
                        currentState = STATE_DOUBLE_QUOTE;
                        out.insert(out.length() - ("\"").length(),
                                "<span class=\"doubleQuoteStyle\">");
                    } else if (currentState == STATE_DOUBLE_QUOTE) {
                        currentState = STATE_TEXT;
                        out.append("</span>");
                    }
                    break;
                case '\'':
                    out.append("\'");
                    if (currentState == STATE_TEXT) {
                        currentState = STATE_SINGLE_QUOTE;
                        out.insert(out.length() - ("\'").length(),
                                "<span class=\"singleQuoteStyle\">");
                    } else if (currentState == STATE_SINGLE_QUOTE) {
                        currentState = STATE_TEXT;
                        out.append("</span>");
                    }
                    break;
                case '\\':
                    out.append("\\");
                    if (currentState == STATE_DOUBLE_QUOTE
                            || currentState == STATE_SINGLE_QUOTE) {
                        out.append(src.charAt(++currentIndex));
                    }
                    break;
                case '\t':
                    for (int i = 0; i < 4; i++)
                        out.append("&nbsp;&nbsp;&nbsp;&nbsp;");
                    break;
                case '*':
                    out.append('*');
                    if (currentState == STATE_TEXT && currentIndex > 0
                            && src.charAt(currentIndex - 1) == '/') {
                        out.insert(out.length() - ("/*").length(),
                                "<span class=\"multiLineCommentStyle\">");
                        currentState = STATE_MULTI_LINE_COMMENT;
                    }
                    break;
                case '/':
                    out.append("/");
                    if (currentState == STATE_TEXT && currentIndex > 0
                            && src.charAt(currentIndex - 1) == '/') {
                        out.insert(out.length() - ("//").length(),
                                "<span class=\"singleLineCommentStyle\">");
                        currentState = STATE_LINE_COMMENT;
                    } else if (currentState == STATE_MULTI_LINE_COMMENT) {
                        out.append("</span>");
                        currentState = STATE_TEXT;
                    }
                    break;
                case '\r':
                case '\n':
                    if (currentState == STATE_LINE_COMMENT) {
                        out.insert(out.length(), "</span>");
                        currentState = STATE_TEXT;
                    }
                    if (currentChar == '\r' && currentIndex < src.length() - 1) {
                        out.append("\r");
                        ++currentIndex;
                    } else
                        out.append("\r\n");
                    out.append("<div></div>");
                    out.append("<span class=\"lineNumberStyle\">"
                            + (++lineNumber) + " </span>");
                    break;
                case 0:
                    if (currentState == STATE_LINE_COMMENT
                            && currentIndex == (src.length() - 1))
                        out.append("</div>");
                    break;
                default:
                    out.append(currentChar);
            }
            identifierLength = 0;
        }
        return out.toString();
    }
}
