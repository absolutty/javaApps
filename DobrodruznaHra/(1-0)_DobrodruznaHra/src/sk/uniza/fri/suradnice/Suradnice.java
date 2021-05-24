package sk.uniza.fri.suradnice;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * aktuálne Súradnice na ktorých sa nachádza hráč.
 */
public class Suradnice {
    private final int x;
    private final int y;

    public Suradnice(int zaciatocneX, int zaciatocneY) {
        this.x = zaciatocneX;
        this.y = zaciatocneY;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
