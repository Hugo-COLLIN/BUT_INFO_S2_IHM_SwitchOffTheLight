package example.switchOffTheLight.model;

public class GameModel
{
    private boolean [][] lights;

    //private int modeN; //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode

    //HashMap<String, Boolean> btnsStatus;

    private boolean
            winState/*,
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
            END = "Arrêter",
            EXIT = "Sortir";

    public static final String [] ACTION_LIST = {PLAY, CONF, END, EXIT};
    public boolean [] btnState;

    /*
    public static final boolean []
            BTNS_STATE_PLAY = {false, true, true, true},
            BTNS_STATE_EDIT = {true, true, false, true};

     */

    //Constructor
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
        this.winState = false;

        this.initialGrid();



        //toDel
        /*
        this.editorMode = true;
        this.endGame = false;
        this.systemExit = false;

         */
    }

    //Methods
    // >Possible actions
    public void clickOnLight (int x, int y)
    {
        if (!this.mode.equals(END))
        {
            invert(x,y);

            if (this.mode.equals(PLAY))
            {
                invert(x - 1, y);
                invert(x + 1, y);
                invert(x, y - 1);
                invert(x, y + 1);
            }


            if (isWin())
                this.winAction();
        }
    }

    public void buttonTriggered(String action)
    {
        this.mode = action;

        for (int i = 0 ; i < ACTION_LIST.length ; i ++)
            this.btnState[i] = !this.mode.equals(ACTION_LIST[i]);

        if (isClearGame())
            this.initialGrid();
    }

    // >Grid
    public void invert (int x, int y)
    {
        if (inGrid(x, y))
            this.lights[x][y] = !this.lights[x][y];
    }

    public boolean inGrid (int x, int y)
    {
        return x >= 0 && x < LENGTH_X && y >= 0 && y < LENGTH_Y;
    }

    public void initialGrid ()
    {
        this.winState = false;
        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;
    }

    public boolean isClearGame ()
    {
        return (!this.winState && this.mode.equals(GameModel.END))
                || (this.winState && this.mode.equals(GameModel.PLAY));
    }


    // >Winning
    public boolean isWin ()
    {
        if (!this.mode.equals(PLAY)) return false;

        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0; j < LENGTH_Y; j++)
                if (this.lights[i][j]) return false;

        return true;
    }

    public void winAction ()
    {
        this.mode = END;
        this.winState = true;
    }

    // >Getters
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

    public boolean getWinState() {
        return winState;
    }

    public void setWinState(boolean winState) {
        this.winState = winState;
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
