package com.example.switchOffTheLight.controller;

import com.example.switchOffTheLight.model.GridModel;
import com.example.switchOffTheLight.view.GraphicalView;
import com.example.switchOffTheLight.view.GridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickOnGridController implements EventHandler<MouseEvent>
{
    private final GridModel model;
    private final GridView vue;

    public ClickOnGridController (GridModel m, GridView v)
    {
        this.model = m;
        this.vue = v;
    }

    @Override
    public void handle(MouseEvent e) {

        this.model.clickOnLight((int) (e.getX() / GridView.TAILLE_CASE), (int) (e.getY() / GridView.TAILLE_CASE));
        System.out.println(e.getX() + "\n" + e.getY());
        this.vue.update();

        //if (e.isPrimaryButtonDown()) {

        //}
    }
}
