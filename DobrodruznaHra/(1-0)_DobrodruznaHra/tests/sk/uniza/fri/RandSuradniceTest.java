package sk.uniza.fri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.suradnice.RandSuradnice;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 */
class RandSuradniceTest {
    private RandSuradnice rnd1;
    private RandSuradnice rnd2;
    private RandSuradnice rnd3;
    private DobrodruznaHra h1;

    @BeforeEach
    void setUp() {
        this.rnd1 = new RandSuradnice(7, 7);
        this.rnd2 = new RandSuradnice(5, 3);
        this.rnd3 = new RandSuradnice(6, 8);
    }

    @Test
    void vypisHodnotyTest() {
        System.out.printf("rnd1-{ x:%d| y:%d}\n", this.rnd1.getX(), this.rnd1.getY());
        System.out.printf("rnd2-{ x:%d| y:%d}\n", this.rnd2.getX(), this.rnd2.getY());
        System.out.printf("rnd3-{ x:%d| y:%d}\n", this.rnd3.getX(), this.rnd3.getY());
    }

    /**
     * generuje hodnoty a zisti ci su vsetky v danom rozsahu
     */
    @Test
    void generujHodnotyTest() {
        final int hornaHodnota = 7;
        RandSuradnice rnd;

        for (int i = 0; i < 1000; i++) {
            rnd = new RandSuradnice(hornaHodnota, hornaHodnota);

            boolean xJeVrozsahu = Cislo.vRozsahu(rnd.getX(), 0, hornaHodnota);
            boolean yJeVrozsahu = Cislo.vRozsahu(rnd.getY(), 0, hornaHodnota);

            System.out.printf("rnd-{ x:%d| y:%d}\n", rnd.getX(), rnd.getY());

            assertTrue(xJeVrozsahu);
            assertTrue(yJeVrozsahu);
        }

    }
}