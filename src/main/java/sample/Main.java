package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Demo combining JavaFX and MIDI: an image moves on the screen based on MIDI keyboard playing.
 * <p/>
 * The main class starts up a {@link MidiController}, that configures the MIDI system and starts the JavaFX application.
 * The GUI is configured by FXML and screen movement based on MIDI messages is implemented in {@link FxController}.
 *
 * @author Manfred Dohmen <manfred.dohmen@gmail.com>
 */
public class Main extends Application {

    MidiController midiController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        midiController = new MidiController();
        URL resource = getClass().getResource("/fxml/sample.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("MIDI + JavaFX demo");
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        midiController.shutdown();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
