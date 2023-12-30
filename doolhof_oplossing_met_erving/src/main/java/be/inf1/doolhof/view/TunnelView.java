/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.doolhof.view;

import be.inf1.doolhof.model.Tunnel;
import be.inf1.doolhof.model.Vakje;
import javafx.scene.paint.Color;

/**
 *
 * @author u0002531
 */
public class TunnelView extends VakjeView  {
    public TunnelView(Vakje model, int x, int y) {
        super(model, x, y);
    }
    
    public Color getColorVoorVakje() {
        Vakje model = getModel();
        if (model instanceof Tunnel) {
            Tunnel t = (Tunnel) model;
           if (!t.isAlBetreden()) {
               return Color.GREEN; 
           }
           else if (t.isNogBruikbaar()) {
               return Color.ORANGE;
           }
           else {
               return Color.RED;
           }
        }
        else {
            return super.getColorVoorVakje();
        }
    }
}
