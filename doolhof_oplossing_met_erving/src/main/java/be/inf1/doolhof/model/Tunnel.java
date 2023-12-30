/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.doolhof.model;

/**
 *
 * @author u0002531
 */
public class Tunnel extends Vakje {
    private boolean nogBruikbaar;
    
    public Tunnel() {
        super(); // een tunnel is een vakje met 0 punten
        nogBruikbaar = true;
    }
    
    public void betreed() {
        if (isAlBetreden()) {
            nogBruikbaar = false;
        }
        else {
            super.betreed();
        }
    }

    public boolean isNogBruikbaar() {
        return nogBruikbaar;
    }
    
    public boolean isBetreedbaar() {
        return isNogBruikbaar();
    }    
}
