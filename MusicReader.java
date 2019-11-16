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
    private static Map<String,String> map = Map.of
    (
        "a","A"
    );
    
    private static char vowels[] = { 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U' };
    
    /**
     * 
     */
    public static List<String> readMusicFromText(String text)
    {
        List<String> music = new ArrayList<String>();
        String lastCommand = null;
        
        for (int i = 0; i < text.length(); i++)
        {
            String command = getCommand(text.substring(i,text.length()-1));
            if (command != null)
            {
                music.add(command);
                lastCommand = command;
            }
            else if (isVowel(text.charAt(i)) && lastCommand != null)
            {
                if (isNoteCommand(lastCommand))
                {
                    music.add(lastCommand);
                }
            }
        }
        
        return music;
    }
    
    private static String getCommand(String inString)
    {
        if (map.containsKey(inString))
        {
            return map.get(inString);
        }
        else if (inString.length() > 1)
        {
            return getCommand(inString.substring(0,inString.length()-2));
        }
        else
        {
            return null;
        }
    }
    
    private static boolean isVowel(char c)
    {
        return false;
    }
    
    private static boolean isNoteCommand(String c)
    {
        return c == "A" || c == "B" || c == "C" || c == "D" || c == "E" || c == "F" || c == "G";
    }
}
