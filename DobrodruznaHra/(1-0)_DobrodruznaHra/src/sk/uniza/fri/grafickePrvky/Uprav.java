package sk.uniza.fri.grafickePrvky;

import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Táto trieda upravuje Components na predurčené hodnoty.
 * Ak sa rozhodnem napr. zmeniť veľkosť všetkých Políčok, uskutočňujem to tu.
 */
public class Uprav {
    public static void komponent(Component komponent, int sirka, int vyska) {
        if (komponent instanceof JTextField) {
            ((JTextField)komponent).setEditable(false);
            komponent.setFocusable(false);
            ((JTextField)komponent).setHorizontalAlignment(JTextField.CENTER);
            komponent.setPreferredSize(new Dimension(sirka, vyska));
            komponent.setFont(new Font("TimesRoman", Font.BOLD, 14));
        } else if (komponent instanceof Button) {
            komponent.setPreferredSize(new Dimension(sirka, vyska));
            komponent.setFocusable(false);
            komponent.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        }
    }
}
