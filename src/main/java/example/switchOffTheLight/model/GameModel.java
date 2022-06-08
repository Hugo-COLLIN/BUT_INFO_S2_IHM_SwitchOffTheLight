package example.switchOffTheLight.model;

public class GameModel
{
    private boolean [][] lights;
    private boolean winState, lightsFieldInt, randBtn;
    private String mode; //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode
    public boolean [] btnState;
    private int nbClicks;

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


    //Constructor
    public GameModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];

        this.winState = false;
        this.randBtn = false;
        this.lightsFieldInt = false;

        this.mode = "Arrêter";
        this.btnState = new boolean[]{true, true, false, true};
        this.nbClicks = 0;

        this.initialGrid();
    }


    //Methods
    // >Possible user actions
    public void clickOnLight (int x, int y)
    {
        if (!this.mode.equals(END))
        {
            invert(x,y);

            if (!this.mode.equals(CONF))
                nbClicks ++;

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

    public void buttonTriggered(String action, int customLightsOn)
    {
        //this.lightsFieldInt = customLightsOn >= 0;
        if (this.mode.equals(CONF))
        {
            if (action.equals(CONF))
            {
                this.randBtn = true;
                this.aleaLights();
                //this.aleaLights(customLightsOn);
            }
            else this.randBtn = false;
        }

        this.mode = action;

        if (this.mode.equals(PLAY))
            this.nbClicks = 0;

        for (int i = 0 ; i < ACTION_LIST.length ; i ++)
            if (!(i == 1 && (this.mode.equals(CONF) || this.mode.equals(RAND))))
                this.btnState[i] = !this.mode.equals(ACTION_LIST[i]);

        if (isClearGame())
            this.initialGrid();
    }


    // >Grid methods
    public void initialGrid ()
    {
        this.winState = false;
        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;
    }

    public void invert (int x, int y)
    {
        if (inGrid(x, y))
            this.lights[x][y] = !this.lights[x][y];
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
    public void aleaLights ()
    {
        int xAlea, yAlea;

        //Switch off all the lights
        this.initialGrid();

        //Generate a number between 0 and the number of lights on the board
        int n = (int)Math.round(Math.random() * LENGTH_X * LENGTH_Y);

        for (int i = 0 ; i < n ; i ++)
        {
            do {
                xAlea = (int) Math.round(Math.random() * (LENGTH_X - 1));
                yAlea = (int) Math.round(Math.random() * (LENGTH_Y - 1));
            }
            while (this.lights[xAlea][yAlea]);
            this.invert(xAlea, yAlea);
        }
    }

    public boolean inGrid (int x, int y)
    {
        return x >= 0 && x < LENGTH_X && y >= 0 && y < LENGTH_Y;
    }

    // >Buttons
    public String setTextTwoSidedBtn ()
    {
        if (randBtn)
            return RAND;
        return CONF;
    }

    // >Meca jeu
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

    public boolean getRandBtn() {
        return randBtn;
    }

    public void setRandBtn(boolean randBtn) {
        this.randBtn = randBtn;
    }
}
