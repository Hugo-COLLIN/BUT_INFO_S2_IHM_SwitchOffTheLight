package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnButtonController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MenuView extends HBox
{
    GameModel model;

    MainView mainView;

    ArrayList<Button> btnList;

    Text win, clicksField, errNumberText;

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

        //TextField for button controller

        //TextField for button controller
        TextField lightsField = new TextField();
        lightsField.setPrefWidth(this.getWidth() / 2);

        VBox menu = new VBox(5);
        menu.setAlignment(Pos.TOP_CENTER);

        //Side action buttons
        for (String action : GameModel.ACTION_LIST)
        {
            Button b = new Button(action);
            b.setOnAction(new ClickOnButtonController(this.model, this.mainView, action, lightsField));

            b.setPrefWidth(this.getWidth());

            this.btnList.add(b);
            menu.getChildren().add(b);

            if (action.equals(GameModel.PLAY))
            {
                HBox hb = new HBox(5);
                Text clicksText = new Text("Nombre de clics : ");
                clicksField = new Text();

                hb.getChildren().addAll(clicksText, clicksField);
                menu.getChildren().add(hb);
            }
            if (action.equals(GameModel.CONF))
            {
                HBox hb = new HBox(5);
                Text lightsText = new Text("Cases : ");

                hb.getChildren().addAll(lightsText, lightsField);
                menu.getChildren().add(hb);

                errNumberText = new Text("Nombre saisi incorrect.");
                errNumberText.setVisible(false);
                menu.getChildren().add(errNumberText);
            }
        }
        win = new Text("Vous avez gagné !");
        win.setVisible(false);
        menu.getChildren().add(win);

        this.getChildren().add(menu);
        this.update();
    }

    public void update()
    {
        if (this.model.getMode().equals(GameModel.EXIT))
            System.exit(0);

        clicksField.setText(String.valueOf(this.model.getNbClicks()));
        this.win.setVisible(this.model.getWinState());
        this.errNumberText.setVisible(!this.model.getLightsFieldInt());

        for (int i = 0 ; i < this.btnList.size() ; i ++)
            this.btnList.get(i).setDisable(!this.model.getBtnState(i));
    }
}
