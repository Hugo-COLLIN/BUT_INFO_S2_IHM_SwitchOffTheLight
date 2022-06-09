package example.switchOffTheLight.view;

import example.switchOffTheLight.model.GameModel;
import javafx.scene.layout.BorderPane;

/**
 * Global view containing the board and the menu
 * @author Hugo COLLIN, 09/06/2022
 */
public class MainView extends BorderPane
{
    //Attributes
    /**
     * Model used by the view
     */
    GameModel model;

    /**
     * BoardView : Sub-view contained in this view, corresponding to the grid view
     */
    BoardView boardView;

    /**
     * MenuView : Sub-view contained in this view, corresponding to the button menu
     */
    MenuView menuView;


    //Constructor
    /**
     * Constructor that uses a model to build a view composed of a BoardView and a MenuView
     * @param m model used to build and update the view
     */
    public MainView (GameModel m)
    {
        this.model = m;

        this.boardView = new BoardView(m, this);
        this.setCenter(this.boardView);

        this.menuView = new MenuView(m, this);
        this.setRight(menuView);
    }


    //Method
    /**
     * Call update methods of each sub-view
     */
    public void update()
    {
        this.boardView.update();
        this.menuView.update();
    }
}
