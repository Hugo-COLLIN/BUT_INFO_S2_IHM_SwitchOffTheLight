package example.switchOffTheLight.controller;

import example.switchOffTheLight.modele.GridModel;
import example.switchOffTheLight.vue.GridView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ClickOnButtonController implements EventHandler<MouseEvent> {

    /*
        Le controller est celui qui modifie les valeurs dans le
        modele et mets a jour la vue afin de voir le changement

        Le controller implements toujours EventHandler<>
        le type peut rester inconnu mais il est conseille
        de mettre le type qu'on recupere dans mon cas
        c'est un MouseEvent
     */


    GridModel model;
    GridView vue;

    int btnX, btnY;

    /*
        Prend souvent en parametre modele et vue car pas
        souvent cree en interne
     */
    public ClickOnButtonController(GridModel model, GridView vue, int x, int y) {
        this.model = model;
        this.vue = vue;
        this.btnX = x;
        this.btnY = y;
    }

    /*
        Methode obligatoire a cause de l'implementation
     */
    @Override
    public void handle(MouseEvent mouseEvent) {
        this.model.clickOnLight(this.btnX, this.btnY);
        this.vue.update();
        /*
        model.deplacer(); // Deplace le cercle non graphique
        model.agrandir(); // Agrandit le cercle
        vue.update(); // Mets a jour la vue par consequent deplace et agrandit le cercle graphique
                 */
    }
}
