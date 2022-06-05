package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnButtonController;
import example.switchOffTheLight.model.GameModel;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class MenuView extends HBox
{
    GameModel model;

    ArrayList<Button> btnList;

    public MenuView (GameModel model)
    {
        this.model = model;
        this.btnList = new ArrayList<>();
        this.makeMenu();
    }

    public void makeMenu()
    {
        for (String action : GameModel.ACTION_LIST)
        {
            Button b = new Button(action);

            b.setOnAction(new ClickOnButtonController(this.model, this, action));

            this.btnList.add(b);
            //b.setOnMouseClicked(new ActionClicController(this.model, this));
            this.getChildren().add(b);
        }
        this.update();
    }

    public void update()
    {
        for (int i = 0 ; i < this.btnList.size() ; i ++)
            this.btnList.get(i).setDisable(!this.model.getBtnState(i));

        /*
        for (int i = 0 ; i < 3 ; i ++)
            if ()
            /*
            if (this.model.getModeN() == i)
                btnList.get(i).setDisable(btnState[i]);
            else
                btnList.get(i).setDisable(!btnState[i]);
            */


        /*
        if (this.model.getMode().equals(GameModel.EDIT))
        {
            btnList.get(0).setDisable(false);
            btnList.get(1).setDisable(true);
            btnList.get(2).setDisable(true);
        }
        else
        {
            btnList.get(0).setDisable(true);
            btnList.get(1).setDisable(false);
            btnList.get(2).setDisable(false);
        }
                */

    }
}