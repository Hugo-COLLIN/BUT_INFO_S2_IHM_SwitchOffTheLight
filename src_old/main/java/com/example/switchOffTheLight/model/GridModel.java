package com.example.switchOffTheLight.model;

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
}
