package sk.uniza.fri;

import java.io.FileNotFoundException;

/**
 * Hlavná trieda aplikácie, ktorá obsahuje metódu main na spustenie programu.
 * Program rieši problém batohu a používa triedu Batoh na výpočet riešenia.
 * Následne sa spustí bonusová heuristika.
 *
 * Created by IntelliJ IDEA.
 * User: Brišák Pavol
 * Date: 7. 5. 2023
 * Time: 7:45
 */
public class Main {

    /**
     * Metóda main slúži na spustenie programu a demonštráciu riešenia problému batohu.
     * Vytvára inštanciu triedy Batoh, načíta vstupné dáta zo súboru, vypíše východiskové riešenie,
     * aplikuje heuristický algoritmus na výpočet konečného riešenia, vypíše konečné riešenie
     * a zápisuje výsledky do súboru.
     * Obdobne to spraví aj pre bonusovú heuristiku.
     *
     * @param args argumenty príkazového riadku (nie sú použité)
     * @throws FileNotFoundException ak súbor s vstupnými dátami neexistuje
     */
    public static void main(String[] args) throws FileNotFoundException {
        Batoh batoh = new Batoh();
        batoh.nacitajZoSuboru();
        System.out.println("Východiskové riešenie: plný batoh");
        batoh.vypisBatoh();
        System.out.println("-------------------");
        batoh.heuristika();
        System.out.println("Konečné riešenie: ");
        batoh.vypisBatoh();
        batoh.zapisDoSuboru("vysledok.txt");
        System.out.println("-------------------");
        System.out.println();

        Batoh batohBonus = new Batoh();
        batohBonus.nacitajZoSuboruBonus();
        System.out.println("Východiskové riešenie bonus: prázdny batoh");
        batohBonus.vypisBatoh();
        System.out.println("-------------------");
        batohBonus.bonusovaHeuristika();
        System.out.println("Konečné riešenie bonus: ");
        batohBonus.vypisBatoh();
        batohBonus.zapisDoSuboru("vysledok_bonus.txt");
        System.out.println("-------------------");
    }
}
