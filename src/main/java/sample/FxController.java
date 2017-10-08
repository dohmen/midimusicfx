package sample;

import com.google.common.eventbus.Subscribe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Simple controller for the JavaFX application. Listens to {@link NoteEvent}s on the Guava EventBus in order to
 * relocate an image on the application scene.
 *
 * @author Manfred Dohmen <manfred.dohmen@gmail.com>
 */
public class FxController {

    @FXML private Button quitButton;
    @FXML private ImageView character;
    double characterX;

    public FxController() {
        EventBus.INSTANCE.getEventBus().register(this);
    }

    @FXML
    public void quitButtonPressed() throws Exception {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    @Subscribe
    public void event(NoteEvent event) {
        if (characterX == 0)
            characterX = character.getLayoutX();

        System.out.println("event: " + event.noteValue);
        int LOWEST_KEY_OF_MY_KEYBOARD = 48;
        character.setLayoutX(characterX + (event.noteValue - LOWEST_KEY_OF_MY_KEYBOARD) * 20);
    }

}
