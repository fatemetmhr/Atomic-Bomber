package Model;

import java.util.ArrayList;

public class Game {

    static Game currentGame = null;
    static ArrayList<Game> allGames;
    private User user;



    public Game(User user) {
        this.user = user;
        allGames.add(this);
    }


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }
}
