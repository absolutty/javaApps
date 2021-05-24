package sk.uniza.fri.grafickePrvky;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Táto trieda slúži na vykreslovanie pravej strany grafického zobrazenia.
 * Vykresluje tzv. menu.
 */
public class Menu extends JPanel {
    private Body body;

    private static final int PADDING = 20;

    public Menu() {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(Menu.PADDING, Menu.PADDING, Menu.PADDING, Menu.PADDING));
        this.generujZobrazenie();
    }

    public void generujZobrazenie() {
        this.setBackground(Color.CYAN);

        JTextField header = new JTextField("Dobrodružná hra");
        Uprav.komponent(header, 300, 40);
        this.add(header, BorderLayout.PAGE_START);

        this.body = new Body();
        this.add(this.body, BorderLayout.CENTER);

        JTextField footer = new JTextField("Vytvoril: Adam Hajro");
        Uprav.komponent(footer, 300, 40);
        this.add(footer, BorderLayout.PAGE_END);
    }

    private static class Body extends JPanel {
        private JTextField pocetHP;
        private JTextField pocetSkore;

        Body() {
            this.setLayout(new GridBagLayout());
            this.generujBody();
        }

        private void generujBody() {
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            JTextField hp = new JTextField("HP");
            Uprav.komponent(hp, 150, 30);
            this.add(hp, gbc);

            gbc.gridx = 1;
            this.pocetHP = new JTextField("100");
            Uprav.komponent(this.pocetHP, 100, 30);
            this.add(this.pocetHP, gbc);

            gbc.gridy = 1;
            gbc.gridx = 0;
            JTextField skore = new JTextField("Tvoje skóre");
            Uprav.komponent(skore, 150, 30);
            this.add(skore, gbc);

            gbc.gridx = 1;
            this.pocetSkore = new JTextField("0");
            Uprav.komponent(this.pocetSkore, 100, 30);
            this.add(this.pocetSkore, gbc);

        }
    }

    public void nastavHP(int kolko) {
        this.body.pocetHP.setText(String.valueOf(kolko));
    }

    public void nastavSkore(int kolko) {
        this.body.pocetSkore.setText(String.valueOf(kolko));
    }
}
