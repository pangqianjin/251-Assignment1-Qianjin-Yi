import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import org.assertj.core.util.Files;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.nio.charset.StandardCharsets;


@ExtendWith(ApplicationExtension.class)
class NotepadTest {

    final String TEXT_SAVE = "this is a test for save.";
    final String TEXT_OPEN = "this is a test for open.";
    final String TEXT_SEARCH = "this is a test for search.";

    @BeforeAll
    public static void setUpClass() throws Exception {
        ApplicationTest.launch(Main.class);

    }

    @Start
    private void start(Stage stage) {
    }

    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    void should_contain_open_file_text(FxRobot robot) {
        // when
        robot.clickOn("#File").clickOn("#Open");

        // the testfx not support for FileChooser
        // so should by hand to open src/test/test_open_content.txt
        robot.sleep(20000);

        // then
        org.testfx.assertions.api.Assertions.assertThat(robot.lookup("#textArea").queryAs(TextArea.class)).hasText(TEXT_OPEN);
    }


    @Test
    void should_contain_save_file_text(FxRobot robot) {
        // before
        robot.lookup("#textArea").queryTextInputControl().setText(TEXT_SAVE);
        // when
        robot.clickOn("#File").clickOn("#Save");

        // the testfx not support for FileChooser
        // so should by hand to save src/test/test_open_content.txt
        robot.sleep(20000);
        String s = Files.contentOf(new File("src/test/test_save_content.txt"), StandardCharsets.UTF_8);
        Files.delete(new File("src/test/test_save_content.txt"));
        Assertions.assertEquals(s, TEXT_SAVE);
    }

    @Test
    void should_contain_same_search_key_numbers(FxRobot robot) {
        // when
        robot.lookup("#textArea").queryTextInputControl().setText(TEXT_SEARCH);
        robot.push(new KeyCodeCombination(KeyCode.F, KeyCombination.CONTROL_DOWN));
        robot.lookup("#SearchArea").queryTextInputControl().setText("is");
        robot.clickOn("#next");

        // then
        int size = Notepad.keysCoordinates.size();
        Assertions.assertEquals(2, size);

    }
}

