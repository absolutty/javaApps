package sk.uniza.fri.destinacie.domcek;

import sk.uniza.fri.Cislo;
import sk.uniza.fri.DobrodruznaHra;
import sk.uniza.fri.destinacie.Destinacia;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 *
 * Destinácia Domček umožňuje hráčovi úkony:
 * - doplniť HP výmenov za jeho skóre (doplňované HP = pocet_skore / 10)
 * - kúpiť si lístok do lotérie s ktorým je možné vyhrať AŽ 1000 skóre
 */
public class Domcek extends Destinacia {
    private static final String NAZOV = "Domček";
    private static final Color FARBA = Color.CYAN;

    public Domcek() {
        super(Domcek.NAZOV, Domcek.FARBA);
    }

    private static final String[] MOZNOSTI = new String[] {"Doplň HP", "Lotéria", "Ukonči prezeranie domčeka"};
    @Override
    public void navstiv() {
        super.navstiv();
        int vyber = super.vyberMoznost(Domcek.MOZNOSTI);

        switch (vyber) {
            case 1 -> this.obchodHP();
            case 2 -> this.loteria();
        }
    }

    /**
     * umožňuje hráčovi zvýšiť počet HP výmenou za jeho skóre
     */
    private void obchodHP() {
        DobrodruznaHra hra = DobrodruznaHra.dajInstanciu();

        JTextField xField = new JTextField(15);
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Za koľko skore chceš zvýšiť HP?:"));
        myPanel.add(xField, BorderLayout.CENTER);
        myPanel.add(Box.createHorizontalStrut(15));

        int zaKolkoSkore = -1;
        JOptionPane.showConfirmDialog(hra.getFrame(), myPanel, "Doplň HP", JOptionPane.DEFAULT_OPTION);
        try {
            zaKolkoSkore = Integer.parseInt(xField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(hra.getFrame(), "Nezadal si číslo!");
            JOptionPane.showConfirmDialog(hra.getFrame(), myPanel, "Doplň HP", JOptionPane.DEFAULT_OPTION);
        }

        if (zaKolkoSkore <= hra.getSkoreHraca()) { //JE mozne upravit HP
            hra.upravHPHraca(zaKolkoSkore / 10);
            hra.upravSkore(zaKolkoSkore * -1);
            JOptionPane.showMessageDialog(hra.getFrame(), "HP úspešne zvýšené");
        } else { //NIE JE mozne upravit HP
            JOptionPane.showMessageDialog(hra.getFrame(), "Nemáš dostatok skóre!");
        }
    }

    /**
     * hráč môže skúsiť svoje šťastie a to tak že si zakúpi lístok do lotérie
     * šanca na výhru je 1:00
     */
    private static final int CENA_LOTERIE = 30;
    private static final int VYHRA_LOTERIE = 1000;
    private void loteria() {
        DobrodruznaHra hra = DobrodruznaHra.dajInstanciu();

        final String[] options = new String[] {"áno", "nie"};
        int vyber = JOptionPane.showOptionDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Želáš si zakúpiť lístok do lotérie?: " , this.getNazov(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        vyber = (vyber == 0) ? 1 : 0;

        if (vyber == 1) { //hráč si CHCE kúpiť lístok do lotérie
            if (Domcek.CENA_LOTERIE <= hra.getSkoreHraca()) { //hrac MA dostatok skore
                hra.upravSkore(Domcek.CENA_LOTERIE * -1);

                int randomHodnota = Cislo.generujRandom(1, 100); //vyhrat loteriu je mozne s pravdepodobnostov 1:100
                if (randomHodnota == 1) { //VYHRAL
                    JOptionPane.showMessageDialog(hra.getFrame(), "Máš šťastný deň! VYHRAL SI :)");
                    hra.upravSkore(Domcek.VYHRA_LOTERIE);
                } else { //PREHRAL
                    JOptionPane.showMessageDialog(hra.getFrame(), "Bohužial si nevyhral :/");
                }
            } else { //hrac NEMA dostatok skore
                JOptionPane.showMessageDialog(hra.getFrame(), "Na zakupenie loterie nemáš dostatok skóre.");
            }
        }
    }
}
