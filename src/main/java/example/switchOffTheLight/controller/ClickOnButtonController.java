package example.switchOffTheLight.controller;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class ClickOnButtonController implements EventHandler<ActionEvent> {

    /*
        Le controller est celui qui modifie les valeurs dans le
        modele et mets a jour la vue afin de voir le changement

        Le controller implements toujours EventHandler<>
        le type peut rester inconnu mais il est conseille
        de mettre le type qu'on recupere dans mon cas
        c'est un MouseEvent
     */


    GameModel model;
    MainView view;
    String action;
    int nbCases;


    public ClickOnButtonController(GameModel model, MainView view, String action, TextField lightF) {
        this.model = model;
        this.view = view;
        this.action = action;

        try {
            //this.model.setLightsFieldInt(true);
            this.nbCases = Integer.parseInt(lightF.getText());
        }
        catch (NumberFormatException e)
        {
            //this.model.setLightsFieldInt(false);
            this.nbCases = -1;
        }
    }

    @Override
    public void handle(ActionEvent mouseEvent) {
        this.model.buttonTriggered(this.action);
        this.view.update();
    }
}
