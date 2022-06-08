package example.switchOffTheLight;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        GameModel model = new GameModel();
        MainView vue = new MainView(model);
        Scene scene = new Scene(vue);

        stage.setScene(scene);
        stage.show();
    }
}
