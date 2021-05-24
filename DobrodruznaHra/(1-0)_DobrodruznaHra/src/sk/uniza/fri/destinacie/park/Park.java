package sk.uniza.fri.destinacie.park;

import sk.uniza.fri.Cislo;
import sk.uniza.fri.DobrodruznaHra;
import sk.uniza.fri.destinacie.Destinacia;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Destinácia Park umožňuje dáva hráčovi príklady na ktoré zadáva výsledky
 * - pri jednom navštívení sú mu dané 3 príklady
 * - ak hráč trafí výsledok príkladu, pripočíta mu k skóre +25
 * - ak hráč netrafí výsledok príkladu, odpočíta mu od skore -25
 */
public class Park extends Destinacia {
    private static final String NAZOV = "Park";
    private static final Color FARBA = Color.GREEN;

    private final HashMap<Integer, Priklad> zoznamPrikladov = new HashMap<>();
    private final ArrayList<Integer> polozenePriklady = new ArrayList<>();

    public Park() {
        super(Park.NAZOV, Park.FARBA);
        this.nacitajPriklady();
    }

    private static final String[] MOZNOSTI = new String[] {"Počítanie príkladov", "Ukonči prezeranie parku"};
    @Override
    public void navstiv() {
        super.navstiv();
        int vyber = super.vyberMoznost(Park.MOZNOSTI);

        if (vyber == 1) {
            this.pokladajPriklady();
        }
    }

    private static final int POCET_PRIKLADOV = 3;
    private static final int HODNOTA_PRIKLADU = 25;
    private void pokladajPriklady() {
        for (int i = 1; i <= Park.POCET_PRIKLADOV; i++) {
            int cisloOtazky = Cislo.generujRandom(1, this.zoznamPrikladov.size());

            while (this.polozenePriklady.contains(cisloOtazky)) {
                cisloOtazky = Cislo.generujRandom(1, this.zoznamPrikladov.size());
            }
            this.polozenePriklady.add(cisloOtazky);

            Priklad priklad = this.zoznamPrikladov.get(cisloOtazky);
            boolean odpovedalSpravne = priklad.zadajPriklad();

            if (odpovedalSpravne) { //ak hráč ODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola správna!");
                DobrodruznaHra.dajInstanciu().upravSkore(Park.HODNOTA_PRIKLADU);
            } else { //ak hráč NEODPOVEDAL správne
                JOptionPane.showMessageDialog(DobrodruznaHra.dajInstanciu().getFrame(), "Tvoja odpoveď bola nesprávna!");

                DobrodruznaHra.dajInstanciu().upravHPHraca((Park.HODNOTA_PRIKLADU * -1));

                if (DobrodruznaHra.dajInstanciu().getHPHraca() <= 0) {
                    DobrodruznaHra.dajInstanciu().ukonciHru();
                }
            }
        }

    }

    private void nacitajPriklady() {
        try {
            String nazovDestinacie = this.getClass().getSimpleName();
            File subor = new File(String.format("destinacie-subory\\%s\\%sPriklady.txt", nazovDestinacie.toLowerCase(), nazovDestinacie));
            Scanner myReader = new Scanner(subor);

            while (myReader.hasNextLine()) {
                String riadok = myReader.nextLine();
                String[] str = riadok.split(" {2}");

                int cisloOtazky = Integer.parseInt(str[0]);
                String zneniePrikladu = str[1];
                int vysledok = Integer.parseInt(str[2]);

                this.zoznamPrikladov.put(cisloOtazky, new Priklad(zneniePrikladu, vysledok));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
