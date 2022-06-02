package example.switchOffTheLight.modele;

import java.util.Random;

public class GridModel
{
    boolean [][] lights;

    public static final int LENGTH_X = 4;
    public static final int LENGTH_Y = 3;

    public GridModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];

        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;
    }

    public void clickOnLight (int x, int y)
    {
        invert(x,y);
        invert(x - 1, y);
        invert(x + 1, y);
        invert(x, y - 1);
        invert(x, y + 1);
    }

    public void invert (int x, int y)
    {
        if (inGrid(x, y))
            this.lights[x][y] = !this.lights[x][y];
    }

    public boolean inGrid (int x, int y)
    {
        return x >= 0 && x < LENGTH_X && y >= 0 && y < LENGTH_Y;
    }

    //Getter
    public boolean getLight(int x, int y) {
        return this.lights[x][y];
    }
    /*

        Le modele dans mon cas est un cercle
        il ne contient que des valeurs brutes
        ne representant rien de graphique
        aucun import de javafx
        il ne connait personne pour ce qui est controller
        ou vue


     */

    /*
    // RNG pour avoir des valeurs aleatoire pour les positions
    public static final Random RNG = new Random();

    // Value maximal en double
    public static final double MAX_VALUE = 10;

    // Position de mon cercle
    double x, y;
    int radius;

    // Initialise les positions
    public GridModel(double initX, double initY) {
        // Permet d'eviter les depassement tels que 20
        this.x = Math.min(initX, MAX_VALUE);
        this.y = Math.min(initY, MAX_VALUE);
        this.radius = 25;
    }

    // Deplace aleatoirement les positions du cercle
    public void deplacer() {
        this.x = RNG.nextDouble() * MAX_VALUE;
        this.y = RNG.nextDouble() * MAX_VALUE;
    }

    // Agrandit le cerlce
    public void agrandir() {
        this.radius += 5;
    }

    // Permet d'obtenir les positions du cercle
    public double[] getPosition() {
        return new double[]{x, y};
    }

    public int getRadius() {
        return this.radius;
    }
         */
}
