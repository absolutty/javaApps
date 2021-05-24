package sk.uniza.fri.suradnice;

import sk.uniza.fri.Cislo;

/**
 * 23. 3. 2021 - 16:46
 *
 * trieda RandSuradnice je určená pre vygenerovanie random súradnic v rozsahu hracej plochy
 */
public class RandSuradnice extends Suradnice {
    public RandSuradnice(int horneX, int horneY) {
        super(Cislo.generujRandom(0, horneX - 1), Cislo.generujRandom(0, horneY - 1));
    }
}
