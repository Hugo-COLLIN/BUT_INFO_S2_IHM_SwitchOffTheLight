package example.switchOffTheLight.vue;

import example.switchOffTheLight.modele.GridModel;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{
    //Attributes
    GridModel model;
    GridView gridView;

    //Constructor
    public MainView (GridModel m)
    {
        this.model = m;
        this.gridView = new GridView(m);
        this.setCenter(this.gridView);
    }

    //Methods
    public GridView getGridView() {
        return this.gridView;
    }
}
