import java.awt.*;
import javax.swing.*;

/**
 * Janela de mais informacoes sobre como utilizar o software
 *
 * @author Bruna Cagliari, Ludhiane Pereira e Tiago Binz
 * @version 21/11/2019
 */
public class ReadMoreWindow extends JFrame
{
    /**
     * Constructor for objects of class ReadMoreWindow
     */
    public ReadMoreWindow()
    {
        setTitle("Read more");
        setSize(350, 800);
        setResizable(false);
        setLocation(300,200);
        setLayout(new BorderLayout());
        
        // West area
        final JTextArea westArea = new JTextArea();
        westArea.setText
        (
            "                 TEXTO\n"                  +
            "_________________________\n"               +
            "Letras a,b,c,d,e,f,g minúsculas\n\n\n\n"   +
            "_________________________\n"               +
            "Caractere Espaço\n\n\n\n"                  +
            "_________________________\n"               +
            "Caractere ! (ponto de\n"                   +
            "exclamação)\n\n"                           +
            "_________________________\n"               +
            "Qualquer outra letra vogal (O\n"           +
            "ou o, I ou i , U ou u)\n\n"                +
            "_________________________\n"               +
            "Dígito par  ou impar\n\n\n\n\n"            +
            "_________________________\n"               +
            "Caractere ? (ponto de\n"                   +
            "interrogação) e caractere .\n"             +
            "(ponto)\n\n"                               +
            "_________________________\n"               +
            "Caractere NL (nova linha)\n\n\n"           +
            "_________________________\n"               +
            "Caractere ; (ponto e vírgula)\n\n\n"       +
            "_________________________\n"               +
            "Caractere , (vírgula)\n\n\n"               +
            "_________________________\n"               +
            "ELSE (nenhum dos caracteres\n"             +
            "anteriores)"
        );
        westArea.setEditable(false);
        getContentPane().add(BorderLayout.WEST, westArea);
        
        // East area
        final JTextArea eastArea = new JTextArea();
        eastArea.setText
        (
            "              RESULTADO\n"     +
            "_________________________\n"   +
            "Se caractere anterior era\n"   +
            "NOTA (A a G), repete nota;\n"  +
            "Caso contrário, Silêncio\n"    +
            "ou pausa\n"                    +
            "_________________________\n"   +
            "Aumenta volume   para o\n"     +
            "DOBRO do volume; Se não\n"     +
            "puder aumentar, volta ao\n"    +
            "volume default (de início)\n"  +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "#114 (Agogo)\n"                +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "#7 (Harpsichord)\n"            +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "cujo numero  é igual ao\n"     +
            "valor do instrumento\n"        +
            "ATUAL + valor do dígito\n"     +
            "_________________________\n"   +
            "Aumenta UMA oitava;  Se\n"     +
            "não puder aumentar, volta\n"   +
            "à oitava default (de\n"        +
            "início)\n"                     +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "#15 (Tubular Bells)\n"         +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "#76 (Pan Flute)\n"             +
            "_________________________\n"   +
            "Trocar instrumento para o\n"   +
            "instrumento General MIDI\n"    +
            "#20 (Church Organ)\n"          +
            "_________________________\n"   +
            "Se caractere anterior era\n"   +
            "NOTA (A a G), repete nota;\n"  +
            "Caso contrário, Silêncio\n"    +
            "ou pausa"
        );
        eastArea.setEditable(false);
        getContentPane().add(BorderLayout.EAST, eastArea);
        
        setVisible(true);
    }
}
