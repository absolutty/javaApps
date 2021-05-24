package sk.uniza.fri;

import sk.uniza.fri.suradnice.Suradnice;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Hráč, každá trieda má iba jedného.
 * Obsahuje základne informácie o hráčovi kt. sú dynamicky menené počas priebehu hry.
 */
public class Hrac {
    private final String nickname;
    private int healthPoints;
    private int skore;

    private Suradnice suradnice;

    public Hrac(String nickname, Suradnice zaciatocneSuradnice) {
        this.nickname = nickname;
        this.healthPoints = 100;
        this.suradnice = zaciatocneSuradnice;
        this.skore = 0;
    }

    @Override
    public String toString() {
        return String.format("[nickname: %s, HP: %d, suradnice{x: %d, y: %d}]",
                this.nickname, this.healthPoints, this.suradnice.getX(), this.suradnice.getY());
    }

    /**
     * x-kova suradnica, na ktorej sa nachadza hrac
     */
    public int nachadzaSaNaX() {
        return this.suradnice.getX();
    }

    /**
     * y-kova suradnica, na ktorej sa nachadza hrac
     */
    public int nachadzaSaNaY() {
        return this.suradnice.getY();
    }

    /**
     * aktualizuje suradnice na kt. sa nachadza hrac
     */
    public void nastavNoveSuradnice(Suradnice noveSuradnice) {
        this.suradnice = noveSuradnice;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getSkore() {
        return this.skore;
    }

    public void zmenHP(int oKolko) {
        this.healthPoints = this.healthPoints + oKolko;

        if (this.healthPoints <= 0) { //hrac je mrtvy, nema dostatok hp
            this.healthPoints = 0; //HP mu je nastavene na 0 aby sa neslo do zapornych hodnot
        }
    }

    public void upravSkore(int kolko) {
        this.skore += kolko;
    }

    public String getMeno() {
        return this.nickname;
    }
}
