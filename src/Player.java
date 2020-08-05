/**
 * player's class.
 */
public class Player {
    // player's tank.
    private Tank playerTank;
    // amount of player's scores , wins and losses.
    private int playerScore , computerWin , computerLoss , multiplayerWin , multiplayerLoss;
    // team which player's belong to.
    private Team playerTeam;
    // player's username and password.
    private String userName , passWord;

    /**
     * setter and getter methods.
     * @param playerScore
     */
    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     *
     * @param playerTank
     */
    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

    /**
     *
     * @param playerTeam
     */
    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @return
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     *
     * @return
     */
    public int getPlayerScore() {
        return playerScore;
    }

    /**
     *
     * @return
     */
    public Tank getPlayerTank() {
        return playerTank;
    }

    /**
     *
     * @return
     */
    public Team getPlayerTeam() {
        return playerTeam;
    }


    public Player(String userName){
        this.userName = userName;
        playerScore = 0;
    }

    /**
     * calculating player's score.
     */
    public void calculateScore() {
        if (!playerTank.isAlive())
            playerScore--;
        else
            playerScore++;
    }


}
