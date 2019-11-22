import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Janela de interacao com o usuario
 *
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 21/11/2019
 */
public class MainWindow
{
    protected static JTextArea inputTextArea;
    
    /**
     * Funcao principal do programa
     */
    public static void main(String[] args)
    {
        createGUI();
    }

    /**
     * Cria a interface grafica do usuario
     */
    protected static void createGUI()
    {
        JFrame frame = createFrame();
        createTipsTextArea(frame);
        createReadMoreButton(frame);
        inputTextArea = createInputTextArea(frame);
        createPlayButton(frame);
        createTXTButton(frame);
        
        frame.setVisible(true);
    }
    
    protected static JFrame createFrame()
    {
        JFrame window = new JFrame("Music Generator");
        window.setSize(450, 375);
        window.setResizable(false);
        window.setLocation(300,200);
        window.setLayout(new FlowLayout());
        return window;
    }
    
    protected static JTextArea createTipsTextArea(JFrame parent)
    {
        final JTextArea area = new JTextArea();
        area.setText
        (
            "          TEXTO                    RESULTADO\n" +
            "(Letra A maiúscula)                Nota Lá\n"   +
            "(Letra B maiúscula)                Nota Sí\n"   +
            "(Letra C maiúscula)                Nota Dó\n"   +
            "(Letra D maiúscula)                Nota Ré\n"   +
            "(Letra E maiúscula)                Nota Mi\n"   +
            "(Letra F maiúscula)                Nota Fá\n"   +
            "(Letra G maiúscula)                Nota Sol"
        );
        area.setEditable(false);
        parent.getContentPane().add(area);
        
        return area;
    }
    
    protected static JButton createReadMoreButton(JFrame parent)
    {
        final JButton button = new JButton("Read More");
        parent.getContentPane().add(button);
        button.addActionListener
        (
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    ReadMoreWindow readMore = new ReadMoreWindow();
                }
            }
        );
        
        return button;
    }
    
    protected static JTextArea createInputTextArea(JFrame parent)
    {
        final JTextArea textArea = new JTextArea(10, 40);
        parent.add(textArea);
        
        return textArea;
    }
    
    protected static JButton createPlayButton(JFrame parent)
    {
        final JButton button = new JButton("Play");
        parent.add(button);
        button.addActionListener
        (
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        onClickedPlay();
                    }
                    catch(Exception expt)
                    {
                        throw new RuntimeException(expt);
                    }
                }
            }
        );
        
        return button;
    }
    
    protected static void onClickedPlay()
    {
        try
        {
            MusicPlayer.play(MusicReader.readMusicFromText(inputTextArea.getText()));
        }
        catch(Exception expt)
        {
            throw new RuntimeException(expt);
        }
    }

    protected static JButton createTXTButton(JFrame parent)
    {
        final JButton button = new JButton("Load TxT");
        parent.add(button);
        button.addActionListener
        (
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        JFileChooser chooser = new JFileChooser();
                       
                        chooser.setDialogTitle("Seleção de arquivos");
                        chooser.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
                        
                        if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION){
                         
                          File file = chooser.getSelectedFile();
                          inputTextArea.read( new FileReader( file.getAbsolutePath() ), null );
                        }
        
                
                    }
                    catch(Exception expt)
                    {
                        throw new RuntimeException(expt);
                    }
                }
            }
        );
        
        return button;
    }
}
