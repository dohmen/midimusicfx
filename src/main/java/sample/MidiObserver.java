package sample;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * This observer listens to MIDI messages and fires the contained note value as an NoteEvent onto a Guava event bus.
 *
 * @author Manfred Dohmen <manfred.dohmen@gmail.com>
 */
public class MidiObserver implements Receiver {
    @Override
    public void send(MidiMessage message, long timeStamp) {
        byte[] b = message.getMessage();

        System.out.println( "Which note: " + b[ 1 ] );
        System.out.println( "Note pressure: " + b[ 2 ] );

        EventBus.INSTANCE.getEventBus().post(new NoteEvent(b[1]));
    }

    @Override
    public void close() {

    }
}
