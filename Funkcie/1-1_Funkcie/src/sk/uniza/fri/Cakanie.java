package sk.uniza.fri;

/**
 * 20. 3. 2021 - 12:26
 *
 * @author adaha
 */
public class Cakanie {
    public static void spusti() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private static final int DEF_HODNOTA = 1000; //hodnota, pomocou kt. sa prenásobí z ms na sek
    public static void spusti(int pocetSek) {
        try {
            Thread.sleep((long)pocetSek * Cakanie.DEF_HODNOTA);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
