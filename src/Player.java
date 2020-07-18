public class Player {
    private Tank playerTank;
    private int playerScore;
    private Team playerTeam;

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public void setPlayerTeam(Team playerTeam) {
        this.playerTeam = playerTeam;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public Team getPlayerTeam() {
        return playerTeam;
    }
}
