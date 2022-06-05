package example.switchOffTheLight.model;

import java.util.HashMap;

public class GameModel
{
    private boolean [][] lights;

    private int modeN; //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode

    HashMap<String, Boolean> btnsStatus;

    private boolean
            isWin,
            editorMode,
            endGame,
            systemExit;

    private String mode;

    public static final int
            LENGTH_X = 4,
            LENGTH_Y = 3;

    public static final String
            PLAY = "Jouer",
            CONF = "Configurer",
            STOP = "Arrêter",
            EXIT = "Sortir";

    public static final String [] ACTION_LIST = {PLAY, CONF, STOP, EXIT};

    public GameModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];
        this.btnsStatus = new HashMap<>();

        boolean tmp = false;

        for (String action : ACTION_LIST)
        {
            btnsStatus.put(action, tmp);
            tmp = true;
        }

        System.out.println(btnsStatus);

        this.modeN = 2;
        this.isWin = false;

        this.initialGrid();



        //toDel
        this.editorMode = true;
        this.endGame = false;
        this.systemExit = false;
    }

    public void clickOnLight (int x, int y)
    {
        invert(x,y);

        if (this.modeN == 0)
        {
            invert(x - 1, y);
            invert(x + 1, y);
            invert(x, y - 1);
            invert(x, y + 1);
        }
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

    public void buttonTriggered(String action)
    {
        this.mode = action;

        switch (action)
        {
            case PLAY:
                this.modeN = 0;
                this.editorMode = false;
                this.endGame = false;
                break;
            case CONF:
                this.modeN = 1;
                this.editorMode = true;
                break;
            case STOP:
                this.modeN = 2;
                this.editorMode = true;
                this.endGame = true;
                break;
            case EXIT:
                this.modeN = 3;
                this.systemExit = true;
            default:
                break;
        }

    }

    public void initialGrid ()
    {
        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;
    }

    //Getter
    public boolean getLight(int x, int y) {
        return this.lights[x][y];
    }


    public boolean getEditorMode() {
        return editorMode;
    }

    public void setEditorMode(boolean editorMode) {
        this.editorMode = editorMode;
    }


    public int getModeN() {
        return modeN;
    }

    public void setModeN(int modeN) {
        this.modeN = modeN;
    }

}
