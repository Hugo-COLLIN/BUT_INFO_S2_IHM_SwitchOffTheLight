package example.switchOffTheLight.model;

public class GameModel
{
    private boolean [][] lights;

    //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode
    private boolean
            winState,
            lightsFieldInt;

    private String mode;

    public static final int
            LENGTH_X = 4,
            LENGTH_Y = 3;

    public static final String
            PLAY = "Jouer",
            CONF = "Configurer",
            END = "Arrêter",
            EXIT = "Sortir",
            RAND = "Aléatoire";

    public static final String [] ACTION_LIST = {PLAY, CONF, END, EXIT};
    public boolean [] btnState;
    private int nbClicks;

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
        this.lightsFieldInt = false;

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
            nbClicks ++;
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

    public void buttonTriggered(String action, int nbCases)
    {
        this.lightsFieldInt = nbCases >= 0;
        if (this.mode.equals(CONF) && action.equals(CONF))
            this.aleaLights(nbCases);

        this.mode = action;

        if (this.mode.equals(PLAY))
            this.nbClicks = 0;

        for (int i = 0 ; i < ACTION_LIST.length ; i ++)
            if (!(i == 1 && (this.mode.equals(CONF) || this.mode.equals(RAND))))
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

    public void aleaLights (int n)
    {
        if (!this.lightsFieldInt) return;

        int xAlea, yAlea;
        for (int i = 0 ; i < n ; i ++)
        {
            do {
                xAlea = (int) Math.round(Math.random() * LENGTH_X);
                yAlea = (int) Math.round(Math.random() * LENGTH_Y);
            }
            while (this.lights[xAlea][yAlea]);
            this.invert(xAlea, yAlea);
        }
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

    // >Light number input
    public void setLightsFieldInt(boolean b)
    {
        this.lightsFieldInt = b;
    }

    // >Getters
    public boolean getLight(int x, int y) {
        return this.lights[x][y];
    }

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

    public boolean getLightsFieldInt ()
    {
        return this.lightsFieldInt;
    }

    public int getNbClicks() {
        return nbClicks;
    }

    public void setNbClicks(int nbClicks) {
        this.nbClicks = nbClicks;
    }
}
