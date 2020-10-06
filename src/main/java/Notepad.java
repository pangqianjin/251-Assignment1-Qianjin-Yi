/**
 * Sample Skeleton for 'notepad.fxml' Controller Class
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.encoding.WinAnsiEncoding;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Notepad {

    @FXML
    private Menu File;

    @FXML
    private Button next;

    @FXML
    private MenuItem exportItem;

    @FXML
    private MenuItem searchItem;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="New"
    private MenuItem New; // Value injected by FXMLLoader

    @FXML // fx:id="Open"
    private MenuItem Open; // Value injected by FXMLLoader

    @FXML // fx:id="Save"
    private MenuItem Save; // Value injected by FXMLLoader

    @FXML // fx:id="Exit"
    private MenuItem Exit; // Value injected by FXMLLoader

    @FXML // fx:id="ToSearch"
    private Menu ToSearch; // Value injected by FXMLLoader

    @FXML // fx:id="Copy"
    private MenuItem Copy; // Value injected by FXMLLoader

    @FXML // fx:id="Paste"
    private MenuItem Paste; // Value injected by FXMLLoader

    @FXML // fx:id="Cut"
    private MenuItem Cut; // Value injected by FXMLLoader

    @FXML // fx:id="Print"
    private MenuItem Print; // Value injected by FXMLLoader

    @FXML // fx:id="About"
    private MenuItem About; // Value injected by FXMLLoader

    @FXML // fx:id="TimeAndDate"
    private Text TimeAndDate; // Value injected by FXMLLoader

    @FXML // fx:id="SearchHBox"
    private HBox SearchHBox; // Value injected by FXMLLoader

    @FXML // fx:id="SearchArea"
    private TextField SearchArea; // Value injected by FXMLLoader

    @FXML // fx:id="textArea"
    private TextArea textArea; // Value injected by FXMLLoader

    // the time thread stop variable
    public static volatile boolean exit = false;

    // store the keys anchor
    protected static List<int[]> keysCoordinates = new LinkedList<>();

    // the filename for current text area, if not it will be untitled
    private String filename = "untitled";
    // the file saving status
    public boolean saved = false;

    @FXML
    void AboutOnClick(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Team member: Pang Qianjin, Gao Yi\nThe Notepad is in developing",
                new ButtonType("Confirm", ButtonBar.ButtonData.RIGHT));
        alert.setTitle("About");
        alert.setHeaderText("About NotePad");
        alert.showAndWait();
    }

    @FXML
    void CopyOnClick(ActionEvent event) {
        textArea.copy();
    }

    @FXML
    void CutOnClick(ActionEvent event) {
        textArea.cut();
    }

    @FXML
    void syntax(ActionEvent event) throws IOException {
        // new WebView and WebEngine
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        // generate a html file and load it
        File html = Utils.writeFileString((this.filename + ".html"),
                Utils.getRenderedText(textArea.getText()));
        URL url = html.toURI().toURL();
        webEngine.load(url.toString());

        // show the read view to read code
        Utils.showReadView(this.filename, webView);
    }

    @FXML
    void ExitOnClick(ActionEvent event) {
        if (this.saved || Utils.ifToExit("Your file has not been saved")) {
            exit = true;
            Stage stage = (Stage) textArea.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void NewOnClick(ActionEvent event) {
        // to create a new (fresh) window
        Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void OpenOnClick(ActionEvent event) throws Exception {
        // choose a file to open
        File f = Utils.chooseAFile("choose a file to open",
                FileOperation.OPEN);
        this.filename = f != null ? f.getName() : "untitled";
        String content = "";
        if (f != null && f.getName().endsWith(".odt")) {
            content = Utils.getOdtString(f);
            textArea.setText(content);
        } else if (f != null) { // common text file
            content = Utils.getFileString(f);
            textArea.setText(content);
        }
    }

    @FXML
    void PasteOnclick(ActionEvent event) {
        textArea.paste();
    }

    @FXML
    void PrintOnClick(ActionEvent event) throws IOException {
        export2pdf("FileToPrint.pdf");
        printer();
        Files.deleteIfExists(Paths.get("FileToPrint.pdf"));
    }

    @FXML
    void SaveOnClick(ActionEvent event) throws IOException {
        // a save dialog to save
        File f = Utils.chooseAFile("choose a position to save",
                FileOperation.SAVE);
        if (f != null) {
            Utils.writeFileString(f.getAbsolutePath(), textArea.getText());
            this.saved = true;
        }
    }

    // remove invalid type "\n" in PDFBOX
    private static List<String> remove(String text) {
        ArrayList<String> strings = new ArrayList<>();

        StringBuilder b = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (WinAnsiEncoding.INSTANCE.contains(text.charAt(i))) {
                b.append(text.charAt(i));
            } else {
                strings.add(b.toString());
                b = new StringBuilder();
            }
        }
        return strings;
    }

    private void export2pdf(String fileName) {
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
            //Begin the Content stream
            contentStream.beginText();
            //Setting the font to the Content stream
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            //Setting the leading
            contentStream.setLeading(14.5f);
            //Setting the position for the line
            contentStream.newLineAtOffset(25, 750);
            // make sure the text endwiths "\n"
            textArea.appendText("\n");
            String text = textArea.getText();
            List<String> remove = remove(text);
            remove.forEach((str) -> {
                try {
                    contentStream.showText(str);
                    contentStream.newLine();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //Ending the content stream
            contentStream.endText();
            //Closing the content stream
            contentStream.close();
            doc.save(fileName);
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void export2pdf(ActionEvent event) {
        export2pdf("Exported.pdf");
    }

    private static void printer() {
        FileChooser file2Selected = new FileChooser();
        file2Selected.setTitle("File to Print");
        file2Selected.setInitialDirectory(
                new File("./")
        );
        file2Selected.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("pdf", "*.pdf"));
        File file2Print = file2Selected.showOpenDialog(new PopupWindow() {
        });
        if (file2Print != null) {
            // build print request attribute set
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // set the print format, select AUTOSENSE
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // find all available printing services
            PrintService[] printService = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // locate the default print service
            PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
            // show print dialog
            PrintService service = ServiceUI.printDialog(null, 200, 200, printService,
                    defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // create a print job
                    FileInputStream fis = new FileInputStream(file2Print); // construct the file stream to be printed
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    void ToSearch(ActionEvent event) {
        SearchHBox.setVisible(true);
    }

    @FXML
    void closeSearch(ActionEvent event) {
        textArea.deselect();
        keysCoordinates.clear();
        SearchHBox.setVisible(false);
    }

    private void highlight(int[] keyCoordinate) {

        textArea.selectRange(keyCoordinate[0], keyCoordinate[1]);
        textArea.setStyle("-fx-highlight-fill: yellow");

        // cycle the keys coordinates
        Collections.rotate(keysCoordinates, -1);
    }

    private void keys2Coordinate(String key, int keyLen) {
        // filter the line contains key
        List<String> keyLine = textArea.getParagraphs()
                .parallelStream()
                .filter(oneLine -> oneLine.toString().contains(key))
                .map(CharSequence::toString)
                .collect(Collectors.toList());

        int anchor = 0;
        // every line
        for (String str : keyLine) {
            int lineLen = str.length();
            // set select anchor start
            anchor += str.indexOf(key);
            for (; anchor <= lineLen - keyLen; anchor++) {
                // find the substring and check whether contains key
                String substring = str.substring(anchor, anchor + keyLen);
                if (substring.contains(key)) {
                    int[] c = {anchor, anchor + keyLen};
                    if (keysCoordinates.contains(c)) continue;
                    keysCoordinates.add(c);
                }
            }
            // next line needs to relocate the anchor
            anchor = lineLen;
        }
    }

    @FXML
    void searchKeys(ActionEvent event) {
        // avoid duplicate coordinates
        if (keysCoordinates.size() > 0) keysCoordinates.clear();

        String key = SearchArea.getText();
        if (key == null || key.equals("")) return;
        int keyLen = key.length();
        keys2Coordinate(key, keyLen);
        nextKey(new ActionEvent());
    }


    @FXML
    void nextKey(ActionEvent event) {
        if (keysCoordinates.size() > 0) highlight(keysCoordinates.get(0));
        else searchKeys(new ActionEvent());
    }

    private void shortcuts() {
        // save and open shortcuts
        Save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
        Open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
        // search shortcut
        searchItem.setAccelerator(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));

        // cut copy paste shortcuts
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb_X = new KeyCodeCombination(KeyCode.X,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination keyComb_C = new KeyCodeCombination(KeyCode.C,
                    KeyCombination.CONTROL_DOWN);
            final KeyCombination keyComb_V = new KeyCodeCombination(KeyCode.V,
                    KeyCombination.CONTROL_DOWN);

            public void handle(KeyEvent ke) {
                if (keyComb_C.match(ke)) {
                    System.out.println("Key Pressed: " + keyComb_C);
                    textArea.copy();
                } else if (keyComb_V.match(ke)) {
                    System.out.println("Key Pressed: " + keyComb_V);
                    textArea.paste();
                } else if (keyComb_X.match(ke)) {
                    System.out.println("Key Pressed: " + keyComb_X);
                    textArea.cut();
                } else {
                    return;
                }
                ke.consume(); // <-- stops passing the event to next node

            }
        });
    }


    private void updateTime() {
        LocalTime now = LocalTime.now();
        TimeAndDate.setText(String.format("Time %d:%d:%d", now.getHour(), now.getMinute(), now.getSecond()));
    }

    private void showTime() {
        // the clock thread is not stopped till the windows closed
        new Thread(() -> {
            while (!exit) {
                updateTime();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert New != null : "fx:id=\"New\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Open != null : "fx:id=\"Open\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Save != null : "fx:id=\"Save\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Exit != null : "fx:id=\"Exit\" was not injected: check your FXML file 'notepad.fxml'.";
        assert ToSearch != null : "fx:id=\"ToSearch\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Copy != null : "fx:id=\"Copy\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Paste != null : "fx:id=\"Paste\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Cut != null : "fx:id=\"Cut\" was not injected: check your FXML file 'notepad.fxml'.";
        assert Print != null : "fx:id=\"Print\" was not injected: check your FXML file 'notepad.fxml'.";
        assert About != null : "fx:id=\"About\" was not injected: check your FXML file 'notepad.fxml'.";
        assert TimeAndDate != null : "fx:id=\"TimeAndDate\" was not injected: check your FXML file 'notepad.fxml'.";
        assert SearchHBox != null : "fx:id=\"SearchHBox\" was not injected: check your FXML file 'notepad.fxml'.";
        assert SearchArea != null : "fx:id=\"SearchArea\" was not injected: check your FXML file 'notepad.fxml'.";
        assert textArea != null : "fx:id=\"textArea\" was not injected: check your FXML file 'notepad.fxml'.";
        // call shortcuts() and showTime()
        shortcuts();
        showTime();

    }


}
