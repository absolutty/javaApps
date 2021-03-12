package sk.uniza.fri;

/**
 * 10. 3. 2021 - 10:43
 * @author Adam Hajro
 * @version 1.0
 *
 * funkciou tejto triedy je vypisovať rozdelenia do príkazového riadku
 * --> zvýši to čitatelnosť jednotlivých vypisovaných prvkov
 */
public class Rozdelenie {
    /**
     * vypíše rozdelenie o dĺžke 20, znak: "-"
     * */
    public static void vypis() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * @param kolko - dĺžka vypísania
     * vypíše rozdelenie o dĺžke n-znakov, znak: "-"
     * */
    public static void vypis(int kolko) {
        for (int i = 0; i <= kolko; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * @param znakNaVypis - samostný znak na výpis
     * vypíše rozdelenie o dĺžke 20, znak: znakNaVypis
     * */
    public static void vypis(char znakNaVypis) {
        for (int i = 0; i <= 20; i++) {
            System.out.print(znakNaVypis);
        }
        System.out.println();
    }

    /**
     * @param kolko - dĺžka vypísania
     * @param znakNaVypis - samostný znak na výpis
     * vypíše rozdelenie o dĺžke n-znakov, znak: znakNaVypis
     * */
    public static void vypis(int kolko, char znakNaVypis) {
        for (int i = 0; i <= kolko; i++) {
            System.out.print(znakNaVypis);
        }
        System.out.println();
    }
}
