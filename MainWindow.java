import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Janela de interacao com o usuario
 *
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 03/10/2019
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
        JFrame f = new JFrame("Music Player");
        f.setSize(250, 250);
        f.setLocation(300,200);
        final JTextArea textArea = new JTextArea(10, 40);
        f.getContentPane().add(BorderLayout.CENTER, textArea);
        final JButton button = new JButton("Play");
        f.getContentPane().add(BorderLayout.SOUTH, button);
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
    
        f.setVisible(true);
    }
}
