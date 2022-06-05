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

    /*
        Le start peut etre commencee a partir
        du moment ou le modele et la vue sont faites
        le controller c'est apres pour les actions
        mais le principale c'est le visuel de base

        De preference faire le modele avant la vue
        la vue avant le main
        le main avant le controller
        puis le controller
     */

    @Override
    public void start(Stage stage) {
        GameModel model = new GameModel();
        MainView vue = new MainView(model);
        Scene scene = new Scene(vue);

        //vue.getGridView().setOnMouseClicked(new ClickOnGridController(model, vue.getGridView()));

        stage.setScene(scene);
        stage.show();

        /*
        GridModel modele = new GridModel(GridModel.MAX_VALUE/2, GridModel.MAX_VALUE/2 );
        MainView vue = new MainView(modele);
        Scene scene = new Scene(vue, MainView.TAILLE_FEN, MainView.TAILLE_FEN);
        primaryStage.setScene(scene);
        primaryStage.show();

         */
    }
}
