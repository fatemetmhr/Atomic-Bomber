package Model;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collection;

public class Game {

    static Game currentGame = null;
    static ArrayList<Game> allGames = new ArrayList<>();
    private User user;
    private Plane plane;
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
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

    public ArrayList<Obstacle> getAllObstacles() {
        return obstacles;
    }

    public void addTank() {
        Tank tank = new Tank(this);
        pane.getChildren().add(tank);
        obstacles.add(tank);
    }

    public ArrayList<Bullet> getBulletsCopy() {
        return new ArrayList<>(bullets);
    }

    public ArrayList<Obstacle> getAllObstaclesCopy() {
        return new ArrayList<>(obstacles);
    }
}
