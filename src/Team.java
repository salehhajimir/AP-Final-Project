import javax.swing.*;
import java.util.ArrayList;

public class Team {
    private ArrayList<Player> members;

    private int teamID;


    /**
     * getter and setter methods.
     * @param teamID
     */
    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    /**
     *
     * @param members
     */
    public void setMembers(ArrayList<Player> members) {
        this.members = members;
    }

    /**
     *
     * @return
     */
    public ArrayList<Player> getMembers() {
        return members;
    }

    /**
     *
     * @return
     */
    public int getTeamID() {
        return teamID;
    }




    void addPlayer(Player newPlayer){
        if (!members.contains(newPlayer))
            members.add(newPlayer);
        else
            JOptionPane.showMessageDialog(null , "This player is already a member of team " + this.teamID);

    }

    /**
     * removing a player from team.
     * @param intendedPlayer
     */
    void removePlayer(Player intendedPlayer){
        if (members.contains(intendedPlayer))
            members.remove(intendedPlayer);
        else
            JOptionPane.showMessageDialog(null , "This player is not a member of this team yet.");
    }

    /**
     * calculate team's score by collecting scores of all team members.
     * @return
     */
    int calculateTeamScore(){
        int teamScore = 0;
        for (Player player : members){
            teamScore += player.getPlayerScore();
        }
        return teamScore;
    }
}
