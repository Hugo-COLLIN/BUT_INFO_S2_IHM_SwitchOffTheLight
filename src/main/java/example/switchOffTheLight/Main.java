package example.switchOffTheLight;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class that launch the application
 * @author Hugo COLLIN, 09/06/2022
 */
public class Main extends Application {

    /**
     * Main
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Build the scene using a model and a view
     */
    @Override
    public void start(Stage stage) {
        GameModel model = new GameModel();
        MainView vue = new MainView(model);
        Scene scene = new Scene(vue);

        stage.setScene(scene);
        stage.setTitle("Éteins la lumière - Hugo COLLIN");
        stage.getIcons().add(new Image("file:icon/icon.png"));
        stage.show();
    }
}
