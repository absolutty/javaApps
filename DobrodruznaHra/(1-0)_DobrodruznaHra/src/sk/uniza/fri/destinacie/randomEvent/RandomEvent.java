package sk.uniza.fri.destinacie.randomEvent;

import sk.uniza.fri.Cislo;
import sk.uniza.fri.DobrodruznaHra;
import sk.uniza.fri.destinacie.Destinacia;

import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Destinácia RandomEvent hráčovi môže uškodiť alebo mu môže pomôcť
 * - je zložená s niekoľkých náhodných udalostí (tie majú rôzne pravdepodobnosti), kt. môžu nastať
 * - lepší prípad: pripočítaju mu HP + pripočítajú mu skóre
 * - horší prípad: odpočítajú mu HP + odpočítajú mu skóre
 */
public class RandomEvent extends Destinacia {
    private static final String NAZOV = "Random event";
    private static final Color FARBA = Color.ORANGE;

    public RandomEvent() {
        super(RandomEvent.NAZOV, FARBA);
    }

    private static final NahodnaUdalost[] NAHODNE_UDALOSTI = {new NahodnaUdalost(-25, -50), new NahodnaUdalost(+50, 100), new NahodnaUdalost(-75, -75),  new NahodnaUdalost(+12, -5), new NahodnaUdalost(-10, +10)};
    @Override
    public void navstiv() {
        super.navstiv();

        int randomCislo = Cislo.generujRandom(1, 100);

        DobrodruznaHra h = DobrodruznaHra.dajInstanciu();
        if (randomCislo <= 30) { //30% šanca na nastanie náhodnej udalosti
            JOptionPane.showMessageDialog(h.getFrame(), RandomEvent.NAHODNE_UDALOSTI[0].vykonajUdalost());
        } else if (randomCislo <= 35) { //5% šanca na nastanie náhodnej udalosti
            JOptionPane.showMessageDialog(h.getFrame(), RandomEvent.NAHODNE_UDALOSTI[1].vykonajUdalost());
        } else if (randomCislo <= 45) { //10% šanca na nastanie náhodnej udalosti
            JOptionPane.showMessageDialog(h.getFrame(), RandomEvent.NAHODNE_UDALOSTI[2].vykonajUdalost());
        } else if (randomCislo <= 95) { //50% šanca na nastanie náhodnej udalosti
            JOptionPane.showMessageDialog(h.getFrame(), RandomEvent.NAHODNE_UDALOSTI[3].vykonajUdalost());
        } else if (randomCislo <= 100) { //5% šanca na nastanie náhodnej udalosti
            JOptionPane.showMessageDialog(h.getFrame(), RandomEvent.NAHODNE_UDALOSTI[4].vykonajUdalost());
        }

        if (h.getHPHraca() <= 0) { //hráč to nezvládol, je mrtvy
            h.ukonciHru();
        }
    }

    /**
     * udalosť, ktorá môže nastať pri navštívení danej tejto destinácie
     */
    private static class NahodnaUdalost {
        private final int upravujuceHP; //o koľko sa zmení hráčové HP
        private final int upravujuceSkore; //o koľko sa zmení hráčové skóre

        NahodnaUdalost(int upravujuceHP, int skore) {
            this.upravujuceHP = upravujuceHP;
            this.upravujuceSkore = skore;
        }

        public String vykonajUdalost() {
            DobrodruznaHra hra = DobrodruznaHra.dajInstanciu();

            hra.upravHPHraca(this.upravujuceHP);
            hra.upravSkore(this.upravujuceSkore);

            return String.format("HP upravujem o %d a skore upravujem o %d", this.upravujuceHP, this.upravujuceSkore);

        }

        public int getUpravujuceHP() {
            return this.upravujuceHP;
        }

        public int getUpravujuceSkore() {
            return this.upravujuceSkore;
        }
    }
}
