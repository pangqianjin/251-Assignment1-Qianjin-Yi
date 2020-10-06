import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.IOException;


@ExtendWith(ApplicationExtension.class)
class NotepadTest {
    @BeforeAll
    public static void setUpClass() throws Exception {
        ApplicationTest.launch(Main.class);

    }

    @Start
    private void start(Stage stage) throws IOException {

    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void should_contain_open_file_text(FxRobot robot) {
    }


    @Test
    void should_contain_save_file_text(FxRobot robot) {
    }

    @Test
    void should_contain_same_search_key_numbers(FxRobot robot) {


    }
}

