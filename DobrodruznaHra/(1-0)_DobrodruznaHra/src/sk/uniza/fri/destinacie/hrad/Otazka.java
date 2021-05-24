package sk.uniza.fri.destinacie.hrad;

import sk.uniza.fri.DobrodruznaHra;

import javax.swing.JOptionPane;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Trieda Otazka je používana v destinácií Hrad, vytvára otázku na ktorú je možné odpovedať (áno/nie) + ak hráč neuhádne jej správnu odpoveď.
 */
public class Otazka {
    private final String znenieOtazky;
    private final int jePravda;
    private final String odpovedNaOtazku;

    public Otazka(String znenieOtazky, int jePravda, String odpovedNaOtazku) {
        this.znenieOtazky = znenieOtazky;
        this.jePravda = jePravda;
        this.odpovedNaOtazku = odpovedNaOtazku;
    }

    private static final String[] MOZNOSTI_ODPOVEDE = {"áno", "nie"};

    public boolean spytajSaOtazku() {
        int odpoved = JOptionPane.showOptionDialog(DobrodruznaHra.dajInstanciu().getFrame(),
                this.znenieOtazky,
                this.getClass().getSimpleName(),
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, MOZNOSTI_ODPOVEDE, null);

        odpoved = (odpoved == 1) ? 0 : 1;
        return (odpoved == this.jePravda);
    }

    public String getOdpovedNaOtazku() {
        return this.odpovedNaOtazku;
    }
}
