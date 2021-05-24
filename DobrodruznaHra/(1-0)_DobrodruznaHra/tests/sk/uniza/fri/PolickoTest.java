package sk.uniza.fri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.destinacie.domcek.Domcek;
import sk.uniza.fri.destinacie.hrad.Hrad;
import sk.uniza.fri.suradnice.Suradnice;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 */
class PolickoTest {
    private Policko p1;
    private Policko p2;
    private Policko p3;
    private Policko p4;

    @BeforeEach
    void setUp() {
        this.p1 = new Policko(null); //p1 - NEOBSADENÉ a PRÁZDNE políčko
        this.p2 = new Policko(null, new Hrac("test-panak", new Suradnice(0, 0))); //p2 - OBSADENÉ a PRÁZDNE políčko
        this.p3 = new Policko(new Domcek()); //p3 - NEOBSADENÉ a Domcek destinacia
        this.p4 = new Policko(new Hrad(), new Hrac("test-panak", new Suradnice(0,0))); //p4 - OBSADENÉ a Hrad destinacia
    }

    @Test
    void p1Test() {
        assertFalse(this.p1.jeObsadene());
        assertNull(this.p1.getDestinaciaPolicka());
        assertEquals("--", this.p1.toString());

        System.out.printf("p1: {toString: (%s), jeObsadene: %s}\n", this.p1.toString(), (this.p1.jeObsadene() ? "áno" : "nie"));
    }

    @Test
    void p2Test() {
        assertTrue(this.p2.jeObsadene());
        assertNull(this.p2.getDestinaciaPolicka());
        assertEquals("-x", this.p2.toString());

        System.out.printf("p2: {toString: (%s), jeObsadene: %s}\n", this.p2.toString(), (this.p2.jeObsadene() ? "áno" : "nie"));
    }

    @Test
    void p3Test() {
        assertFalse(this.p3.jeObsadene());
        assertNotNull(this.p3.getDestinaciaPolicka());
        assertEquals("D-", this.p3.toString());

        System.out.printf("p3: {toString: (%s), jeObsadene: %s}\n", this.p3.toString(), (this.p3.jeObsadene() ? "áno" : "nie"));
    }

    @Test
    void p4Test() {
        System.out.printf("p4: {toString: (%s), jeObsadene: %s}\n", this.p4.toString(), (this.p4.jeObsadene() ? "áno" : "nie"));
    }
}