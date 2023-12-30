package be.inf1.doolhof.view;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SpelerView extends Region {

    public SpelerView(int x, int y, int size) {
        Circle c = new Circle(x*size + size/2, y*size + size/2, size/3.0);
        c.setFill(Color.BLUE);
        getChildren().addAll(c);
    }
}
