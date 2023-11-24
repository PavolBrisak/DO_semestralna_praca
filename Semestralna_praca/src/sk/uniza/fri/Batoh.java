package sk.uniza.fri;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 7. 5. 2023 - 7:45
 *
 * Trieda Batoh reprezentuje batohový problém s implementovanými heuristikami.
 *
 * Autor: Brišák Pavol
 */
public class Batoh {
    private ArrayList<Predmet> nespracovanePrvky = new ArrayList<>();
    private ArrayList<Predmet> batoh = new ArrayList<>();
    private int hmotnostBatohu = 0;
    private int pocetPrvkovVBatohu = 0;
    private int hodnotaUcelovejFunkcie = 0;
    private int k = 9500;
    private int r = 350;
    private boolean koniec = false;

    /**
     * Konštruktor pre vytvorenie objektu triedy Batoh.
     */
    public Batoh() {
    }

    /**
     * Metóda načíta údaje o predmetoch zo súborov H3_a.txt a H3_c.txt a inicializuje nespracované prvky a batoh.
     * Batoh aj nespracované prvky naplní predmetmi.
     *
     * @throws FileNotFoundException ak súbor neexistuje
     */
    public void nacitajZoSuboru() throws FileNotFoundException {
        java.util.Scanner citacH = new java.util.Scanner(new java.io.File("H3_a.txt"));
        java.util.Scanner citacC = new java.util.Scanner(new java.io.File("H3_c.txt"));
        int poradie = 1;
        while (citacH.hasNextInt()) {
            int hmotnost = citacH.nextInt();
            int cena = citacC.nextInt();
            Predmet predmet = new Predmet(hmotnost, cena, poradie);
            this.nespracovanePrvky.add(predmet);
            this.batoh.add(predmet);
            this.hmotnostBatohu += hmotnost;
            this.hodnotaUcelovejFunkcie += cena;
            this.pocetPrvkovVBatohu++;
            poradie++;
        }
    }

    /**
     * Metóda načíta údaje o predmetoch zo súborov H3_a.txt a H3_c.txt a inicializuje iba nespracované prvky.
     * Naplni len nespracované prvky.
     *
     * @throws FileNotFoundException ak súbor neexistuje
     */
    public void nacitajZoSuboruBonus() throws FileNotFoundException {
        java.util.Scanner citacH = new java.util.Scanner(new java.io.File("H3_a.txt"));
        java.util.Scanner citacC = new java.util.Scanner(new java.io.File("H3_c.txt"));
        int poradie = 1;
        while (citacH.hasNextInt()) {
            int hmotnost = citacH.nextInt();
            int cena = citacC.nextInt();
            Predmet predmet = new Predmet(hmotnost, cena, poradie);
            this.nespracovanePrvky.add(predmet);
            poradie++;
        }
    }

    /**
     * Metóda vráti index predmetu s najmenšou hmotnosťou medzi nespracovanými prvkami.
     *
     * @return index predmetu s najmenšou hmotnosťou
     */
    public int dajIndexNajlepsiehoHmotnost() {
        int index = 0;
        int najnizsiaHmotnost = Integer.MAX_VALUE;
        for (int i = 0; i < this.nespracovanePrvky.size(); i++) {
            if (this.nespracovanePrvky.get(i).getHmotnost() < najnizsiaHmotnost) {
                najnizsiaHmotnost = this.nespracovanePrvky.get(i).getHmotnost();
                index = i;
            }
        }
        return index;
    }

    /**
     * Metóda vráti index predmetu s najnižšou cenou medzi nespracovanými prvkami.
     *
     * @return index predmetu s najnižšou cenou
     */
    public int dajIndexNajlepsiehoCena() {
        int index = 0;
        int najnizsiaCena = Integer.MAX_VALUE;
        for (int i = 0; i < this.nespracovanePrvky.size(); i++) {
            if (this.nespracovanePrvky.get(i).getCena() < najnizsiaCena) {
                najnizsiaCena = this.nespracovanePrvky.get(i).getCena();
                index = i;
            }
        }
        return index;
    }

    /**
     * Metóda implementuje heuristiku na riešenie batohového problému.
     * Postupne vybera predmety z batohu pokiaľ nie je splnená podmienka pre
     * aspoň 350 predmetov a hmotnosť batohu 9500.
     */
    public void heuristika() {
        while (!this.koniec) {
            int index = this.dajIndexNajlepsiehoHmotnost();
            if ((this.hmotnostBatohu - this.nespracovanePrvky.get(index).getHmotnost() >= this.k) && (this.pocetPrvkovVBatohu - 1 >= this.r)) {
                this.hmotnostBatohu -= this.nespracovanePrvky.get(index).getHmotnost();
                this.hodnotaUcelovejFunkcie -= this.nespracovanePrvky.get(index).getCena();
                this.pocetPrvkovVBatohu--;
                this.nespracovanePrvky.remove(index);
                this.batoh.remove(index);
            } else {
                this.koniec = true;
            }
        }
    }

    /**
     * Metóda implementuje bonusovú heuristiku na riešenie batohového problému.
     * Batoh sa postupne napĺňa predmetmi s namenšou cenou a opakuje to pokiaľ
     * nie je splnená podmienka pre aspoň 350 predmetov a hmotnosť batohu 9500.
     */
    public void bonusovaHeuristika() {
        while (!this.koniec) {
            int index = this.dajIndexNajlepsiehoCena();
            if ((this.hmotnostBatohu + this.nespracovanePrvky.get(index).getHmotnost() <= this.k) || (this.pocetPrvkovVBatohu + 1 <= this.r)) {
                this.hmotnostBatohu += this.nespracovanePrvky.get(index).getHmotnost();
                this.hodnotaUcelovejFunkcie += this.nespracovanePrvky.get(index).getCena();
                this.pocetPrvkovVBatohu++;
                this.batoh.add(this.nespracovanePrvky.get(index));
                this.nespracovanePrvky.remove(index);
            } else {
                this.koniec = true;
            }
        }
    }

    /**
     * Metóda zapisuje výsledky riešenia do zadaného súboru.
     *
     * @param nazovSuboru názov súboru, do ktorého sa majú výsledky zapísať
     * @throws FileNotFoundException ak súbor neexistuje
     */
    public void zapisDoSuboru(String nazovSuboru) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(nazovSuboru);
        writer.write("Hmotnosť batohu: " + this.hmotnostBatohu + "\n");
        writer.write("Počet prvkov v batohu: " + this.pocetPrvkovVBatohu + "\n");
        writer.write("Hodnota účelovej funkcie: " + this.hodnotaUcelovejFunkcie + "\n");
        for (Predmet predmet : this.batoh) {
            writer.write("Predmet č." + predmet.getPoradie() + " : Hmotnosť " + predmet.getHmotnost() + ", Cena " + predmet.getCena() + "\n");
            writer.write("--------------\n");
        }
        writer.close();
    }

    /**
     * Metóda vypíše informácie o obsahu batohu na konzolu.
     * Vypíše sa hmotnosť batohu, počet predmetov v batohu a hodnota účelovej funkcie.
     */
    public void vypisBatoh() {
        System.out.println("Hmotnosť batohu: " + this.hmotnostBatohu);
        System.out.println("Počet prvkov v batohu: " + this.pocetPrvkovVBatohu);
        System.out.println("Hodnota účelovej funkcie: " + this.hodnotaUcelovejFunkcie);
    }
}
