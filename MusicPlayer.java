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
    private static int octave = 6;      // oitava atual
    private static boolean paused = false;
    
    /**
     * Reproduz uma música definida por uma lista
     * ordenada de comandos.
     */
    public static void play(List<String> commands)
    {
        paused = false;
        
        for(int i = 0; i < commands.size(); i++)
        {
            if (!paused)
            {
                try
                {
                    executeCommand(commands.get(i));
                }
                catch(Exception expt)
                {
                    throw new RuntimeException(expt);
                }
            }
            else
            {
                return;
            }
        }
    }
    
    public static void stop()
    {
        paused = true;
    }
    
    private static void executeCommand(String command) throws InterruptedException
    {
        System.out.println(command);
        
        // Nota
        if (isNoteCommand(command))
        {
            try
            {
               playNote(command); 
            }
            catch(Exception expt)
            {
                throw new RuntimeException(expt);
            }
        }
        
        // Multiplicar volume
        if (command.contains("Multiply volume"))
        {
            setVolume(volume * command.charAt(16));
        }
        
        // Alterar o instrumento
        if (command.contains("Change instrument"))
        {
            setInstrument(command.charAt(18));
        }
        else if(command.contains("Shift instrument"))
        {
            setInstrument(instrument + command.charAt(17));
        }
        
        // Alterar oitava
        if (command.contains("Increase octave"))
        {
            setOctave(octave + command.charAt(16));
        }
        
        // Pausa
        if (command == "Silence")
        {
            Thread.sleep(1);
        }
    }
    
    /**
     * Reproduz uma nota musical
     */
    private static void playNote(String note) throws InterruptedException
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
        int noteIndex = idMIDI(note);
        System.out.println(noteIndex);
        midiChannels[instrument].noteOn(noteIndex, volume);
        // * espera
        Thread.sleep(1);
        // * para de tocar
        midiChannels[instrument].noteOff(noteIndex);
    }
    
    /**
     * Encontra o índice MIDI para uma nota: eg. 4C -> 60
     */
    private static int idMIDI(String note)
    {
        return notes.indexOf(note.substring(0)) + 12 * octave + 12; 
    }
    
    private static void setVolume(int inVolume)
    {
        volume = inVolume;
    }
    
    private static void setInstrument(int inInstrument)
    {
        instrument = inInstrument;
    }
    
    private static void setOctave(int inOctave)
    {
        octave = inOctave;
    }
    
    private static boolean isNoteCommand(String c)
    {
        return c == "A" || c == "B" || c == "C" || c == "D" || c == "E" || c == "F" || c == "G";
    }
}
