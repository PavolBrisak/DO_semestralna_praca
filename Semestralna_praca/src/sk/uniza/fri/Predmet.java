package sk.uniza.fri;

/**
 * 7. 5. 2023 - 7:45
 * Trieda reprezentuje predmet s definovanou hmotnosťou,cenou a poradím.
 * @author Brišák Pavol
 */
public class Predmet {
    private int hmotnost;
    private int cena;
    private int poradie;

    /**
     Konštruktor pre vytvorenie objektu predmetu s danou hmotnosťou, cenou a poradím.
     @param hmotnost hmotnosť predmetu
     @param cena cena predmetu
     @param poradie poradie predmetu
     */
    public Predmet(int hmotnost, int cena, int poradie) {
        this.hmotnost = hmotnost;
        this.cena = cena;
        this.poradie = poradie;
    }

    /**
     Metóda vráti hmotnosť predmetu.
     @return hmotnosť predmetu
     */
    public int getHmotnost() {
        return this.hmotnost;
    }

    /**
     Metóda vráti cenu predmetu.
     @return cena predmetu
     */
    public int getCena() {
        return this.cena;
    }

    /**
     Metóda vráti poradie predmetu.
     @return poradie predmetu
     */
    public int getPoradie() {
        return this.poradie;
    }
}
