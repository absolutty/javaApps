package sk.uniza.fri.destinacie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 23. 3. 2021 - 16:46
 *
 * @author adaha
 * Každá destinácia má svoj popis, kt. je načítaný zo súboru
 */
public class PopisDestinacie {
    private final ArrayList<String> popis = new ArrayList<>();

    public PopisDestinacie(Destinacia destinacia) {
        this.nacitajPopis(destinacia);
    }

    /**
     * popis je načítavany na základe názvu destinácie.
     * napr. HradInfo.txt
     */
    private void nacitajPopis(Destinacia destinacia) {
        try {
            String nazovDestinacie = destinacia.getClass().getSimpleName();
            File subor = new File(String.format("destinacie-subory\\%s\\%sInfo.txt", nazovDestinacie.toLowerCase(), nazovDestinacie));
            Scanner myReader = new Scanner(subor);

            while (myReader.hasNextLine()) {
                String riadok = myReader.nextLine();
                this.popis.add(riadok);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getPopis() {
        StringBuilder sb = new StringBuilder();

        for (String str : this.popis) {
            sb.append(String.format("%s\n", str));
        }

        return sb.toString();
    }
}
