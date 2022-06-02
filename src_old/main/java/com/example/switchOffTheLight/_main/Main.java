package com.example.switchOffTheLight._main;

import com.example.switchOffTheLight.controller.ClickOnGridController;
import com.example.switchOffTheLight.model.GridModel;
import com.example.switchOffTheLight.view.GraphicalView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GridModel model = new GridModel();
        GraphicalView vue = new GraphicalView();
        Scene scene = new Scene(vue);

        vue.getGridView().setOnMouseClicked(new ClickOnGridController(model, vue.getGridView()));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}