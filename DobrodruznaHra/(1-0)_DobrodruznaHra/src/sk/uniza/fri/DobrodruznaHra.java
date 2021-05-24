package sk.uniza.fri;

import sk.uniza.fri.destinacie.Destinacia;
import sk.uniza.fri.destinacie.domcek.Domcek;
import sk.uniza.fri.destinacie.hrad.Hrad;
import sk.uniza.fri.destinacie.park.Park;
import sk.uniza.fri.exceptions.PosunException;
import sk.uniza.fri.grafickePrvky.Menu;
import sk.uniza.fri.grafickePrvky.Uprav;
import sk.uniza.fri.suradnice.RandSuradnice;
import sk.uniza.fri.suradnice.Suradnice;
import sk.uniza.fri.destinacie.randomEvent.RandomEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * 4. 5. 2021 - 10:11
 *
 * V tomto projekte je použitá moja jedna knižnica 1-0_Funkcie kt. obsahuje pomocné metódy na prácu s číslami.
 *
 * @author adaha
 * trieda Dobrodružná hra slúži na vykreslenie a spustienie hry
 * - je návrhového typu Singleton.
 */
public class DobrodruznaHra {
    private static DobrodruznaHra instanciaObjektu;

    private static final int VELKOST_PLOCHY = 13; //veľkosť políčok na jednotlivých osiach
    private static final Destinacia[] ZOZNAM_DESTINACII = {new Hrad(), new Park(), new RandomEvent()}; //destinácie, vykreslované na hracej ploche

    private static final int VELKOST_WIDGETOV = 40; //základná veľkosť jednotlivých widgetov v hracej ploche (txtField, btn, ...)

    private final Policko[][] zoznamPolicok = new Policko[DobrodruznaHra.VELKOST_PLOCHY][DobrodruznaHra.VELKOST_PLOCHY]; //obsahuje vsetky policka hracej plochy
    private final JPanel zobrazeniePolicok = new JPanel(); //vykreslovaná hracia plocha
    private final Menu zobrazenieMenu = new Menu();

    private final JFrame frame = new JFrame(); //frame v ktorom sú uložené JPanel-y

    private Hrac hrac; //vytvarany je na zaklade mena, kt. uzivatel zada v metode generujJednotlivePolicka()

    /**
     * konštruktor, kt. vytvára hraciu plochu
     * sú v ňom invokované jednotlivé metódy pre generáciu a vykreslenie zobrazenia
     * je privátny keďže táto trieda je návrhového typu Singleton
     */
    private DobrodruznaHra() {
        this.inicializujPolicka();
        this.generujZobrazeniePolicok();

        this.vykresliFrame();
    }

    /**
     * vráti inštanciu tohto jedine-vytvoreného objektu
     */
    public static DobrodruznaHra dajInstanciu() {
        if (DobrodruznaHra.instanciaObjektu == null) {
            DobrodruznaHra.instanciaObjektu = new DobrodruznaHra();
        }

        return DobrodruznaHra.instanciaObjektu;
    }

    /**
     * nacita meno pomocou triedy JOptionPane
     *  - ak meno hrac zada meno v nespravnom formate, poziada ho o opatovne zadanie
     */
    private static String nacitajMeno() {
        String menoHraca;
        boolean spravneZadaneMeno = false;

        do {
            menoHraca = JOptionPane.showInputDialog(null, "Zadaj meno hráča: ", "Vitaj v mojej hre!", JOptionPane.QUESTION_MESSAGE);

            if (!menoHraca.equals("")) { //zadane meno JE spravne
                spravneZadaneMeno = true;
            } else { //zadane meno NIE JE spravne
                JOptionPane.showMessageDialog(null, "Meno zadane v chybnom formate!");
            }
        } while (!spravneZadaneMeno);

        return menoHraca;
    }

    /**
     * inicializuje jednotlivé hracie políčka
     * prístup k tejto metóde je umožnený iba 1-krát a to pri vytvaraní inšanicie
     */
    private void inicializujPolicka() {
        //vygeneruje plochu a zaciatocny domcek pre hraca
        for (int i = 0; i < this.zoznamPolicok.length; i++) {
            for (int j = 0; j < this.zoznamPolicok[i].length; j++) {
                //STRED HraciejPlochy, vygeneruje domček s hráčom
                if ((j == (DobrodruznaHra.VELKOST_PLOCHY) / 2) && (i == (DobrodruznaHra.VELKOST_PLOCHY) / 2)) { //domček je vždy v stredných políčkach plochy
                    String menoHraca = DobrodruznaHra.nacitajMeno();
                    this.frame.setTitle(String.format("Dobrodružná hra, hráč %s", menoHraca));
                    this.hrac = new Hrac(menoHraca, new Suradnice(i, j));

                    this.zoznamPolicok[i][j] = new Policko(new Domcek(), this.hrac);
                } else { //OSTATNÉ políčka
                    this.zoznamPolicok[i][j] = new Policko(null);
                }
            }
        }

        //vygeneruje jednotlive zaujimave destinacie (pocet zalezi od poctu v ZOZNAM_DESTINACII
        for (int i = 0; i < DobrodruznaHra.ZOZNAM_DESTINACII.length; i++) {
            RandSuradnice rnd = new RandSuradnice(DobrodruznaHra.VELKOST_PLOCHY, DobrodruznaHra.VELKOST_PLOCHY);

            while (this.obsahujeNejakuDestinaciu(rnd.getX(), rnd.getY())) { //cyklus sa opakuje do vtedy, pokial nenajde random volne policko
                rnd = new RandSuradnice(DobrodruznaHra.VELKOST_PLOCHY, DobrodruznaHra.VELKOST_PLOCHY);
            }

            this.zoznamPolicok[rnd.getX()][rnd.getY()] = new Policko(DobrodruznaHra.ZOZNAM_DESTINACII[i]);
        }
    }

    /**
     * vygeneruje jednotlivé prvky JFrame-u (hraciu plochu vykreslovanú v JPanely)
     * (lava strana)
     */
    private void generujZobrazeniePolicok() {
        this.zobrazeniePolicok.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //cyklus, generuje HORIZONTALNE oznacenia
        for (int stlpec = 1; stlpec <= DobrodruznaHra.VELKOST_PLOCHY; stlpec++) {
            gbc.gridx = stlpec;
            gbc.gridy = 0;

            JTextField txtField = new JTextField(String.valueOf(stlpec));
            Uprav.komponent(txtField, DobrodruznaHra.VELKOST_WIDGETOV, DobrodruznaHra.VELKOST_WIDGETOV / 2);

            this.zobrazeniePolicok.add(txtField, gbc);
        }

        //cyklus, generuje celu hraciu plochu + VERTIKALNE oznacenia
        for (int riadok = 1; riadok <= DobrodruznaHra.VELKOST_PLOCHY; riadok++) {
            for (int stlpec = 0; stlpec <= DobrodruznaHra.VELKOST_PLOCHY; stlpec++) {
                if (stlpec != 0) { //vykresluje hraciu plochu
                    gbc.gridx = stlpec;
                    gbc.gridy = riadok;

                    Policko policko = this.zoznamPolicok[riadok - 1][stlpec - 1];
                    Uprav.komponent(policko, DobrodruznaHra.VELKOST_WIDGETOV, DobrodruznaHra.VELKOST_WIDGETOV);
                    policko.setBackground(policko.getFarbuDestinacie());
                    policko.addActionListener(e -> JOptionPane.showMessageDialog(DobrodruznaHra.instanciaObjektu.frame, policko.dajPopis()));
                    this.zobrazeniePolicok.add(policko, gbc);

                } else { //vykresluje VERTIKALNE oznacenia
                    gbc.gridx = stlpec;
                    gbc.gridy = riadok;

                    JTextField txtField = new JTextField(String.valueOf(riadok));
                    Uprav.komponent(txtField, DobrodruznaHra.VELKOST_WIDGETOV / 2, DobrodruznaHra.VELKOST_WIDGETOV);

                    this.zobrazeniePolicok.add(txtField, gbc);
                }
            }
        }
    }

    /**
     * nastaví jednotlivé atribúty zobrazenia, následne ho zobrazí
     */
    private void vykresliFrame() {
        this.zobrazeniePolicok.setBackground(new Color(181, 181, 181));

        this.frame.setFocusable(true);
        this.frame.addKeyListener(new OvladanieHryKlavesnica());

        this.frame.add(this.zobrazenieMenu, BorderLayout.EAST);
        this.frame.add(this.zobrazeniePolicok, BorderLayout.CENTER);

        this.frame.setSize(1200, 800);
        this.frame.setPreferredSize(this.frame.getSize());
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);

        WindowListener ukoncenieListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int volba = JOptionPane.showConfirmDialog(DobrodruznaHra.this.frame, "Naozaj si želáš ukončiť hru?", "Ukončenie hry", JOptionPane.YES_NO_OPTION);
                if (volba == JOptionPane.YES_OPTION) {
                    DobrodruznaHra.this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//áno, želám si ukončiť hru
                    DobrodruznaHra.this.ukonciHru();
                } else {
                    DobrodruznaHra.this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//nie, chcem ostať v hre
                }
            }
        };

        this.frame.addWindowListener(ukoncenieListener);
    }

    /**
     * zistí či políčko na daných súradniciach obsahuje destináciu
     */
    private boolean obsahujeNejakuDestinaciu(int x, int y) {
        Policko p = this.zoznamPolicok[x][y];

        return p.getDestinaciaPolicka() != null;
    }

    /**
     * vnorená trieda, kt. je potomkom KeyAdapter
     * - umožňuje ovládanie pomocou klávesnice
     */
    private class OvladanieHryKlavesnica extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int inputKlavesnica = event.getKeyCode();

            if (inputKlavesnica == KeyEvent.VK_LEFT || inputKlavesnica == KeyEvent.VK_RIGHT || inputKlavesnica == KeyEvent.VK_UP || inputKlavesnica == KeyEvent.VK_DOWN) {
                try {
                    DobrodruznaHra.this.posunHraca(inputKlavesnica);
                } catch (PosunException ex) {
                    JOptionPane.showMessageDialog(DobrodruznaHra.this.frame, "Mimo plochy sa posunúť nemôžeš!");
                    DobrodruznaHra.this.zoznamPolicok[ex.getX()][ex.getY()].pridajHraca(DobrodruznaHra.this.hrac);
                }
            } else if (inputKlavesnica == KeyEvent.VK_A) {
                try {
                    DobrodruznaHra.this.vypisPolicko();
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(DobrodruznaHra.this.frame, "Takéto políčko tu neexistuje!");
                }
            }
        }
    }

    /**
     * na základe parametra metódy sa pokúsi posunúť hráča daným smerom
     */
    private void posunHraca(int smerPosunu) throws PosunException {
        int aktualneX = this.hrac.nachadzaSaNaX();
        int aktualneY = this.hrac.nachadzaSaNaY();

        Hrac docasneUlozenyHrac = this.zoznamPolicok[aktualneX][aktualneY].odoberHraca();

        if (smerPosunu == KeyEvent.VK_LEFT) {
            aktualneY--; //posunie do lava
            if (!Cislo.vRozsahu(aktualneY, 0, VELKOST_PLOCHY - 1)) {
                throw new PosunException(DobrodruznaHra.VELKOST_PLOCHY, aktualneX, aktualneY);
            }
        } else if (smerPosunu == KeyEvent.VK_RIGHT) {
            aktualneY++; //posunie do prava
            if (!Cislo.vRozsahu(aktualneY, 0, VELKOST_PLOCHY - 1)) {
                throw new PosunException(DobrodruznaHra.VELKOST_PLOCHY, aktualneX, aktualneY);

            }
        } else if (smerPosunu == KeyEvent.VK_UP) {
            aktualneX--;//posunie smerom hore
            if (!Cislo.vRozsahu(aktualneX, 0, VELKOST_PLOCHY - 1)) {
                throw new PosunException(DobrodruznaHra.VELKOST_PLOCHY, aktualneX, aktualneY);

            }
        } else if (smerPosunu == KeyEvent.VK_DOWN) {
            aktualneX++;//posunie smerom dole
            if (!Cislo.vRozsahu(aktualneX, 0, VELKOST_PLOCHY - 1)) {
                throw new PosunException(DobrodruznaHra.VELKOST_PLOCHY, aktualneX, aktualneY);
            }
        }

        docasneUlozenyHrac.nastavNoveSuradnice(new Suradnice(aktualneX, aktualneY));
        this.zoznamPolicok[aktualneX][aktualneY].pridajHraca(docasneUlozenyHrac);
    }

    /**
     * vypíše info o užívateľom zvolenom políčku
     */
    private void vypisPolicko() {
        int pozX = Integer.parseInt(JOptionPane.showInputDialog(DobrodruznaHra.this.frame, "Zadaj X: ")) - 1;
        int pozY = Integer.parseInt(JOptionPane.showInputDialog(DobrodruznaHra.this.frame, "Zadaj Y: ")) - 1;
        System.out.println(DobrodruznaHra.this.zoznamPolicok[pozX][pozY].toString());
    }

    /**
     *  upravi HP hráča na základe zadanej hodnoty
     *  - ak je záporné číslo: odoberie mu HP
     *  - ak je kladné číslo: prida mu HP
     */
    public void upravHPHraca(int oKolko) {
        this.hrac.zmenHP(oKolko);
        this.zobrazenieMenu.nastavHP(this.hrac.getHealthPoints());
    }

    public void upravSkore(int oKolko) {
        this.hrac.upravSkore(oKolko);
        this.zobrazenieMenu.nastavSkore(this.hrac.getSkore());

    }

    public int getHPHraca() {
        return this.hrac.getHealthPoints();
    }

    public int getSkoreHraca() {
        return this.hrac.getSkore();
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void ukonciHru() {
        if (this.hrac.getHealthPoints() <= 0) { //nedobrovolne ukoncenie hry (hrac nema dostatok HP)
            JOptionPane.showMessageDialog(this.frame, "Tvoj hráč je už mŕtvy :(", "Koniec hry", JOptionPane.ERROR_MESSAGE);
        }

        this.zapisNajvyssieSkore();
        JOptionPane.showMessageDialog(this.frame, "Ukončujem hru. Ak si dosiahol vyššie skóre ako je pri tvojom mene, prepíše sa.", "Koniec hry", JOptionPane.WARNING_MESSAGE);
        System.exit(1);
    }

    /**
     * zisti, ci skore kt. hrac dosiahol pocas hry je najvyssie, ak ano prepise ho
     */
    private void zapisNajvyssieSkore() {
        try { //hráč už hral túto hru, meno je zapísané
            File myObj = new File(String.format("najvyssie-skore\\%s.txt", this.hrac.getMeno()));
            Scanner myReader = new Scanner(myObj);

            String txt = myReader.nextLine();
            String[] str = txt.split(": ");
            int najvyssieSkore = Integer.parseInt(str[1]);

            if (this.getSkoreHraca() > najvyssieSkore) { //hráčom bol dosiahnutý nový rekord
                FileWriter zapisovac = new FileWriter(String.format("najvyssie-skore\\%s.txt", this.hrac.getMeno()));
                zapisovac.write(String.format("%s, najvyššie skóre: %d", this.hrac.getMeno(), this.hrac.getSkore()));
                zapisovac.close();
            }

            myReader.close();
        } catch (IOException ex) { //hráč ešte nehral túto hru, je potrebné mu vytvoriť nový .txt súbor
            try {
                FileWriter zapisovac = new FileWriter(String.format("najvyssie-skore\\%s.txt", this.hrac.getMeno()));
                zapisovac.write(String.format("%s, najvyššie skóre: %d", this.hrac.getMeno(), this.hrac.getSkore()));
                zapisovac.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
