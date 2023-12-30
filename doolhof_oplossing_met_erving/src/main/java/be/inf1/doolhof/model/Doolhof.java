package be.inf1.doolhof.model;


import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Point2D;

/**
 * In dit doolhof moet je in zo weinig mogelijk stappen de vakjes met punten in stijgende volgorde betreden.
 * Elk vakje mag je slechts eennmaal betreden.
 *
 * @author Kris Aerts & Wouter Groeneveld
 * @version 19/12/2021
 */
public class Doolhof
{
    private Vakje[][] rooster; // de vakjes
    private int breedte, hoogte; // afmetingen van het rooster
    private int stappen; // het aantal stappen dat al gezet is
    private int volgende; // het aantal punten van het volgende vakje
    private int aantal; // het aantal vakjes met punten
    private int x, y; // huidige positie in het rooster
    private Random r = new Random(); // om willekeurige getallen te genereren
    
    /**
     * Constructor voor een Doolhof van 10x10 met een willekeurig aantal punten tussen 2 en 10
     */
    public Doolhof()
    {
        this(10,10,(int)(Math.random()*8)+2);
    }
    
    /**
     * Maak een doolhof met de opgegeven afmetingen en het aantal vakjes met punten
     * 
     * @param b de breedte van het rooster
     * @param h de hoogte van het rooster
     * @param aantal het aantal vakjes met punten
     */
    public Doolhof(int b, int h, int aantal) 
    {
        initDoolhof(b, h, aantal);
    }

    private void initDoolhof(int b, int h, int aantal) {
        breedte = b;
        hoogte = h;
        rooster = new Vakje[breedte][hoogte];

        maakLeegDoolhof();

        this.aantal = aantal;
        // 3 varianten van code met hetzelfde effect
        //zetPuntenOpRandomPositiesMetWhile(aantal);
        //zetPuntenOpRandomPositiesMetFor(aantal);
        zetPuntenOpRandomPositiesMetForEnWhile(aantal);
        zetTunnels(aantal); // TOEVOEGING VOOR TUNNELS
        stappen = 0;
        volgende = 1;

        genereerWillekeurigeBeginPositie();
    }

    private int getRandomX() {
        return r.nextInt(breedte);
    }
    private int getRandomY() {
        return r.nextInt(hoogte);
    }
    
    private void genereerWillekeurigeBeginPositie() {
        x = getRandomX();
        y = getRandomY();
        // zolang er op die startpositie punten staan, kiezen we een andere positie
        while (huidigVakje().getPunten() > 0)
        {
            // probeer een andere positie
            x = getRandomX();
            y = getRandomY();
        }
    }
    
    private void zetPuntenOpRandomPositiesMetWhile(int aantal) {
        int i=1;
        while (i <= aantal)
        {
           // kies een willekeurige positie
            int x = getRandomX();
            int y = getRandomY();
            if (!getVakjeOp(x, y).isPuntenVakje())
            {
                rooster[x][y] = new Vakje(i);
                i++;
            }
        }
    }

    private void zetPuntenOpRandomPositiesMetFor(int aantal) {
        for (int i=0; i <= aantal; i++) {
           // kies een willekeurige positie
            int x = getRandomX();
            int y = getRandomY();
            if (getVakjeOp(x, y).isPuntenVakje()) {
                // als er punten staan, doe de i++ van de for-lus teniet
                // zodat je nog eens een positie probeert
                i--;
            }
            else {
                rooster[x][y] = new Vakje(i);
            }
        }
    }

    private void zetPuntenOpRandomPositiesMetForEnWhile(int aantal) {
        for (int i=0; i <= aantal; i++) {
           // kies een willekeurige positie
            int x = getRandomX();
            int y = getRandomY();
            // blijf zoeken tot er op de gekozen positie geen punten staan
            while (getVakjeOp(x, y).isPuntenVakje()) {
                x = getRandomX();
                y = getRandomY();
            }
            rooster[x][y] = new Vakje(i);
        }
    }

    private void zetTunnels(int aantal) {
        for (int i=0; i <= aantal; i++) {
           // kies een willekeurige positie
            int x = getRandomX();
            int y = getRandomY();
            // blijf zoeken tot er op de gekozen positie geen punten staan
            while (getVakjeOp(x, y).isPuntenVakje()) {
                x = getRandomX();
                y = getRandomY();
            }
            rooster[x][y] = new Tunnel();
        }
    }

    private void maakLeegDoolhof() {
        for (int i=0; i<breedte; i++) {
            for (int j=0; j<hoogte; j++) {
                rooster[i][j] = new Vakje();
            }
        }
    }

    /**
     * geeft vakje op positie
     * @param pos de positie van het vakje
     * @return het vakje in het rooster van het huidig spel
     */
    public Vakje getVakjeOp(int x, int y) {
        return rooster[x][y];
    }

    public Vakje huidigVakje() {
        return getVakjeOp(x, y);
    }

    public int getVolgende() {
        return volgende;
    }


    public void herstart() {
        initDoolhof(breedte, hoogte, aantal);
    }

    /**
     * hoeveel vakjes hebben we al verzameld?
     * 
     * @return het aantal vakjes dat al betreden is
     */
    public int getAantalBehaald() 
    {
        return volgende-1;
    }
    /**
     * hoeveel vakjes moeten we al verzamelen?
     * 
     * @return het aantal vakjes dat we moeten verzamelen
     */
    public int getAantal() 
    {
        return aantal;
    }
    /**
     * hoeveel stappen hebben we al gezet?
     * 
     * @return het aantal stappen
     */
    public int getStappen() 
    {
        return stappen;
    }
    /**
     * wat is de x-positie van het mannetje?
     * 
     * @return de x-positie
     */
    public int getX() 
    {
        return x;
    }
    /**
     * wat is de y-positie van het mannetje?
     * 
     * @return de y-positie
     */
    public int getY() 
    {
        return y;
    }
    /**
     * wat is de breedte van het rooster?
     * 
     * @return de breedte
     */
    public int getBreedte() {
        return breedte;
    }
    /**
     * wat is de hoogte van het rooster?
     * 
     * @return de hoogte
     */
    public int getHoogte() {
        return hoogte;
    }

    /**
     * ligt het opgegeven punt binnen het doolhof?
     * 
     * @param x de x-coordinaat
     * @param y de y-coordinaat
     * @return true indien (x,y) binnen de grenzen van het doolhof ligt
     */
    public boolean isBinnenRooster(int x, int y) {
        return x >= 0 && x < breedte && y >= 0 && y < hoogte;
    }

    
    /**
     * geef de buren van de speler
     * 
     * @return een ArrayList van punten met de coordinaten van de buren
     */
    public ArrayList<Point2D> getBurenVanSpeler() {
        ArrayList<Point2D> punten = new ArrayList<>();

        // links
        int vakjeX = x - 1;
        int vakjeY = y;
        
        if (isBinnenRooster(vakjeX,vakjeY)) {
            punten.add(new Point2D(vakjeX, vakjeY));
        }

        // rechts
        vakjeX = x + 1;
        vakjeY = y;
        
        if (isBinnenRooster(vakjeX,vakjeY)) {
            punten.add(new Point2D(vakjeX, vakjeY));
        }


        // boven
        vakjeX = x;
        vakjeY = y - 1;
        
        if (isBinnenRooster(vakjeX,vakjeY)) {
            punten.add(new Point2D(vakjeX, vakjeY));
        }


        // onder
        vakjeX = x;
        vakjeY = y + 1;
        
        if (isBinnenRooster(vakjeX,vakjeY)) {
            punten.add(new Point2D(vakjeX, vakjeY));
        }
        return punten;
    }

    /**
     * kan de speler nog bewegen?
     * Indien deze methode false is, is het spel afgelopen
     * 
     * @return true indien speler nog kan bewegen, false otherwise
     */
    public boolean kanBewegen() {
        ArrayList<Point2D> buren = getBurenVanSpeler();
        for (Point2D buur : buren) {
            int x = (int)buur.getX();
            int y = (int)buur.getY();
            Vakje buurVakje = getVakjeOp(x, y); 
            if (buurVakje.isBetreedbaar() && (!buurVakje.isPuntenVakje() || buurVakje.getPunten() == volgende)) return true;
        }
        return false;
    }
    
    /** 
     * ga naar rechts
     * 
     */
    public void rechts() {
        beweeg(1,0);
    }
    /** 
     * ga naar links
     * 
     */
    public void links() {
        beweeg(-1, 0);
    }
    /** 
     * ga naar boven
     * 
     */
    public void boven() {
        beweeg(0, -1);
    }
    /** 
     * ga naar onder
     * 
     */
    public void onder() {
        beweeg(0, 1);
    }
    
    /**
     * ga in de opgegeven richting
     * 
     * @param dx de verandering in x
     * @param dy de verandering in y
     */
    protected void beweeg(int dx, int dy) {
        int doelX = x + dx;
        int doelY = y + dy;
        if (!isBinnenRooster(doelX, doelY)) return;
        
        Vakje vakje = getVakjeOp(doelX, doelY);

        if (vakje.isBetreedbaar())
        {
            if (!vakje.isPuntenVakje())
            {
                beweegNaar(doelX, doelY, vakje);
            }
            else if (vakje.getPunten() == volgende)
            {
                volgende++;
                beweegNaar(doelX, doelY, vakje);
            }
        }
    }

    private void beweegNaar(int x, int y, Vakje vakje) {
        vakje.betreed();
        this.x = x;
        this.y = y;
        stappen++;
    }

}
