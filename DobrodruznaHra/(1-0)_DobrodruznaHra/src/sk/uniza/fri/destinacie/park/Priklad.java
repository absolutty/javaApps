package sk.uniza.fri.destinacie.park;

import sk.uniza.fri.DobrodruznaHra;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Trieda Priklad je používana v destinácií Park, vytvára matematický príklad aj s jeho odpoveďou (výsledkom)
 */
public class Priklad {
    private final String zneniePrikladu;
    private final int vysledok;

    public Priklad(String zneniePrikladu, int vysledok) {
        this.zneniePrikladu = zneniePrikladu;
        this.vysledok = vysledok;
    }

    public boolean zadajPriklad() {
        JTextField xField = new JTextField(15);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel(this.zneniePrikladu + " = "));
        myPanel.add(xField, BorderLayout.CENTER);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        int hracovVysledok = -1;
        boolean prikladUspesneZadany = false;
        do {
            JOptionPane.showConfirmDialog(DobrodruznaHra.dajInstanciu().getFrame(), myPanel, "Zadaj výsledok", JOptionPane.DEFAULT_OPTION);
            try {
                hracovVysledok = Integer.parseInt(xField.getText());
                prikladUspesneZadany = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Nezadal si číslo!");
            }

        } while (!prikladUspesneZadany);

        return (hracovVysledok == this.vysledok);
    }

    @Override
    public String toString() {
        return String.format("%s = %d", this.zneniePrikladu, this.vysledok);
    }
}
