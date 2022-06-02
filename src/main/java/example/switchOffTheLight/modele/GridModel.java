package example.switchOffTheLight.modele;

public class GridModel
{
    boolean [][] lights;

    boolean editorMode;

    public static final int LENGTH_X = 4;
    public static final int LENGTH_Y = 3;

    public GridModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];

        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;

        this.editorMode = true;
    }

    public void clickOnLight (int x, int y)
    {
        invert(x,y);

        //if (!this.editorMode)
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
}
