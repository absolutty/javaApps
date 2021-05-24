package sk.uniza.fri.destinacie.hrad;

import sk.uniza.fri.DobrodruznaHra;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

/**
 * 23. 3. 2021 - 16:46
 * Trieda Hadanka je používana v destinácií Hrad, vytvára hádanku s jej odpoveďou (všetky reprezentácie odpovede).
 */
public class Hadanka {
    private final String znenieHadanky;
    private final String[] mozneOdpovede;

    public Hadanka(String znenieHadanky, String[] mozneOdpovede) {
        this.znenieHadanky = znenieHadanky;
        this.mozneOdpovede = mozneOdpovede;
    }

    public boolean spytajSaHadanku() {
        JTextField xField = new JTextField(15);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel(this.znenieHadanky + "?: "));
        myPanel.add(xField, BorderLayout.CENTER);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer

        JOptionPane.showConfirmDialog(DobrodruznaHra.dajInstanciu().getFrame(), myPanel, "Zadaj odpoved: ", JOptionPane.DEFAULT_OPTION);
        String hracovaOdpoved = xField.getText();

        return (this.spravnaOdpoved(hracovaOdpoved));
    }

    private boolean spravnaOdpoved(String odpoved) {
        for (String str : this.mozneOdpovede) {
            if (str.equalsIgnoreCase(odpoved)) {
                return true;
            }
        }
        return false;
    }

    public String getSpravnuOdpoved() {
        return this.mozneOdpovede[0];
    }
}
