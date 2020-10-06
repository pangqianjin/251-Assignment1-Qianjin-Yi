import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Notepad.exit = true;
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("notepad.fxml"));
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        titleConfig(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    protected static void titleConfig(Stage stage) {
        stage.setTitle("notepad");
    }
}
