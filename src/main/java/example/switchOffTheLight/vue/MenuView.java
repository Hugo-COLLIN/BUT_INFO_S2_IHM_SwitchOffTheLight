package example.switchOffTheLight.vue;

import example.switchOffTheLight.modele.GridModel;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class MenuView extends HBox
{
    GridModel model;
    String [] actionList;

    public MenuView (GridModel model)
    {
        this.model = model;
        this.actionList = new String[]{"Jouer", "Configurer", "Arrêter", "Sortir"};
        this.makeMenu();
    }

    public void makeMenu()
    {
        for (String action : this.actionList)
        {
            Button b = new Button(action);
            //b.setOnMouseClicked(new ActionClicController(this.model, this));
            this.getChildren().add(b);
        }
    }
}
