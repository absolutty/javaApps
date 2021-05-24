package sk.uniza.fri;

import sk.uniza.fri.destinacie.Destinacia;

import java.awt.Button;
import java.awt.Color;

/**
 * 26. 2. 2021 - 19:42
 *
 * @author adaha
 * Políčko je vykreslované na plochu.
 * Táto trieda extenduje Button, čo znamená že každému poličku je možné nastaviť onClickListener{} (vypíše info)
 */
public class Policko extends Button {
    private final Destinacia destinaciaPolicka;
    private Hrac hrac;
    private boolean jeObsadeneHracom;

    /**
     * preťažený konštruktor, destinaciaPolicka je nastavená na danú destináciu na navštívenie
     * */
    public Policko(Destinacia destinaciaPolicka) {
        this.destinaciaPolicka = destinaciaPolicka;
        this.setLabel(this.toString());
    }

    /**
     * preťažený konštruktor, destinaciaPolicka je nastavená na danú destináciu na navštívenie a je na nu pridany aj hrac
     * */
    public Policko(Destinacia destinaciaPolicka, Hrac hracDoHry) {
        this.destinaciaPolicka = destinaciaPolicka;
        this.hrac = hracDoHry;
        this.jeObsadeneHracom = true;
        this.setLabel(this.toString());
    }

    /**
     * zisti ci nie je pridany ziaden hrac
     *  - ak nie: prida ho
     *  - ak ano: vyhodi exception
     */
    public void pridajHraca(Hrac hrac) {
        if (this.hrac == null) { //JE mozne pridaj hraca
            this.hrac = hrac;
            this.jeObsadeneHracom = true;
            this.setLabel(this.toString());
            this.navstivDestinaciu();

        } else { //NIE JE mozne pridaj hraca
            throw new RuntimeException("na tomto policku uz je pridany hrac");
        }
    }

    /**
     * pokusi sa odobrat hraca z daneho polick
     * - ak tu hrac JE: odoberie ho a vrati
     * - ak tu hrac NIE JE: vyhodi exception
     */
    public Hrac odoberHraca() {
        if (this.hrac != null) { //JE mozne odobrat hraca
            Hrac docasneUlozenyHrac = this.hrac;
            this.hrac = null;
            this.jeObsadeneHracom = false;
            this.setLabel(this.toString());

            return docasneUlozenyHrac;
        } else { //NIE JE mozne odobrat hraca
            throw new RuntimeException("na tomto policku nie je ziadny hrac");
        }
    }

    private void navstivDestinaciu() {
        if (this.destinaciaPolicka != null) {
            this.destinaciaPolicka.navstiv();
        }
    }

    /**
     * vráti reprezentáciu políčka
     * (-- : prázdne poličko, bez hráča), (D- : domček políčko, bez hráča), (-x : prázdne poličko, s hráčom), (Dx : domček políčko, s hráčom)
     * */
    @Override
    public String toString() {
        StringBuilder repre = new StringBuilder(); //prázdny String, k nemu sa budú pridávať znaky, na konci bude vrátený

        //skúška DESTINÁCIE
        if (this.destinaciaPolicka != null) { //ak toto políčko MÁ destináciu
            repre.append(this.destinaciaPolicka.getKey());
        } else { //ak toto políčko NEMÁ destináciu
            repre.append('-');
        }

        //skúška OBSADENOSTI hráčom
        if (this.jeObsadeneHracom) { //ak JE obsadené
            repre.append('x');
        } else { //ak NIE JE obsadené
            repre.append('-');
        }

        return repre.toString();
    }

    /**vráti danú destináciu políčka*/
    public Destinacia getDestinaciaPolicka() {
        return this.destinaciaPolicka;
    }

    /**
     * vráti boolean o obsadenosti políčka
     * */
    public boolean jeObsadene() {
        return this.jeObsadeneHracom;
    }

    /**
     * vráti farbu, v ktorej má byť vykreslovaná destinaciaPolicka
     */
    public Color getFarbuDestinacie() {
        try {
            return this.destinaciaPolicka.getFarba();
        } catch (NullPointerException ex) {
            return Destinacia.getDefaultFarbaDestinacie();
        }
    }

    /**
     * opíše destináciu, kt. sa nachádza na políčku
     */
    public String dajPopis() {
        if (this.destinaciaPolicka != null) { //je na ňom nejaká zaujímava destinácia
            return this.destinaciaPolicka.getPopis();
        } else { //nie je na ňom nejaká zaujímava destinácia
            return  "Na tomto políčku sa nenachádza žiadna destinácia.";
        }
    }
}
