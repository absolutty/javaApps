package sk.uniza.fri;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 10. 3. 2021 - 10:43
 *
 * @author adaha
 */
class CisloTest {
    //kladné čísla - začiatok
    private static final int k1 = 2;
    private static final int k2 = Integer.MAX_VALUE;
    private static final double k3 = Math.PI;
    private static final double k4 = Double.MAX_VALUE;
    //kladné čísla - koniec

    //záporné čísla - začiatok
    private static final int z1 = -2;
    private static final int z2 = Integer.MIN_VALUE;
    private static final double z3 = (Math.PI*-1);
    private static final double z4 = (Double.MAX_VALUE*-1);
    //záporné čísla - koniec

    @Test
    void kladneCisloTest() {
        assertTrue(Cislo.jeKladne(CisloTest.k1));
        assertTrue(Cislo.jeKladne(CisloTest.k2));
        assertTrue(Cislo.jeKladne(CisloTest.k3));
        assertTrue(Cislo.jeKladne(CisloTest.k4));

        assertFalse(Cislo.jeKladne(CisloTest.z1));
        assertFalse(Cislo.jeKladne(CisloTest.z2));
        assertFalse(Cislo.jeKladne(CisloTest.z3));
        assertFalse(Cislo.jeKladne(CisloTest.z4));
    }

    @Test
    void zaporneCisloTest() {
        assertTrue(Cislo.jeZaporne(CisloTest.z1));
        assertTrue(Cislo.jeZaporne(CisloTest.z2));
        assertTrue(Cislo.jeZaporne(CisloTest.z3));
        assertTrue(Cislo.jeZaporne(CisloTest.z4));

        assertFalse(Cislo.jeZaporne(CisloTest.k1));
        assertFalse(Cislo.jeZaporne(CisloTest.k2));
        assertFalse(Cislo.jeZaporne(CisloTest.k3));
        assertFalse(Cislo.jeZaporne(CisloTest.k4));
    }

    @Test
    void nulaTest() {
        assertTrue(Cislo.jeNula(0));
        assertFalse(Cislo.jeNula(CisloTest.k2));
        assertFalse(Cislo.jeNula(CisloTest.z2));
    }

    @Test
    void rozsahTest() {
        assertTrue(Cislo.vRozsahu(3, 2, 5));
        assertTrue(Cislo.vRozsahu(2, 2, 5));

        assertFalse(Cislo.vRozsahu(-7, 2,5));
        assertFalse(Cislo.vRozsahu(19, 2, 5));
    }

    private static final int POCET_TESTOVANI = 100;
    private static final int SPOD_HRANICA = 2;
    private static final int HOR_HRANICA = 7;
    @Test
    void randomTest() {
        for(int i = 0; i < CisloTest.POCET_TESTOVANI; i++) {
            int randNum = Cislo.generujRandom(CisloTest.SPOD_HRANICA, CisloTest.HOR_HRANICA);

            //zistí či vygenerované číslo randNum je v rozsahu spodnej a hornej hranice
            assertTrue((randNum>=CisloTest.SPOD_HRANICA) && (randNum<=CisloTest.HOR_HRANICA));
        }
    }
}