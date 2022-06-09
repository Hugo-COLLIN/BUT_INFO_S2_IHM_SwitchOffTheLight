package example.switchOffTheLight.controller;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Contoller actionned when a light (=rectangle) is clicked on the grid
 * @author Hugo COLLIN, 09/06/2022
 */
public class ClickOnLightController implements EventHandler<MouseEvent>
{
    //Attributes
    /**
     * Model used by the controller
     */
    GameModel model;

    /**
     * View used by the controller
     */
    MainView mainView;

    /**
     * Coordinates of the rectangle position corresponding to player's click
     */
    int btnX, btnY;

    //Constructor
    /**
     * Constructor that takes a model, a view and 2 coordinates x and y
     * @param model model that will be modified by the controller
     * @param mainView view which the action come from
     * @param x rectangle's width position corresponding to player's click
     * @param y rectangle's height position corresponding to player's click
     */
    public ClickOnLightController(GameModel model, MainView mainView, int x, int y) {
        this.model = model;
        this.mainView = mainView;
        this.btnX = x;
        this.btnY = y;
    }

    //Methdod
    /**
     * Update the model with its corresponding method, then update the view based on the model
     * @param mouseEvent mouse event
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        this.model.clickOnLight(this.btnX, this.btnY);
        this.mainView.update();
    }
}
