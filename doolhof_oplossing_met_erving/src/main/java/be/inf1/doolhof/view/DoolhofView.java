
package be.inf1.doolhof.view;

import be.inf1.doolhof.model.Doolhof;
import be.inf1.doolhof.model.Tunnel;
import be.inf1.doolhof.model.Vakje;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * @author Kris Aerts & Wouter Groeneveld
 */
public class DoolhofView extends Region {

    private Doolhof model;
    public static final int SIZE = 40;

    public DoolhofView(Doolhof doolhofModel) {
        model = doolhofModel;
        update();
    }
    
    public void update() {
        getChildren().clear();
        int breedte = model.getBreedte();
        int hoogte = model.getHoogte();

        Rectangle rechthoek = new Rectangle(0, 0, breedte* SIZE, hoogte* SIZE);
        rechthoek.setFill(Color.gray(0.9));
        getChildren().addAll(rechthoek);

        for (int i=0; i<breedte; i++) {
            for (int j=0; j<hoogte; j++) {
                Node vakjeView = getVakjeViewOp(i, j);
                getChildren().add(vakjeView);
            }
        }

        paintPuntenTekst(hoogte);
                        
        getChildren().add(new SpelerView(model.getX(), model.getY(), SIZE));

        if (!model.kanBewegen()) {
            gameOver();
        }
    }

    private Node getVakjeViewOp(int x, int y) {
        Vakje vakje = model.getVakjeOp(x, y);
        if (vakje instanceof Tunnel) {
           return new TunnelView(vakje, x, y); 
        }
        else {
            return new VakjeView(vakje, x, y);
        }
    }

    // een methode zoals hieronder mag wél in de view, maar NOOIT in het model
    private void gameOver() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("game over");
        alert.setContentText("Je bent zo dood als ne ... \nDruk op spatiebalk om opnieuw te beginnen!");
        alert.showAndWait();
    }

    private void paintPuntenTekst(int hoogte) {
        Text puntenTekst = new Text(0, (hoogte+1)* SIZE,
                        model.getStappen() + " stappen om " + model.getAantalBehaald()
                        + " van de " + model.getAantal()
                        + " punten te verzamelen");
        getChildren().addAll(puntenTekst);
    }

}
