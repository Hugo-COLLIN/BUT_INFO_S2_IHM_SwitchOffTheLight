package example.switchOffTheLight.view;

import example.switchOffTheLight.controller.ClickOnLightController;
import example.switchOffTheLight.model.GameModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GridView extends GridPane
{
    public static final int TAILLE_CASE = 75;

    GameModel model;

    MainView mainView;

    Rectangle[][] rectList;

    Pane winPane;

    public GridView(GameModel m, MainView v)
    {
        //int count = GridModel.LENGTH_X;
        this.model = m;
        this.mainView = v;
        this.rectList = new Rectangle[GameModel.LENGTH_X][GameModel.LENGTH_Y];

        //for (int i = 0 ; i < count ; i ++)
        //    this.rectList.add(new ArrayList<>());

        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        this.makeGridLigths();
        this.makeWinScreen();
    }

    public void makeGridLigths ()
    {
        for (int i = 0; i < GameModel.LENGTH_X; i ++)
            for (int j = 0; j < GameModel.LENGTH_Y; j ++)
                makeRect(i, j);
    }

    public void makeWinScreen ()
    {
        winPane = new Pane();
        winPane.setStyle("-fx-background-color: white");

        winPane.setMaxSize(0, 0);
        winPane.setVisible(false);

        Text win = new Text(GameModel.WIN);
        win.setFill(Color.RED);
        win.setStyle("-fx-font-size: large;" +
                "-fx-font-weight: bold;" +
                "-fx-translate-x: -17em;" +
                "-fx-translate-y: -15em"
        );        winPane.getChildren().add(win);

        this.add(winPane, 5, 5);
    }

    public void makeRect (int x, int y)
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

        this.add(rect, x, y);

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
        if (this.model.getWinState())
        {
            this.winPane.setVisible(this.model.getWinState());
            this.winPane.setMaxSize(00, 00);
        }
        else
        {
            this.winPane.setVisible(this.model.getWinState());
            this.winPane.setMaxSize(0, 0);
        }

        for (int i = 0; i < GameModel.LENGTH_X ; i ++)
            for (int j = 0; j < GameModel.LENGTH_Y ; j ++)
            {
                this.updateRectFill(this.rectList[i][j], this.model.getLight(i, j));
            }

    }
}
