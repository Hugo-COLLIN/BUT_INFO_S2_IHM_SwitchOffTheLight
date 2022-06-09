package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnLightController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BoardView extends StackPane
{
    public static final int TAILLE_CASE = 75;

    GameModel model;

    MainView mainView;

    Rectangle[][] rectList;

    StackPane winPane;
    //StackPane container;

    public BoardView(GameModel m, MainView v)
    {
        //int count = GridModel.LENGTH_X;
        this.model = m;
        this.mainView = v;
        this.rectList = new Rectangle[GameModel.LENGTH_X][GameModel.LENGTH_Y];

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        this.makeGamePanel();

    }

    public void makeGamePanel ()
    {
        this.makeGridLigths();
        this.makeWinScreen();
    }

    public void makeGridLigths ()
    {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        grid.setVgap(5);

        for (int i = 0; i < GameModel.LENGTH_X; i ++)
            for (int j = 0; j < GameModel.LENGTH_Y; j ++)
                grid.add(makeRect(i, j), i, j);

        this.getChildren().add(grid);
    }

    public void makeWinScreen ()
    {
        winPane = new StackPane();

        Rectangle back = new Rectangle(240, 140);
        back.setFill(Color.ORANGE);
        back.setStroke(Color.DARKORANGE);
        back.setStrokeWidth(5);
        back.setStyle("-fx-border-radius: 20px;");

        Text winText = new Text(GameModel.WIN);
        winText.setLineSpacing(10.0);
        winText.setStyle("-fx-font-size: xx-large;" +
                "-fx-text-alignment: center;"
        );

        winPane.setVisible(false);
        winPane.getChildren().addAll(back, winText);











        /*
        winPane.setStyle("-fx-background-color: white");
        winPane.setStyle(
                "-fx-translate-x: 10em;" +
                        "-fx-translate-y: 10em;"
        );

        winPane.setMaxSize(0, 0);
        winPane.setVisible(false);






        Text win = new Text(GameModel.WIN);
        win.setFill(Color.RED);
        win.setStyle("-fx-font-size: large;" +
                "-fx-font-weight: bold;"
        );
        winPane.getChildren().addAll(back, win);
    */

        this.getChildren().add(winPane);
    }

    public Rectangle makeRect (int x, int y)
    {
        Rectangle rect = updateRectFill(
                new Rectangle(TAILLE_CASE, TAILLE_CASE),
                this.model.getLight(x,y)
        );
        rect.setArcHeight(15);
        rect.setArcWidth(15);

        this.rectList[x][y] = rect;

        rect.setOnMouseClicked(
                new ClickOnLightController(this.model, this.mainView, x, y)
        );

        return rect;

    }

    public Rectangle updateRectFill (Rectangle rectP, boolean lightModel)
    {
        if (lightModel)
            rectP.setFill(Color.LIGHTGREEN);
        else
            rectP.setFill(Color.DARKGREEN);

        return rectP;
    }

    public void update ()
    {
        this.winPane.setVisible(this.model.getWinState());
        /*
        if (this.model.getWinState())
        {
            this.winPane.setVisible(this.model.getWinState());
            //this.winPane.getChildren().get(0).set
        }
        else
        {
            this.winPane.setVisible(this.model.getWinState());
            this.winPane.setMaxSize(0, 0);
        }

         */

        for (int i = 0; i < GameModel.LENGTH_X ; i ++)
            for (int j = 0; j < GameModel.LENGTH_Y ; j ++)
            {
                this.updateRectFill(this.rectList[i][j], this.model.getLight(i, j));
            }

    }
}
