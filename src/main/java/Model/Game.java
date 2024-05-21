package Model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Game {

    static Game currentGame = null;
    static ArrayList<Game> allGames = new ArrayList<>();
    private User user;
    private Plane plane;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private Pane pane;



    public Game(User user, Pane root) {
        this.user = user;
        allGames.add(this);
        this.pane = root;
        plane = new Plane(this);
        root.getChildren().add(plane);
    }


    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void setCurrentGame(Game currentGame) {
        Game.currentGame = currentGame;
    }

    public  Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public Pane getPane() {
        return pane;
    }
}
