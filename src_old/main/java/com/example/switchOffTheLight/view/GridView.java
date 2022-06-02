package com.example.switchOffTheLight.view;

import com.example.switchOffTheLight.controller.ClickOnGridController;
import com.example.switchOffTheLight.model.GridModel;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GridView extends GridPane
{
    public static final int TAILLE_CASE = 75;

    GridModel model;

    ArrayList<Rectangle> rectList;

    public GridView()
    {
        //int count = GridModel.LENGTH_X;
        this.model = new GridModel();
        this.rectList = new ArrayList<>();

        //for (int i = 0 ; i < count ; i ++)
        //    this.rectList.add(new ArrayList<>());

        this.setHgap(5);
        this.setVgap(5);
        this.setPadding(new Insets(5));

        makeGridLigths();
    }

    public void makeGridLigths ()
    {
        for (int i = 0; i < GridModel.LENGTH_X; i ++)
            for (int j = 0; j < GridModel.LENGTH_Y; j ++)
                makeRect(i, j);
    }

    public void makeRect (int x, int y)
    {
        Rectangle rect = updateRectFill(
                new Rectangle(TAILLE_CASE, TAILLE_CASE),
                this.model.getLight(x,y)
        );
        rect.setArcHeight(15);
        rect.setArcWidth(15);

        this.rectList.add(rect);

        //this.setOnMouseClicked(new ClickOnGridController(model, this));

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
        for (int i = 0 ; i < GridModel.LENGTH_Y ; i ++)
            for (int j = 0 ; j < GridModel.LENGTH_X ; j ++)
            {
                int tmp1 = i * GridModel.LENGTH_X + j;
                this.updateRectFill(this.rectList.get(i * GridModel.LENGTH_X + j), this.model.getLight(i, j));
                System.out.println(i + "\n" + j + "\n" + tmp1);
            }

    }


}
