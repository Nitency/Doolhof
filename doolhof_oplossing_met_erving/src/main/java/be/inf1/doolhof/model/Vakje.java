package be.inf1.doolhof.model;

/**
 * Een vakje op het doolhof waarin eventueel punten zitten en waar je maar één keer over mag gaan
 *
 * @author Kris Aerts & Wouter Groeneveld
 * @version 23/03/2018
 */
public class Vakje
{
    private int     punten;
    private boolean alBetreden;

    public Vakje()
    {
        this(0);
    }
    
    /**
     * Een constructor om een Vakje mét punten te maken
     * @param punten het aantal punten van dit vakje
     */
    public Vakje(int punten)
    {
        this.punten = punten;
        alBetreden = false;
    }
    

    public int getPunten() 
    {
       return punten;
    }
    
    /**
     * mag je dit vakje betreden?
     * 
     * @return true als het vakje nog niet betreden is, false als het al betreden is
     */
    public boolean isBetreedbaar() {
        return !alBetreden;
    }

    public boolean isAlBetreden() {
        return alBetreden;
    }
    
    public boolean isPuntenVakje() {
        return punten > 0;
    }

    /**
     * betreed dit vakje.
     * Zorgt er voor dat isBetreedbaar() false wordt.
     * 
     */
    public void betreed() {
        alBetreden = true;
    }
}
