package example.switchOffTheLight.view;

import example.switchOffTheLight.model.GameModel;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{
    //Attributes
    GameModel model;
    BoardView boardView;
    MenuView menuView;

    //Constructor
    public MainView (GameModel m)
    {
        this.model = m;

        this.boardView = new BoardView(m, this);
        this.setCenter(this.boardView);

        this.menuView = new MenuView(m, this);
        this.setRight(menuView);
    }

    //Methods
    public void update()
    {
        this.boardView.update();
        this.menuView.update();
    }

    public BoardView getGridView() {
        return this.boardView;
    }
}
