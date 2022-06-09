package example.switchOffTheLight.model;

/**
 * Class that contains all the data concerning the game
 * @author Hugo COLLIN, 09/06/2022
 */
public class GameModel
{
    /**
     * State of each light in the grid
     */
    private final boolean [][] lights;

    /**
     * Is the game won ?
     */
    private boolean winState;

    /**
     * Is the random function enabled ?
     */
    private boolean randBtn;

    /**
     * State of each button
     */
    public boolean [] btnState;

    /**
     * Count the number of clicks performed by user
     */
    private int nbClicksGridPlay;

    /**
     * Game mode (0 : playMode, 1 : editorMode, 2 : endMode, 3 : exitMode)
     */
    private String mode;

    /**
     * Constants which define the width and the height of the light grid
     */
    public static final int
            LENGTH_X = 4,
            LENGTH_Y = 4;

    /**
     * Constants which define the different modes of the game
     */
    public static final String
            PLAY = "▶\nJouer",
            CONF = "🔧\nConfigurer",
            RAND = "\uD83D\uDD01\nAléatoire", //🔁
            END = "❌\nQuitter",
            EXIT = "\uD83D\uDEA8\nPartir", //🚨
            WIN = "\uD83C\uDFC6\nTu as gagné !\n\uD83C\uDFC6", //🏁
            CLICKS = " clics !";

    /**
     * List of actions corresponding to each button shown on the interface
     */
    public static final String [] BTN_LIST = {PLAY, CONF, END, EXIT};


    //Constructor

    /**
     * Constructor without parameters that initialize all the attributes
     */
    public GameModel() {
        this.lights = new boolean[LENGTH_X][LENGTH_Y];

        this.winState = false;
        this.randBtn = false;

        this.mode = END;
        this.btnState = new boolean[]{true, true, false, true};
        this.nbClicksGridPlay = 0;

        this.initialGrid();
    }


    //Methods
    // >Possible user actions
    /**
     * When the player clicks on a light, it will change the lights state depending on the current mode
     * @param x coordinate corresponding to the width which is localized the clicked light
     * @param y coordinate corresponding to the height which is localized the clicked light
     */
    public void clickOnLight (int x, int y)
    {
        if (!this.mode.equals(END))
        {
            invert(x,y);

            if (this.mode.equals(PLAY))
                nbClicksGridPlay++;

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

    /**
     * When the player clicks on a button, it will execute the corresponding fuction of this button
     * and update the state of each button.
     * @param action it's the function of the clicked button
     */
    public void btnTriggered(String action)
    {
        this.upBtnState(action);

        if (this.winState) this.winState = false;
        if (isClearGame())
            this.initialGrid();
    }

    // >Game mechanic

    /**
     * Verify if the game needs to be cleared (is it needed to reset all the lights ?)
     * @return state about the reset light's need
     */
    public boolean isClearGame ()
    {
        return (!this.winState && this.mode.equals(GameModel.END))
                || (this.winState && this.mode.equals(GameModel.PLAY));
    }


    //   ->Winning

    /**
     * Verify if the player won the game
     * @return the winning state
     */
    public boolean isWin ()
    {
        if (!this.mode.equals(PLAY)) return false;

        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0; j < LENGTH_Y; j++)
                if (this.lights[i][j]) return false;

        return true;
    }

    /**
     * Actions executed if the game is won
     */
    public void winAction ()
    {
        this.mode = END;
        this.winState = true;
        this.upBtnState(this.mode);
    }

    // >Grid methods
    /**
     * Reset all the lights to default state (false => off)
     */
    public void initialGrid ()
    {
        this.winState = false;
        for (int i = 0 ; i < LENGTH_X ; i ++)
            for (int j = 0 ; j < LENGTH_Y ; j ++)
                this.lights[i][j] = false;
    }

    /**
     * Change the state of a light
     * @param x coordinate corresponding to the width which is localized the light
     * @param y coordinate corresponding to the height which is localized the light
     */
    public void invert (int x, int y)
    {
        if (inGrid(x, y))
            this.lights[x][y] = !this.lights[x][y];
    }

    /**
     * Switch on randomly a random number of light(s)
     */
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

    /**
     * Verify if a coordinate is in the grid of lights
     * @param x coordinate corresponding to the width which is localized the light
     * @param y coordinate corresponding to the height which is localized the light
     * @return if the position is in the grid
     */
    public boolean inGrid (int x, int y)
    {
        return x >= 0 && x < LENGTH_X && y >= 0 && y < LENGTH_Y;
    }


    // >Buttons
    /**
     * Update the state of each button corresponding to the actual mode
     * @param action Function corresponding to the button clicked by player
     */
    public void upBtnState(String action)
    {
        if (!this.mode.equals(CONF) && action.equals(CONF))
            this.randBtn = true;
        else if (this.mode.equals(CONF) && action.equals(CONF))
            this.aleaLights();
        else if (this.mode.equals(CONF))
            this.randBtn = false;

        this.mode = action;

        if (this.mode.equals(PLAY))
            this.nbClicksGridPlay = 0;

        for (int i = 0; i < BTN_LIST.length ; i ++)
            if (!((i == 1 || i == 2) && this.mode.equals(CONF)))
                this.btnState[i] = !this.mode.equals(BTN_LIST[i]);
            else
                this.btnState[i] = this.mode.equals(BTN_LIST[i]);
    }

    /**
     * For double-sided CONF/ALEA button. Switch between CONF and ALEA String shown as the button text
     * @return
     */
    public String textTwoSidedBtn()
    {
        if (randBtn)
            return RAND;
        return CONF;
    }

    // >Getters

    /**
     * Getter of the light attribute
     * @param x coordinate corresponding to the width which is localized the light
     * @param y coordinate corresponding to the height which is localized the light
     * @return the light state
     */
    public boolean getLight(int x, int y) {
        return this.lights[x][y];
    }

    /**
     * Getter of the winState attribute
     * @return the winState attribute
     */
    public boolean getWinState() {
        return winState;
    }

    /**
     * Getter of the winState attribute
     */
    public void setWinState(boolean winState) {
        this.winState = winState;
    }

    /**
     * Getter of the mode attribute
     * @return the mode attribute
     */
    public String getMode() {
        return mode;
    }

    /**
     * Setter of the mode attribute
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * Getter of the btnState attribute
     * @return the btnState attribute
     */
    public boolean[] getBtnState() {
        return btnState;
    }

    /**
     * Setter of the btnState attribute
     */
    public void setBtnState(boolean[] btnState) {
        this.btnState = btnState;
    }

    /**
     * Getter of a specific button state
     * @param index location of the button state in the array
     * @return the state of the button
     */
    public boolean getBtnState(int index) {
        return btnState[index];
    }

    /**
     * Getter of the nbClicksGridPlay attribute
     * @return the nbClicksGridPlay attribute
     */
    public int getNbClicksGridPlay() {
        return nbClicksGridPlay;
    }

    /**
     * Setter of the setNbClicksGridPlay attribute
     */
    public void nbClicksGridPlay(int nbClicksGridPlay) {
        this.nbClicksGridPlay = nbClicksGridPlay;
    }

    /**
     * Getter of the randBtn attribute
     * @return the randBtn attribute
     */
    public boolean getRandBtn() {
        return randBtn;
    }

    /**
     * Setter of the randBtn attribute
     */
    public void setRandBtn(boolean randBtn) {
        this.randBtn = randBtn;
    }
}
