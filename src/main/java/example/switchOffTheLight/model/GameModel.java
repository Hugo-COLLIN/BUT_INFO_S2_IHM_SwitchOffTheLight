package example.switchOffTheLight.model;

public class GameModel
{
    private boolean [][] lights;

    //private int modeN; //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode

    //HashMap<String, Boolean> btnsStatus;

    private boolean
            isWin/*,
            editorMode,
            endGame,
            systemExit*/;

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
    public boolean [] btnState;

    /*
    public static final boolean []
            BTNS_STATE_PLAY = {false, true, true, true},
            BTNS_STATE_EDIT = {true, true, false, true};

     */

    public GameModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];
        //this.btnsStatus = new HashMap<>();

        this.mode = "Arrêter";
        this.btnState = new boolean[]{true, true, false, true};

        /*
        boolean tmp = false;
        for (String action : ACTION_LIST)
        {
            btnsStatus.put(action, tmp);
            tmp = true;
        }
        System.out.println(btnsStatus);
         */


        //this.modeN = 2;
        this.isWin = false;

        this.initialGrid();



        //toDel
        /*
        this.editorMode = true;
        this.endGame = false;
        this.systemExit = false;

         */
    }

    public void clickOnLight (int x, int y)
    {
        if (!this.mode.equals(STOP))
        {
            invert(x,y);

            if (this.mode.equals(PLAY))
            {
                invert(x - 1, y);
                invert(x + 1, y);
                invert(x, y - 1);
                invert(x, y + 1);
            }
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

    public boolean isClearGame ()
    {
        return (!this.isWin() && this.mode.equals(GameModel.STOP))
                || (this.isWin() && this.mode.equals(GameModel.PLAY));
    }

    public void buttonTriggered(String action)
    {
        this.mode = action;

        for (int i = 0 ; i < ACTION_LIST.length ; i ++)
            this.btnState[i] = !this.mode.equals(ACTION_LIST[i]);

        if (isClearGame())
            this.initialGrid();

        /*
        switch (action)
        {
            case PLAY:
                btnState = {}
                break;
            case EDIT:

                break;
            case STOP:

                break;
            case EXIT:

                break;
            default:
                break;
        }*/
/*
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
*/
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

/*
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
*/

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public boolean[] getBtnState() {
        return btnState;
    }

    public void setBtnState(boolean[] btnState) {
        this.btnState = btnState;
    }

    public boolean getBtnState(int index) {
        return btnState[index];
    }
}
