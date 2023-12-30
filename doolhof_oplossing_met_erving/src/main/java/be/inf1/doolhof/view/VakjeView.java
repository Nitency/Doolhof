package be.inf1.doolhof.view;

import be.inf1.doolhof.model.Vakje;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class VakjeView extends Region {

    private Vakje vakjeModel;

    public VakjeView(Vakje vakjeModel, int x, int y) {
        this.vakjeModel = vakjeModel;

        int size = DoolhofView.SIZE;
        Rectangle rechthoek = new Rectangle(x * size+1, y * size+1, size-2, size-2);
        rechthoek.setFill(getColorVoorVakje());
        getChildren().add(rechthoek);

        int ptn = vakjeModel.getPunten();
        if (ptn > 0) {
            Text t = new Text(x*size+size/3, (y+1)*size-size/3, "" + ptn);
            getChildren().add(t);
        }
    }

    public Vakje getModel() {
        return vakjeModel;
    }

    public Color getColorVoorVakje() {
        if (vakjeModel == null) {
            return Color.BEIGE;
        }
        else if (vakjeModel.isBetreedbaar()) {
            return Color.WHITE;
        }
        else {
            return Color.RED;
        }
    }
}
