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

/**
 * Sub-view containing the grid and the winning screen
 * @author Hugo COLLIN, 09/06/2022
 */
public class BoardView extends StackPane
{
    //Attributes
    /**
     * Size for lights represented by Rectangle
     */
    public static final int BLOCK_SIZE = 75;

    /**
     * Model used by the view
     */
    GameModel model;

    /**
     * Parent view of this
     */
    MainView mainView;

    /**
     * List of rectangles corresponding to the grid representation
     */
    Rectangle[][] rectList;

    /**
     * Pane shown when the player win
     */
    StackPane winPane;


    //Constructor

    /**
     * Constructor that uses a model and a main view to build a BoardView sub-view
     * @param model model used to build and update this view
     * @param mainView main view used to build and update this view
     */
    public BoardView(GameModel model, MainView mainView)
    {
        //int count = GridModel.LENGTH_X;
        this.model = model;
        this.mainView = mainView;
        this.rectList = new Rectangle[GameModel.LENGTH_X][GameModel.LENGTH_Y];

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        this.makeGamePanel();

    }

    /**
     * Call methods to build the game panel : first the grid, then the ending screen
     */
    public void makeGamePanel ()
    {
        this.makeGridLigths();
        this.makeWinScreen();
    }

    /**
     * Create a grid composed of rectangles which represents the model's lights
     */
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

    /**
     * Create a winning screen with a rectangle and a Text in a StackPane
     */
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

        this.getChildren().add(winPane);
    }

    /**
     * Method that enable to create a rectangle for the grid of lights
     * @param x width position in the grid
     * @param y height position in the grid
     * @return a rectangle (object of type Rectangle)
     */
    public Rectangle makeRect (int x, int y)
    {
        Rectangle rect = updateRectFill(
                new Rectangle(BLOCK_SIZE, BLOCK_SIZE),
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

    /**
     * Set the background color of the rectangle according the state
     * of the corresponding light in the model
     * @param rectP rectangle from the view
     * @param lightModel light representation from the model
     * @return a rectangle good-colored according his light state in the model
     */
    public Rectangle updateRectFill (Rectangle rectP, boolean lightModel)
    {
        if (lightModel)
            rectP.setFill(Color.LIGHTGREEN);
        else
            rectP.setFill(Color.DARKGREEN);

        return rectP;
    }

    /**
     * Called each time the player executes an action on the interface
     * Show the winning screen if the player wins
     * Then, set the color of each rectangle according to his corresponding state in the model
     */
    public void update ()
    {
        this.winPane.setVisible(this.model.getWinState());

        for (int i = 0; i < GameModel.LENGTH_X ; i ++)
            for (int j = 0; j < GameModel.LENGTH_Y ; j ++)
                this.updateRectFill(this.rectList[i][j], this.model.getLight(i, j));
    }
}
