import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.odftoolkit.odfdom.doc.OdfDocument;
import org.odftoolkit.odfdom.dom.element.office.OfficeTextElement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Utils {

    // decide if to exit if the current page has not been saved
    public static boolean ifToExit(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you want to leave?",
                new ButtonType("cancel", ButtonBar.ButtonData.NO),
                new ButtonType("confirm", ButtonBar.ButtonData.YES)
        );
        alert.setTitle("important info");
        alert.setHeaderText(message);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.filter(type -> !type.getButtonData().equals(ButtonBar.ButtonData.NO)).isPresent();
    }


    // show a file chooser to choose a file and return it
    public static File chooseAFile(String message, FileOperation operation) {
        FileChooser fileChooser = new FileChooser();
        // choose current directory
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle(message);
        // add filters for extension file name
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Java", "*.java"),
                new FileChooser.ExtensionFilter("Python", "*.py"),
                new FileChooser.ExtensionFilter("Cpp", "*.cpp"),
                new FileChooser.ExtensionFilter("C", "*.c"),
                new FileChooser.ExtensionFilter("TXT", "*.txt"),
                new FileChooser.ExtensionFilter("OpenDocument Text", "*.odt")
        );
        return operation == FileOperation.OPEN ? fileChooser.showOpenDialog(new Stage()) :
                fileChooser.showSaveDialog(new Stage());
    }

    // read odt file and return a String
    public static String getOdtString(File file) throws Exception {
        OdfDocument document = OdfDocument.loadDocument(file);
        OfficeTextElement officeTextElement = (OfficeTextElement) document.getContentRoot();
        return officeTextElement.getTextContent().
                replaceAll("\r", "\r\n");
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
        Path write = Files.write(new File(filename).toPath(), content.getBytes());
        return write.toFile();
    }


    // show the read view to read code
    public static void showReadView(String filename, WebView webView) {
        StackPane root = new StackPane(webView);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle(filename);
        stage.setScene(scene);
        stage.show();
    }

    // return a html structure String
    public static String getRenderedText(String src) {
        final int STATE_TEXT = 1;
        final int STATE_DOUBLE_QUOTE = 2;
        final int STATE_SINGLE_QUOTE = 3;
        final int STATE_MULTI_LINE_COMMENT = 4;
        final int STATE_LINE_COMMENT = 5;
        int lineNumber = 0;

        Set<String> literalSet = new HashSet<>(Configuration.getKeyWordsMap().getLiteralArray());
        Set<String> keywordSet = new HashSet<>(Configuration.getKeyWordsMap().getKeywordArray());
        Set<String> primitiveTypeSet = new HashSet<>(Configuration.getKeyWordsMap().getPrimitiveTypeArray());

        int currentState = STATE_TEXT;
        int identifierLength = 0;
        int currentIndex = -1;
        char currentChar = 0;
        String identifier = "";
        StringBuilder out = new StringBuilder("<link rel=\"stylesheet\" href=\"src/main/resources/keywords.css\">\n");

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
