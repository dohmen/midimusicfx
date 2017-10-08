# Demo combining JavaFX and MIDI

This JavaFX application uses the Java MIDI system to let the user play on a MIDI keyboard. Sound is synthesized via the system's default synthesizer.

The screen shows a character moving sidewards based on the played notes. The main class starts up a MidiController, that configures the MIDI system and it starts the JavaFX application.
 The GUI is configured by FXML and screen movement based on MIDI messages is implemented in FxController.