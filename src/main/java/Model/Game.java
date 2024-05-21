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
    private int wave = 1;
    private int kills = 0;
    private boolean upKey = false;
    private boolean downDown = false;
    private boolean leftKey = false;
    private boolean rightKey = false;




    public Game(User user, Pane root) {
        this.user = user;
        allGames.add(this);
        this.pane = root;
        plane = new Plane(this);
        root.getChildren().add(plane);
        createWave();
    }

    private void createWave() {
        Building building = new Building(this, 950, 600);
        building = new Building(this, 400, 550);
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
    }

    public ArrayList<Bullet> getBulletsCopy() {
        return new ArrayList<>(bullets);
    }

    public ArrayList<Obstacle> getAllObstaclesCopy() {
        return new ArrayList<>(obstacles);
    }

    public boolean hasAnyTank() {
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Tank)
                return true;
        }
        return false;
    }

    public void increaseKills(int score) {
        kills += score;
    }

    public boolean getUpKey() {
        return upKey;
    }

    public void setUpKey(boolean upKey) {
        this.upKey = upKey;
    }

    public boolean getDownDown() {
        return downDown;
    }

    public void setDownDown(boolean downDown) {
        this.downDown = downDown;
    }

    public boolean getLeftKey() {
        return leftKey;
    }

    public void setLeftKey(boolean leftKey) {
        this.leftKey = leftKey;
    }

    public boolean getRightKey() {
        return rightKey;
    }

    public void setRightKey(boolean rightKey) {
        this.rightKey = rightKey;
    }


}
