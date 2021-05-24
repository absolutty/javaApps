package sk.uniza.fri;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sk.uniza.fri.destinacie.*;
import sk.uniza.fri.destinacie.domcek.Domcek;
import sk.uniza.fri.destinacie.hrad.Hrad;
import sk.uniza.fri.destinacie.park.Park;
import sk.uniza.fri.destinacie.randomEvent.RandomEvent;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 */
class DestinacieTest {
    private Destinacia d1;
    private Destinacia d2;
    private Destinacia d3;
    private Destinacia d4;

    @BeforeEach
    void setUp() {
        this.d1 = new Domcek();
        this.d2 = new Hrad();
        this.d3 = new Park();
        this.d4 = new RandomEvent();
    }

    @Test
    void vypisNazvy() {
        System.out.printf("d1: {key: %s, nazov: %s}\n", this.d1.getKey(), this.d1.getNazov());
        System.out.printf("d2: {key: %s, nazov: %s}\n", this.d2.getKey(), this.d2.getNazov());
        System.out.printf("d3: {key: %s, nazov: %s}\n", this.d3.getKey(), this.d3.getNazov());
        System.out.printf("d4: {key: %s, nazov: %s}\n", this.d4.getKey(), this.d4.getNazov());
    }
}