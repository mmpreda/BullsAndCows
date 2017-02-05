package ro.devacademy.mada.bullsandcows;

/**
 * Created by mpreda on 21/11/2016.
 */

public class Player {

    /**
     *  TO DO:
     *  - player picture (taken with camera)
     *  - date when the game is played (to be later displayed in hall of fame)
     */

    private String playerName;
    private int playerScore;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public Player(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }
}
