package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnButtonController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class MenuView extends HBox
{
    GameModel model;

    MainView mainView;

    ArrayList<Button> btnList;

    public MenuView (GameModel model, MainView mainView)
    {
        this.model = model;
        this.mainView = mainView;
        this.btnList = new ArrayList<>();
        this.makeMenu();
    }

    public void makeMenu()
    {
        this.setPadding(new Insets(10));
        this.setWidth(100);

        VBox menu = new VBox(5);
        menu.setAlignment(Pos.TOP_CENTER);

        for (String action : GameModel.ACTION_LIST)
        {
            Button b = new Button(action);
            b.setPrefWidth(this.getWidth());

            b.setOnAction(new ClickOnButtonController(this.model, this.mainView, action));

            this.btnList.add(b);
            menu.getChildren().add(b);
        }
        this.getChildren().add(menu);

        this.update();
    }

    public void update()
    {
        if (this.model.getMode().equals(GameModel.EXIT))
            System.exit(0);

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
