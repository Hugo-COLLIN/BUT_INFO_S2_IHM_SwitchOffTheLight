package example.switchOffTheLight.vue;

import example.switchOffTheLight.modele.GridModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MainView extends BorderPane {

    /*
        La vue graphique est un Pane sachant que GridPane etc.. sont des panes la vue
        peut extends eux aussi. De ce fait ca fait que VueJeu est la representation
        de notre "root" qu'on faisait en debut d'ihm
     */

    //Attributes
    GridModel model;
    GridView gridView;

    //Constructor
    public MainView (GridModel m)
    {
        this.model = m;
        this.gridView = new GridView(m);
        this.setCenter(this.gridView);
    }


    public GridView getGridView() {
        return this.gridView;
    }

    /*
    // Taille de ma fenetre, possible d'avoir deux variables une pour longueur et une hauteur
    public static final double TAILLE_FEN = 600;

    // Permet de convertir mes valeurs dans le cercle en position en pixel pour la vue graphique
    public static final double CONVERTION_PIXEL = TAILLE_FEN / GridModel.MAX_VALUE;

    // Modele pour savoir les donnees
    GridModel modele;

    // Donnee graphique convertion du modele en graphique
    Circle cercle;

    /*
        Prend souvent un modele en parametre
        mais peut etre cree en interne
     *//*
    public VueJeu(GridModel modele) {
        this.modele = modele;

        // Methode pas obligatoire juste pour la structure
        initVue();
        // Remplacable par le code qu'elle contient
    }
    private void initVue() {
        // Recupere les positions de mon cercle non graphique
        double[] pos = modele.getPosition();

        // Cree mon cercle graphique
        cercle = new Circle(
                pos[0] * CONVERTION_PIXEL, // Convertion de la position x en pixel
                pos[1] * CONVERTION_PIXEL, // Convertion de la position y en pixel
                25 // Le radius du cercle
        );

        /*
            Dans mon cas je dois cree le controller dans la vue
            de ce fait le parametre de la vue c'est "this"
         *//*
        cercle.setOnMouseEntered(new CursorIsEntered(modele, this));

        cercle.setFill(Color.BLUEVIOLET);

        this.getChildren().add(cercle);
    }

    // Met a jour le valeur du cercle graphique
    public void update() {
        double[] pos = modele.getPosition();

        cercle.setCenterX(pos[0] * CONVERTION_PIXEL);
        cercle.setCenterY(pos[1] * CONVERTION_PIXEL);
        cercle.setRadius(modele.getRadius());
    }*/
}
