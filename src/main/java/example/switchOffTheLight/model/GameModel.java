package example.switchOffTheLight.model;

public class GameModel
{
    private boolean [][] lights;
    private boolean winState, randBtn;
    private String mode; //0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode
    public boolean [] btnState;
    private int nbClicks;

    public static final int
            LENGTH_X = 4,
            LENGTH_Y = 3;

    public static final String
            PLAY = "▶ Jouer",
            PAUSE = "⏸ Pause", //🎲🔮⚰🔌🀄🃏🧩🧸🧿🏆🥇🚨🏁🏴‍☠️🧭🌌🪐
            RESTART = " Rejouer", //🔁
            CONF = "🔧 Configurer",
            RAND = "\uD83D\uDD01 Aléatoire", //🎲
            END = "❌ Abandonner",
            EXIT = "\uD83D\uDEA8 Sortir"; //🌙💤💫

    public static final String [] ACTION_LIST = {PLAY, CONF, END, EXIT};

    //public static final char PLAYICON = '▶',


    //public static final char [] ICONS = {'▶', '⚙', '❌', '🔁⏹🔄📴💣💡🔦'};


    //Constructor
    public GameModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];

        this.winState = false;
        this.randBtn = false;

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

    public void changeButtonStatus(String action)
    {
        /*
        if (action.equals(CONF))
            if (!this.mode.equals(CONF))
                this.randBtn = true;
            else
                this.aleaLights();
        else if (this.mode.equals(CONF))
            this.randBtn = false;
        */

        if (!this.mode.equals(CONF) && action.equals(CONF))
            this.randBtn = true;
        else if (this.mode.equals(CONF) && action.equals(CONF))
            this.aleaLights();
        else if (this.mode.equals(CONF))
            this.randBtn = false;

        if (this.winState) this.winState = false;



        this.mode = action;

        if (this.mode.equals(PLAY))
            this.nbClicks = 0;

        for (int i = 0 ; i < ACTION_LIST.length ; i ++)
            if (!((i == 1 || i == 2) && this.mode.equals(CONF)))
                this.btnState[i] = !this.mode.equals(ACTION_LIST[i]);
            else
                this.btnState[i] = this.mode.equals(ACTION_LIST[i]);

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
    public String textTwoSidedBtn()
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
        this.changeButtonStatus(this.mode);
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
