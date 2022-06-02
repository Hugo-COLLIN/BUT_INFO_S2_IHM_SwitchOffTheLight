package com.example.switchOffTheLight.view;

import com.example.switchOffTheLight.model.GridModel;
import javafx.scene.layout.BorderPane;

public class GraphicalView extends BorderPane
{
    //Attributes
    GridModel model;
    GridView gridView;

    //Constructor
    public GraphicalView ()
    {
        this.model = new GridModel();
        this.gridView = new GridView();
        this.setCenter(this.gridView);
    }


    public GridView getGridView() {
        return this.gridView;
    }
}
