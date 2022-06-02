package example.switchOffTheLight.vue;

import example.switchOffTheLight.modele.GridModel;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{
    //Attributes
    GridModel model;
    GridView gridView;
    MenuView menuView;

    //Constructor
    public MainView (GridModel m)
    {
        this.model = m;

        this.gridView = new GridView(m);
        this.setCenter(this.gridView);

        this.menuView = new MenuView(m);
        this.setBottom(menuView);
    }

    //Methods
    public GridView getGridView() {
        return this.gridView;
    }
}
