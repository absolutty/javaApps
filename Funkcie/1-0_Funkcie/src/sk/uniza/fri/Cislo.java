package sk.uniza.fri;

import java.util.Random;

/**
 * 10. 3. 2021 - 10:43
 * @author Adam Hajro
 * @version 1.0
 *
 * funkciou tejto triedy overiť zadané číslo/čisla na jednotlivé pre jednotlivé vlastnosti
 * -->kladné, záporné, nula, v rozsahu, generujRandom...
 */
public class Cislo {
    /**
     * @param cislo - testované číslo.
     * zistí či int číslo zadané ako parameter metódy je KLADNÉ (nula je braná ako kladné číslo).
     * */
    public static boolean jeKladne(int cislo) {
        return (cislo >= 0);
    }

    /**
     * @param cislo - testované číslo.
     * zistí či double číslo zadané ako parameter metódy je KLADNÉ (nula je braná ako kladné číslo).
     * */
    public static boolean jeKladne(double cislo) {
        //dodržanie kohézie, číslo je castnuté na int a invokovaná je metóda jeKladne(int cislo)
        return Cislo.jeKladne((int)cislo);
    }

    /**
     * @param cislo - testované číslo.
     * zistí či int číslo zadané ako parameter metódy je ZÁPORNÉ.
     * */
    public static boolean jeZaporne(int cislo) {
        return (cislo < 0);
    }

    /**
     * @param cislo - testované číslo.
     * zistí či double číslo zadané ako parameter metódy je ZÁPORNÉ.
     * */
    public static boolean jeZaporne(double cislo) {
        //dodržanie kohézie, číslo je castnuté na int a invokovaná je metóda jeZaporne(int cislo)
        return Cislo.jeZaporne((int)cislo);
    }

    /**
     * @param cislo - testované číslo.
     * zistí či int číslo zadané ako parameter metódy je NULA.
     * */
    public static boolean jeNula(int cislo) {
        return (cislo == 0);
    }

    /**
     * @param cislo - testované číslo.
     * @param spodHranica - dolná hranica čísla.
     * @param horHranica - horná hranica čísla.
     * zistí či zadané int číslo je v rozsahu spodnej hranice a hornej hranice.
     * */
    public static boolean vRozsahu(int cislo, int spodHranica, int horHranica) {
        return (cislo >= spodHranica) && (cislo <= horHranica);
    }

    /**
     * @param spodHranica - OD kade gener. čísla.
     * @param horHranica - DO kade gener. čísla.
     * generuje náhodné číslo v určenom rozsahu
     * */
    public static int generujRandom(int spodHranica, int horHranica) {
        return new Random().nextInt(horHranica - spodHranica + 1) + spodHranica;
    }
}
