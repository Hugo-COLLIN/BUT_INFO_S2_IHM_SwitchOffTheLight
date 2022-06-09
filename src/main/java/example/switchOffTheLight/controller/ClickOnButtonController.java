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

    public ClickOnButtonController(GameModel model, MainView view, String action) {
        this.model = model;
        this.view = view;
        this.action = action;
    }

    @Override
    public void handle(ActionEvent mouseEvent) {
        this.model.btnTriggered(this.action);
        this.view.update();
    }
}
