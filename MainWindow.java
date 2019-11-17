import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Janela de interacao com o usuario
 *
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 21/11/2019
 */
public class MainWindow
{
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
        createInputTextArea(frame);
        createPlayButton(frame);
        createStopButton(frame);
        
        frame.setVisible(true);
    }
    
    protected static JFrame createFrame()
    {
        JFrame f = new JFrame("Music Player");
        f.setSize(450, 400);
        f.setResizable(false);
        f.setLocation(300,200);
        f.setLayout(new FlowLayout());
        return f;
    }
    
    protected static JTextArea createTipsTextArea(JFrame parent)
    {
        final JTextArea area = new JTextArea();
        area.setText
        (
            "          TEXTO                    RESULTADO\n" +
            "(Letra A maiúscula)                Nota Lá\n"   +
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
        final JButton button = new JButton("Ler mais");
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
                        MusicPlayer.playNote("C");
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
    
    protected static JButton createStopButton(JFrame parent)
    {
        final JButton button = new JButton("Stop");
        parent.add(button);
        button.addActionListener
        (
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    
                }
            }
        );
        
        return button;
    }
}
