package sk.uniza.fri.destinacie.hrad;

import sk.uniza.fri.Cislo;
import sk.uniza.fri.DobrodruznaHra;
import sk.uniza.fri.destinacie.Destinacia;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Destinácia Hrad umožňuje pokladá hrá z hráčom otázkové hry
 * - pri jednom navštívení sú mu položené 3 otázky
 * - môže si zvoliť či chce odpovedať na áno/nie otázky
 *     - ak hráč uhádne otázku, pripočíta mu k skóre +20
 *     - ak hráč neuhádne otázku, odpočíta mu od skore -20
 * - môže si zvoliť odpovedanie na hádanky
 *     - ak hráč uhádne hádanku, pripočíta mu k skóre +30
 *     - ak hráč neuhádne hádanku, odpočíta mu od skore -30
 */
public class Hrad extends Destinacia {
    private static final String NAZOV = "Hrad";
    private static final Color FARBA = Color.PINK;

    private final HashMap<Integer, Otazka> zoznamOtazok = new HashMap<>();
    private final ArrayList<Integer> spytaneSaOtazky = new ArrayList<>();

    private final HashMap<Integer, Hadanka> zoznamHadaniek = new HashMap<>();
    private final ArrayList<Integer> spytaneSaHadanky = new ArrayList<>();

    public Hrad() {
        super(Hrad.NAZOV, Hrad.FARBA);
        this.nacitajOtazky();
        this.nacitajHadanky();
    }

    private static final String[] MOZNOSTI = new String[] {"Áno/nie otázky", "Hádanky", "Ukonči prezeranie parku"};
    @Override
    public void navstiv() {
        super.navstiv();
        int vyber = super.vyberMoznost(Hrad.MOZNOSTI);

        switch (vyber) {
            case 1 -> this.pokladajOtazky();
            case 2 -> this.pokladajHadanky();
        }
    }

    private static final int POCET_PYTANYCH_OTAZOK = 3;//počet otázok, kt. sú opýtané za každé navštívenie Destinácie
    private static final int HODNOTA_OTAZKY = 20; //koľko bodov/HP je pričítaných/odčítaných za odpovedanie otázky
    private void pokladajOtazky() {
        for (int i = 1; i <= Hrad.POCET_PYTANYCH_OTAZOK; i++) {
            int cisloOtazky = Cislo.generujRandom(1, this.zoznamOtazok.size());

            while (this.spytaneSaOtazky.contains(cisloOtazky)) {
                cisloOtazky = Cislo.generujRandom(1, this.zoznamOtazok.size());
            }
            this.spytaneSaOtazky.add(cisloOtazky);

            Otazka otazka = this.zoznamOtazok.get(cisloOtazky);
            boolean odpovedalSpravne = otazka.spytajSaOtazku();

            if (odpovedalSpravne) { //ak hráč ODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola správna!");
                DobrodruznaHra.dajInstanciu().upravSkore(Hrad.HODNOTA_OTAZKY);
            } else { //ak hráč NEODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola nesprávna!");
                if (!otazka.getOdpovedNaOtazku().equals("")) {
                    JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), otazka.getOdpovedNaOtazku());
                }

                DobrodruznaHra.dajInstanciu().upravHPHraca((Hrad.HODNOTA_OTAZKY * -1));

                if (DobrodruznaHra.dajInstanciu().getHPHraca() <= 0) {
                    DobrodruznaHra.dajInstanciu().ukonciHru();
                }
            }
        }
    }

    private void nacitajOtazky() {
        try {
            String nazovDestinacie = this.getClass().getSimpleName();
            File subor = new File(String.format("destinacie-subory\\%s\\%sOtazky.txt", nazovDestinacie.toLowerCase(), nazovDestinacie));
            Scanner myReader = new Scanner(subor);

            while (myReader.hasNextLine()) {
                String riadok = myReader.nextLine();
                String[] str = riadok.split(" {2}");

                int cisloOtazky = Integer.parseInt(str[0]);
                String znenieOtazky = str[1];
                int pravdivost = Integer.parseInt(str[2]);
                String odpovedNaOtazku = "";
                try {
                    odpovedNaOtazku = str[3];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    //pravdivá otázka
                }

                this.zoznamOtazok.put(cisloOtazky, new Otazka(znenieOtazky, pravdivost, odpovedNaOtazku));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final int HODNOTA_HADANKY = 30; //koľko bodov/HP je pričítaných/odčítaných za odpovedanie hadanky
    private void pokladajHadanky() {
        for (int i = 1; i <= Hrad.POCET_PYTANYCH_OTAZOK; i++) {
            int cisloHadanky = Cislo.generujRandom(1, this.zoznamHadaniek.size());

            while (this.spytaneSaHadanky.contains(cisloHadanky)) {
                cisloHadanky = Cislo.generujRandom(1, this.zoznamHadaniek.size());
            }
            this.spytaneSaHadanky.add(cisloHadanky);
            Hadanka hadanka = this.zoznamHadaniek.get(cisloHadanky);
            boolean odpovedalSpravne = hadanka.spytajSaHadanku();

            if (odpovedalSpravne) { //ak hráč ODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola správna!");
                DobrodruznaHra.dajInstanciu().upravSkore(Hrad.HODNOTA_HADANKY);
            } else { //ak hráč NEODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola nesprávna!, spravna je: " + hadanka.getSpravnuOdpoved());

                DobrodruznaHra.dajInstanciu().upravHPHraca((Hrad.HODNOTA_HADANKY * -1));

                if (DobrodruznaHra.dajInstanciu().getHPHraca() <= 0) {
                    DobrodruznaHra.dajInstanciu().ukonciHru();
                }
            }

        }
    }

    private void nacitajHadanky() {
        try {
            String nazovDestinacie = this.getClass().getSimpleName();
            File subor = new File(String.format("destinacie-subory\\%s\\%sHadanky.txt", nazovDestinacie.toLowerCase(), nazovDestinacie));
            Scanner myReader = new Scanner(subor);

            while (myReader.hasNextLine()) {
                String riadok = myReader.nextLine();
                String[] str = riadok.split(" {2}");

                int cisloHadanky = Integer.parseInt(str[0]);
                String znenieHadanky = str[1];
                ArrayList<String> mozneOdpovede = new ArrayList<>(Arrays.asList(str).subList(2, str.length));

                this.zoznamHadaniek.put(cisloHadanky, new Hadanka(znenieHadanky, mozneOdpovede.toArray(new String[0])));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("nenasiel som hadanky");
            e.printStackTrace();
        }
    }

}

