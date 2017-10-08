package sample;

import javax.sound.midi.*;

/**
 * The MidiController wires a MIDI input device to the system's default synthesizer and additionally wiretaps the MIDI
 * messages with another transmitter to the {@link MidiObserver}, which is used to notify the application
 * about {@link NoteEvent}s.
 *
 * @author Manfred Dohmen <manfred.dohmen@gmail.com>
 */
public class MidiController {

    private MidiDevice inputDevice = null;
    private MidiDevice synthDevice = null;
    private Transmitter transmitter = null;
    private Transmitter observerTransmitter = null;
    private Synthesizer synthesizer = null;
    private Receiver receiver = null;

    MidiController() {
        System.out.println("initialize MIDI");
        try {
            inputDevice = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[1]);
            synthDevice = MidiSystem.getMidiDevice(MidiSystem.getMidiDeviceInfo()[0]);
        } catch (MidiUnavailableException ex) {
            System.out.println("MIDI not available");
            throw new RuntimeException(ex);
        }

        if (inputDevice == null || synthDevice == null) {
            throw new RuntimeException("MIDI in/out not availble");
        }

        try {
            transmitter = inputDevice.getTransmitter();
            observerTransmitter = inputDevice.getTransmitter();
            System.out.println("Transmitter: " + inputDevice.getDeviceInfo());
            System.out.println(String.format("T/R: %s/%s", inputDevice.getMaxTransmitters(),
                    inputDevice.getMaxReceivers()));

            receiver = synthDevice.getReceiver();
            System.out.println("Receiver: " + synthDevice.getDeviceInfo());
            System.out.println(String.format("T/R: %s/%s", synthDevice.getMaxTransmitters(),
                    synthDevice.getMaxReceivers()));

            transmitter.setReceiver(receiver);
            observerTransmitter.setReceiver(new MidiObserver());

            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            inputDevice.open();
            synthDevice.open();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    void shutdown() {
        System.out.println("Shut down MIDI");
        inputDevice.close();
        synthDevice.close();
        transmitter.close();
        observerTransmitter.close();
        receiver.close();
        synthesizer.close();
    }

}
