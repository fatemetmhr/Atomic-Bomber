package Model;

import Controller.GameController;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Game {

    static Game currentGame = null;
    static ArrayList<Game> allGames = new ArrayList<>();
    private User user;
    private Plane plane;
    private ArrayList<Shot> allShots = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private Pane pane;
    private int wave = 1;
    private int kills = 0;
    private boolean upKey = false;
    private boolean downDown = false;
    private boolean leftKey = false;
    private boolean rightKey = false;
    private int shoots = 0;
    private int ice;
    private int remainedClusters = 0;


    public Game(User user, Pane root) {
        this.user = user;
        allGames.add(this);
        this.pane = root;
        plane = new Plane(this);
        root.getChildren().add(plane);
        ice = 0;
        createWave();
    }

    private void createWave() {
        View.GameController gameController = View.Game.gameController;
        gameController.showWave(wave);
        Building building = new Building(this, 950, 600);
        building = new Building(this, 400, 550);
        Bunker bunker = new Bunker(this, 670, 650);
        Tree tree = new Tree(this, 30, 650);
        tree = new Tree(this, 1100, 630);
        tree = new Tree(this, 530, 650);
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


    public Pane getPane() {
        return pane;
    }

    public ArrayList<Obstacle> getAllObstacles() {
        return obstacles;
    }

    public void addTank() {
        Tank tank = new Tank(this);
    }

    public ArrayList<Shot> getShotsCopy() {
        return new ArrayList<>(allShots);
    }

    public ArrayList<Obstacle> getAllObstaclesCopy() {
        return new ArrayList<>(obstacles);
    }

    public int numberOfTanks() {
        int cnt = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Tank)
                cnt++;
        }
        return cnt;
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


    public int numberOfTrucks() {
        int cnt = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle instanceof Truck)
                cnt++;
        }
        return cnt;
    }

    public void addTruck() {
        Truck truck = new Truck(this);
    }

    public boolean isAnyMovingObstacleInFrame() {
        for (Obstacle obstacle : obstacles)
            if(obstacle instanceof Truck || obstacle instanceof Tank){
                if (obstacle.getX() < 200 || obstacle.getX() > 1101)
                    return true;
        }
        return false;
    }

    public int getKills() {
        return kills;
    }

    public double getAccuracy() {
        if(shoots == 0)
            return 100;
        return ((double)kills) / shoots * 100;
    }

    public void increaseShoots() {
        shoots++;
    }

    public void checkForIncreasedIce(Obstacle obstacle) {
        if(obstacle instanceof Truck || obstacle instanceof Tank || obstacle instanceof Building){
            if(ice < 5) {
                ice++;
                View.Game.gameController.changeIce(5 - ice + 1, true);
            }
        }
    }

    public ArrayList<Shot> getShots() {
        return allShots;
    }

    public void setRemainedClusters(int remainedClusters) {
        this.remainedClusters = remainedClusters;
        Controller.GameController.showClusters(remainedClusters);
    }

    public int getRemainedClusters() {
        return remainedClusters;
    }
}
