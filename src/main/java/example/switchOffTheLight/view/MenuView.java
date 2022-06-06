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

public class MenuView extends HBox
{
    GameModel model;

    MainView mainView;

    ArrayList<Button> btnList;

    Text win;

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

        this.win.setVisible(this.model.getWinState());

        for (int i = 0 ; i < this.btnList.size() ; i ++)
            this.btnList.get(i).setDisable(!this.model.getBtnState(i));
    }
}
