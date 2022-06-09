package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnButtonController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Sub-view containing the the buttons and the texts
 * @author Hugo COLLIN, 09/06/2022
 */
public class MenuView extends HBox
{
    //Attributes
    /**
     * Model used by the view
     */
    GameModel model;

    /**
     * Parent view of this
     */
    MainView mainView;

    /**
     * List containing all the buttons on the interface
     */
    ArrayList<Button> btnList;

    /**
     * Clicks' counter's corresponding texts
     */
    Text clicksText, clicksCounter;


    //Constructor
    /**
     * Constructor that uses a model and a main view to build a MenuView sub-view
     * @param model model used to build and update this view
     * @param mainView main view used to build and update this view
     */
    public MenuView (GameModel model, MainView mainView)
    {
        this.model = model;
        this.mainView = mainView;
        this.btnList = new ArrayList<>();
        this.makeMenu();
    }


    //Methods
    /**
     * Method that makes a menu with a click counter and the buttons
     * corresponding to the BTN_LIST list.
     */
    public void makeMenu()
    {
        this.setPadding(new Insets(10));
        this.setWidth(100);
        //this.setAlignment(Pos.CENTER);

        VBox menu = new VBox(5);
        menu.setAlignment(Pos.TOP_CENTER);
        menu.setPrefHeight(this.getHeight());

        // >Show the click counter
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);

        clicksText = new Text(GameModel.CLICKS);
        clicksText.setStyle(
                "-fx-font-weight: bold;" +
                        "-fx-font-size: 1.1em"
        );

        clicksCounter = new Text();
        clicksCounter.setStyle(clicksText.getStyle());

        hb.getChildren().addAll(clicksCounter, clicksText);
        menu.getChildren().add(hb);

        // >Make side action buttons
        for (String action : GameModel.BTN_LIST)
        {
            Button b = new Button(action);
            b.setOnAction(new ClickOnButtonController(this.model, this.mainView, action));

            b.setPrefWidth(80);
            b.setStyle(
                    "-fx-background-color: orange;" +
                    "-fx-border-color: darkorange;" +
                    "-fx-border-radius: 5px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-alignment: center;" +
                    "-fx-font-size: 1.1em;" +
                    "-fx-padding: 8px 0"
            );

            this.btnList.add(b);
            menu.getChildren().add(b);
        }

        this.getChildren().add(menu);
        this.update();
    }

    /**
     * Called each time the player executes an action on the interface
     * Update buttons state and execute the action corresponding to the model's mode
     */
    public void update()
    {
        if (this.model.getMode().equals(GameModel.EXIT))
            System.exit(0);

        //Counter view state
        clicksCounter.setText(String.valueOf(this.model.getNbClicksGridPlay()));
        if (this.model.getWinState() || this.model.getMode().equals(GameModel.PLAY))
        {
            this.clicksText.setVisible(true);
            this.clicksCounter.setVisible(true);
        }
        else
        {
            this.clicksText.setVisible(false);
            this.clicksCounter.setVisible(false);
        }
        this.btnList.get(1).setText(this.model.textTwoSidedBtn());


        for (int i = 0 ; i < this.btnList.size() ; i ++)
            this.btnList.get(i).setDisable(!this.model.getBtnState(i));
    }
}
