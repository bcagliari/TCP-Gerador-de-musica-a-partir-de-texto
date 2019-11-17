import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.MidiChannel;

/**
 * Tocador de musicas.
 * 
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 03/10/2019
 */
public class MusicPlayer
{
    private static final List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static MidiChannel[] midiChannels;
    private static int instrument = 0;  // instrumento MIDI atual
    private static int volume = 30;     // volume atual - entre 0 e 127
    private static int octave = 3;      // oitava atual
    
    /**
     * Reproduz uma música definida por uma lista
     * ordenada de comandos.
     */
    public static void play(List<String> commands)
    {
        
    }
    
    public static void stop()
    {
        
    }
    
    /**
     * Reproduz uma nota musical
     */
    public static void playNote(String note) throws InterruptedException
    {
        if (midiChannels == null)
        {
            try
            {
                Synthesizer s = MidiSystem.getSynthesizer();
                s.open();
                midiChannels = s.getChannels();
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        
        // * comeca a tocar
        midiChannels[instrument].noteOn(idMIDI(note), volume);
        // * espera
        Thread.sleep(1);
        // * para de tocar
        midiChannels[instrument].noteOff(idMIDI(note));
    }
    
    /**
     * Encontra o índice MIDI para uma nota: eg. 4C -> 60
     */
    private static int idMIDI(String note)
    {
        return notes.indexOf(note.substring(1)) + 12 * octave + 12; 
    }
}
