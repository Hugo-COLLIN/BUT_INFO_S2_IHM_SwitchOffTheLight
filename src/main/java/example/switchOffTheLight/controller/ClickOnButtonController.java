package example.switchOffTheLight.controller;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 * Contoller actionned when a button is clicked in the menu
 * @author Hugo COLLIN, 09/06/2022
 */
public class ClickOnButtonController implements EventHandler<ActionEvent>
{
    //Attributes
    /**
     * Model used by the controller
     */
    GameModel model;

    /**
     * View used by the controller
     */
    MainView view;

    /**
     * Action of the button the player triggered
     */
    String action;


    //Constructor
    /**
     * Constructor that takes a model, a view and an action
     * @param model model that will be modified by the controller
     * @param mainView view which the action come from
     * @param action action from the button clicked by the player
     */
    public ClickOnButtonController(GameModel model, MainView mainView, String action) {
        this.model = model;
        this.view = mainView;
        this.action = action;
    }

    //Method
    /**
     * Update the model with its corresponding method, then update the view based on the model
     * @param mouseEvent mouse event
     */
    @Override
    public void handle(ActionEvent mouseEvent) {
        this.model.btnTriggered(this.action);
        this.view.update();
    }
}
