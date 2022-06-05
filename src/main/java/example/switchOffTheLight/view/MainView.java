package example.switchOffTheLight.view;

import example.switchOffTheLight.model.GameModel;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{
    //Attributes
    GameModel model;
    GridView gridView;
    MenuView menuView;

    //Constructor
    public MainView (GameModel m)
    {
        this.model = m;

        this.gridView = new GridView(m, this);
        this.setCenter(this.gridView);

        this.menuView = new MenuView(m);
        this.setBottom(menuView);
    }

    //Methods
    public void update()
    {
        this.gridView.update();
        this.menuView.update();
    }

    public GridView getGridView() {
        return this.gridView;
    }
}
