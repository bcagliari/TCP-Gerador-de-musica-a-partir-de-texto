import java.util.Arrays;
import java.util.List;
import javax.sound.midi.*;

/**
 * Tocador de musicas.
 * 
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 03/10/2019
 */
public class MusicPlayer
{
    private static final List<String> notes = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
    private static int channel = 0;  // instrumento MIDI atual
    private static int volume = 30;     // volume atual - entre 0 e 127
    private static int octave = 3;      // oitava atual
    private static int delayBetweenNotes = 100;
    private static int currentInstrument = 0;
    
    private static MidiChannel[] midiChannels;
    private static Synthesizer synth;
    private static Instrument[] instruments;
    
    /**
     * Reproduz uma música definida por uma lista
     * ordenada de comandos.
     */
    public static void play(List<String> commands) throws InterruptedException
    {
        if (midiChannels == null)
        {
            try
            {
                synth = MidiSystem.getSynthesizer();
                synth.open();
                midiChannels = synth.getChannels();
                instruments = synth.getDefaultSoundbank().getInstruments();
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }
        }
        
        for(int cont = 0; cont < commands.size(); cont++)
        {
            try
            {
                Thread.sleep(executeCommand(commands.get(cont)));
            }
            catch(Exception expt)
            {
                throw new RuntimeException(expt);
            }
        }
    }
    
    
    // Retorna o tempo de espera
    private static int executeCommand(String command) throws InterruptedException
    {        
        // Nota
        if (isNoteCommand(command))
        {
            try
            {
               playNote(command);
               return delayBetweenNotes;
            }
            catch(Exception expt)
            {
                throw new RuntimeException(expt);
            }
        }
        
        // Multiplicar volume
        if (command.contains("Multiply volume"))
        {
            setVolume(volume * Integer.valueOf(command.substring(16)));
        }
        
        // Alterar o instrumento
        if (command.contains("Change instrument"))
        {
            setInstrument(Integer.valueOf(command.substring(18)));
        }
        else if(command.contains("Shift instrument"))
        {
            setInstrument(currentInstrument + command.charAt(17));
        }
        
        // Alterar oitava
        if (command.contains("Increase octave"))
        {
            setOctave(octave + command.charAt(16));
        }
        
        // Pausa
        if (command == "Silence")
        {
            return delayBetweenNotes;
        }
        
        return 0;
    }
    
    /**
     * Reproduz uma nota musical
     */
    private static int playNote(String note) throws InterruptedException
    {   
        // * comeca a tocar
        if (midiChannels[channel] != null)
        {
            int noteIndex = idMIDI(note);
            
            midiChannels[channel].noteOn(noteIndex, volume);
            // * espera
            Thread.sleep(100);
            // * para de tocar
            midiChannels[channel].noteOff(noteIndex);
            
            return 100;
        }
        
        return 0;
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
        
        if (volume > 127 || volume < 0)
        {
            volume = 30;
        }
    }
    
    private static void setInstrument(int inInstrument)
    {
        currentInstrument = inInstrument % 128;
        midiChannels[channel].programChange(currentInstrument);
    }
    
    private static void setOctave(int inOctave)
    {
        octave = inOctave%8;
    }
    
    private static boolean isNoteCommand(String caracter)
    {
        return caracter == "A" || caracter == "B" || caracter == "C" || caracter == "D" || caracter == "E" || caracter == "F" || caracter == "G";
    }
}
