package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnButtonController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class MenuView extends HBox
{
    GameModel model;

    MainView mainView;

    ArrayList<Button> btnList;

    Text win, clicksField;

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
        //this.setAlignment(Pos.CENTER);

        VBox menu = new VBox(5);
        menu.setAlignment(Pos.CENTER);
        menu.setPrefHeight(this.getHeight());

        //Side action buttons
        for (String action : GameModel.BTN_LIST)
        {
            Button b = new Button(action);
            b.setOnAction(new ClickOnButtonController(this.model, this.mainView, action));

            //int n = b.getText().getC
            b.setPrefWidth(80);
            //b.setPrefHeight(this.getHeight() / 4);
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

            if (action.equals(GameModel.PLAY))
            {
                HBox hb = new HBox();
                hb.setAlignment(Pos.CENTER);

                Text clicksText = new Text(GameModel.CLICKS);
                clicksText.setStyle(
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 1.1em"
                );

                clicksField = new Text();
                clicksField.setStyle(clicksText.getStyle());

                hb.getChildren().addAll(clicksField, clicksText);
                menu.getChildren().add(hb);
            }
        }
        /*
        win = new Text(GameModel.WIN);
        win.setFill(Color.RED);
        win.setStyle("-fx-font-size: large;" +
                "-fx-font-weight: bold;" +
                "-fx-translate-x: -17em;" +
                "-fx-translate-y: -15em"
        );
        win.setVisible(false);
        menu.getChildren().add(win);

         */

        this.getChildren().add(menu);
        this.update();
    }

    public void update()
    {
        if (this.model.getMode().equals(GameModel.EXIT))
            System.exit(0);

        clicksField.setText(String.valueOf(this.model.getNbClicksGridPlay()));
        //this.win.setVisible(this.model.getWinState());
        this.btnList.get(1).setText(this.model.textTwoSidedBtn());

        for (int i = 0 ; i < this.btnList.size() ; i ++)
            this.btnList.get(i).setDisable(!this.model.getBtnState(i));
    }
}
