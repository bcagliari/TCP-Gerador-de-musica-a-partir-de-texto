
    import java.util.*;
    import java.lang.String;
    
    /**
     * Write a description of class MusicReader here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */
    public class MusicReader
    {
        static String lastCommand = null;
        
        /**
         * Decode a music from a text.
         */
        public static List<String> readMusicFromText(String text)
        {
            abstract class Command
            {
                public abstract String GetMusicalCommand();
            }
            
            class DirectNote extends Command
            {
                String command;
                
                public DirectNote(String inCommand)
                {
                    command = inCommand;
                }
                
                public String GetMusicalCommand()
                {
                    return command;
                }
            }
            
            class RepeatNote extends Command
            {                
                public String GetMusicalCommand()
                {
                    if (lastCommand != null && isNoteCommand(lastCommand))
                    {
                        return lastCommand;
                    }
                    else
                    {
                        return "Silence";
                    }
                }
            }
            
            class MultiplyVolume extends Command
            {
                private int multiplier;
                
                public MultiplyVolume(int n)
                {
                    multiplier = n;
                }
                
                public String GetMusicalCommand()
                {
                    return "Multiply volume " + multiplier;
                }
            }
            
            class ChangeInstrument extends Command
            {
                private int ID;
                
                public ChangeInstrument(int inID)
                {
                    ID = inID;
                }
                
                public String GetMusicalCommand()
                {
                    return "Change instrument " + ID;
                }
            }
            
            class ShiftInstrument extends Command
            {
                private int amount;
                
                public ShiftInstrument(int inAmount)
                {
                    amount = inAmount;
                }
                
                public String GetMusicalCommand()
                {
                    return "Shift instrument " + amount;
                }
            }
            
            class IncreaseOctave extends Command
            {
                private int increase;
                
                public IncreaseOctave(int amount)
                {
                    increase = amount;
                }
                
                public String GetMusicalCommand()
                {
                    return "Increase octave " + increase;
                }
            }
            
            // Mapeamento de caracteres para comandos
            HashMap<String,Command> map = new HashMap<String,Command>();
            
            // Notas
            map.put("A",new DirectNote("A"));   // Lá
            map.put("B",new DirectNote("B"));   // Sí
            map.put("C",new DirectNote("C"));   // Dó
            map.put("D",new DirectNote("D"));   // Ré
            map.put("E",new DirectNote("E"));   // Mí
            map.put("F",new DirectNote("F"));   // Fá
            map.put("G",new DirectNote("G"));   // Sol
            
            // Repeticao da ultima nota
            map.put("a", new RepeatNote());  // abcdefg minúsculas...
            map.put("b", new RepeatNote());
            map.put("c", new RepeatNote());
            map.put("d", new RepeatNote());
            map.put("e", new RepeatNote());
            map.put("f", new RepeatNote());
            map.put("g", new RepeatNote());
            map.put("H", new RepeatNote()); // Qualquer outra consoante...
            map.put("h", new RepeatNote());
            map.put("J", new RepeatNote());
            map.put("j", new RepeatNote());
            map.put("K", new RepeatNote());
            map.put("k", new RepeatNote());
            map.put("L", new RepeatNote());
            map.put("l", new RepeatNote());
            map.put("M", new RepeatNote());
            map.put("m", new RepeatNote());
            map.put("N", new RepeatNote());
            map.put("n", new RepeatNote());
            map.put("O", new RepeatNote());
            map.put("o", new RepeatNote());
            map.put("P", new RepeatNote());
            map.put("p", new RepeatNote());
            map.put("Q", new RepeatNote());
            map.put("q", new RepeatNote());
            map.put("R", new RepeatNote());
            map.put("r", new RepeatNote());
            map.put("S", new RepeatNote());
            map.put("s", new RepeatNote());
            map.put("T", new RepeatNote());
            map.put("t", new RepeatNote());
            map.put("V", new RepeatNote());
            map.put("v", new RepeatNote());
            map.put("X", new RepeatNote());
            map.put("x", new RepeatNote());
            map.put("Y", new RepeatNote());
            map.put("y", new RepeatNote());
            map.put("Z", new RepeatNote());
            map.put("z", new RepeatNote());
            
            // Multiplicar volume
            map.put(" ", new MultiplyVolume(2));
            
            // Alterar o instrumento
            map.put("!", new ChangeInstrument(114));    // Agogo
            map.put("O", new ChangeInstrument(7));      // Harpsichord...
            map.put("o", new ChangeInstrument(7));
            map.put("I", new ChangeInstrument(7));
            map.put("i", new ChangeInstrument(7));
            map.put("U", new ChangeInstrument(7));
            map.put("u", new ChangeInstrument(7));
            map.put("\n",new ChangeInstrument(15));     // Tubular Bells
            map.put(";", new ChangeInstrument(76));     // Pan Flute
            map.put(",", new ChangeInstrument(20));     // Church Organ
            map.put("0", new ShiftInstrument(0));       // ATUAL + dígito...
            map.put("1", new ShiftInstrument(1));
            map.put("2", new ShiftInstrument(2));
            map.put("3", new ShiftInstrument(3));
            map.put("4", new ShiftInstrument(4));
            map.put("5", new ShiftInstrument(5));
            map.put("6", new ShiftInstrument(6));
            map.put("7", new ShiftInstrument(7));
            map.put("8", new ShiftInstrument(8));
            map.put("9", new ShiftInstrument(9));
            
            // Incrementar oitava
            map.put("?", new IncreaseOctave(1));
            
            // Caso não esteja definido no mapa
            Command defaultCommand = new RepeatNote();
            
            lastCommand = null;
            
            // Lê a música de acordo com as definicoes acima
            List<String> music = new ArrayList<String>();
            for (int i = 0; i < text.length(); i++)
            {
                if (map.containsKey(Character.toString(text.charAt(i))))
                {
                    lastCommand = map.get
                    (
                        Character.toString(text.charAt(i))
                    ).GetMusicalCommand();
                    
                    music.add(lastCommand);
                }
                else
                {
                    music.add(defaultCommand.GetMusicalCommand());
                }
            }
            
            return music;
    }
    
    private static boolean isNoteCommand(String c)
    {
        return c == "A" || c == "B" || c == "C" || c == "D" || c == "E" || c == "F" || c == "G";
    }
}
