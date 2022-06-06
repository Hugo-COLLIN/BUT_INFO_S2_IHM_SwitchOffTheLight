package example.switchOffTheLight.controller;

import example.switchOffTheLight.model.GameModel;
import example.switchOffTheLight.view.MainView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickOnLightController implements EventHandler<MouseEvent> {

    /*
        Le controller est celui qui modifie les valeurs dans le
        modele et mets a jour la vue afin de voir le changement

        Le controller implements toujours EventHandler<>
        le type peut rester inconnu mais il est conseille
        de mettre le type qu'on recupere dans mon cas
        c'est un MouseEvent
     */


    GameModel model;
    MainView mainView;

    int btnX, btnY;

    /*
        Prend souvent en parametre modele et vue car pas
        souvent cree en interne
     */
    public ClickOnLightController(GameModel model, MainView mainView, int x, int y) {
        this.model = model;
        this.mainView = mainView;
        this.btnX = x;
        this.btnY = y;
    }

    /*
        Methode obligatoire a cause de l'implementation
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        this.model.clickOnLight(this.btnX, this.btnY);
        this.mainView.update();
        /*
        model.deplacer(); // Deplace le cercle non graphique
        model.agrandir(); // Agrandit le cercle
        vue.update(); // Mets a jour la vue par consequent deplace et agrandit le cercle graphique
                 */
    }
}
