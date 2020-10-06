/**
 * Sample Skeleton for 'notepad.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Notepad {

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


    @FXML
    void AboutOnClick(ActionEvent event) {

    }

    @FXML
    void CopyOnClick(ActionEvent event) {

    }

    @FXML
    void CutOnClick(ActionEvent event) {

    }

    @FXML
    void ExitOnClick(ActionEvent event) {

    }

    @FXML
    void NewOnClick(ActionEvent event) {

    }

    @FXML
    void OpenOnClick(ActionEvent event) {

    }

    @FXML
    void PasteOnclick(ActionEvent event) {

    }

    @FXML
    void PrintOnClick(ActionEvent event) {

    }

    @FXML
    void SaveOnClick(ActionEvent event) {

    }

    @FXML
    void ToSearch(ActionEvent event) {

    }

    @FXML
    void closeSearch(ActionEvent event) {

    }

    @FXML
    void searchNow(ActionEvent event) {

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

    }
}