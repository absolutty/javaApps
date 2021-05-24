package sk.uniza.fri.exceptions;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Výnimka, kt. nastane ak sa hráč pokúša posunúť mimo hracej plochy.
 * Obsahuje atribúty x a y, kt. sú potom použíté na opätovné nastavenie pozície hráča.
 */
public class PosunException extends ArrayIndexOutOfBoundsException {
    private final int x;
    private final int y;

    public PosunException(int velkostPlochy, int aktualneX,  int aktualneY) {
        super("[pokus o posun mimo rozsahu plochy]");
        this.x = PosunException.upravHodnotu(velkostPlochy, aktualneX);
        this.y = PosunException.upravHodnotu(velkostPlochy, aktualneY);
    }

    /**
     * keďže PosunException nastane vtedy, keď sa hráč pokúša posunúť mimohracej plocy,
     * táto trieda si so sebou nesie hodnoty x a y ktoré je potrebné upraviť pre ďalšie použitie.
     * - ak je menej ako 0 (hráč sa pokúša posunúť ZÁPORNÝM smerom menším ako je 0)
     * - ak je väčší ako veľkosť hracej plochy (hráč sa pokúša posunúť KLADNÝM smerom väčším ako je veľkosť hracej plochy)
     */
    public static int upravHodnotu(int velkostPlochy, int hodnota) {
        int upravenaHodnota;

        if (hodnota < 0) {
            upravenaHodnota = hodnota + 1;
            return upravenaHodnota;
        } else if (hodnota >= velkostPlochy) {
            upravenaHodnota = hodnota - 1;
            return upravenaHodnota;
        } else {
            return  hodnota;
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
