package sk.uniza.fri.destinacie;

import sk.uniza.fri.DobrodruznaHra;

import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * 26. 2. 2021 - 19:42
 * @author adaha
 *
 * Abstraktná trieda Destinácia je určená pre vytvorenie základu destinácie, ktorý budú jednotlivé lokácie extendovať.
 * Pri nej je uskutočnený polymorfizmus.
 */

public abstract class Destinacia {
    private final char key;
    private final String nazov;
    private final Color farba;

    private final PopisDestinacie popis;

    private static final Color DEFAULT_FARBA_DESTINACIE = new Color(224, 224, 224); //farba, ktorou sa vypisuju policka kt. NEOBSAHUJU ziadnu destinaciu

    public Destinacia(String nazov, Color farba) {
        this.key = this.getClass().getSimpleName().charAt(0);
        this.nazov = nazov;
        this.farba = farba;
        this.popis = new PopisDestinacie(this);
    }

    public char getKey() {
        return this.key;
    }

    public String getNazov() {
        return this.nazov;
    }

    public Color getFarba() {
        return this.farba;
    }

    public static Color getDefaultFarbaDestinacie() {
        return DEFAULT_FARBA_DESTINACIE;
    }

    public void navstiv() {
        JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Vitaj v destinácií s názvom " + this.nazov);
    }

    public String getPopis() {
        return this.popis.getPopis();
    }

    public int vyberMoznost(String[] zoznamMoznosti) {
        int vyber = JOptionPane.showOptionDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Vyber si: ", this.getNazov(),
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, zoznamMoznosti, zoznamMoznosti[0]);

        return vyber + 1;
    }
}
