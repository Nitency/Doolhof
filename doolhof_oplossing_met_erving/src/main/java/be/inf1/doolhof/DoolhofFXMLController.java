/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.inf1.doolhof;

import be.inf1.doolhof.model.Doolhof;
import be.inf1.doolhof.view.DoolhofView;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author u0002531
 */
public class DoolhofFXMLController {
    private Doolhof model;
    private DoolhofView view;

    @FXML
    private AnchorPane paneel;
    
    @FXML
    public void initialize()
    {
        model = new Doolhof(10,10,8);
        view  = new DoolhofView(model);

        paneel.getChildren().add(view);
        paneel.setOnKeyPressed(this::keyPressed);
        paneel.setFocusTraversable(true);
    }

    private void keyPressed(KeyEvent ev) {
        switch (ev.getCode()) {
            case UP:
                model.boven();
                break;
            case DOWN:
                model.onder();
                break;
            case LEFT:
                model.links();
                break;
            case RIGHT:
                model.rechts();
                break;
            case SPACE:
                model.herstart();
        }

        update();
    }
    
    public void update() {
        view.update();
    }

}
