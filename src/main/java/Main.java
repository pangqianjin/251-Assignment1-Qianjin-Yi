import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("notepad.fxml"));
        Parent root = loader.load();
        Notepad notepad = loader.getController();
        primaryStage.getIcons().add(new Image("notepad.png"));
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setTitle("notepad");
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                // delete all the temporary html files generated
                File[] folder = new File("./").listFiles();
                for (File file : folder) {
                    if (file.getName().endsWith(".html")) {
                        file.delete();
                    }
                }

                // ask to exit if the current page has not been saved
                if (!notepad.saved && !Utils.ifToExit("Your file has not been saved")) {
                    // if decide to save it
                    event.consume();
                } else {// already saved or just don't want to save
                    // set the exit status to true
                    try {
                        Notepad.class.getDeclaredField("exit").setBoolean(notepad,true);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }


}
