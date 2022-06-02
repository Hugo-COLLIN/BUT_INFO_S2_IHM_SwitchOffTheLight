package com.example.switchOffTheLight.controller;

import com.example.switchOffTheLight.model.GridModel;
import com.example.switchOffTheLight.view.GridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickOnButtonController implements EventHandler<MouseEvent>
{
    private final GridModel model;
    private final GridView vue;

    int btnX, btnY;

    public ClickOnButtonController(GridModel m, GridView v, int x, int y)
    {
        this.model = m;
        this.vue = v;
        this.btnX = x;
        this.btnY = y;
    }

    @Override
    public void handle(MouseEvent e) {
        if (e.isPrimaryButtonDown()) {
            this.model.clickOnLight(this.btnX, this.btnY);
            //System.out.println(e.getX() + "\n" + e.getY());
            this.vue.update();
        }
    }
}
