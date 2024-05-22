package View;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameController {

    public ImageView heart1;
    public ImageView heart2;
    public ImageView heart3;
    public ImageView heart4;
    public ImageView ice1;
    public ImageView ice2;
    public ImageView ice3;
    public ImageView ice4;
    public ImageView ice5;
    public Label wave;
    public Label kills;
    public Label accuracy;
    public Label clusters;
    public Label radioactive;

    public void initialize(){
        Game.gameController = this;
    }

    public void showWave(int wave){
        this.wave.setText("Wave " + wave);
    }

    public void showKillsAndAccuracy(int kills, double accuracy){
        this.kills.setText("Number of Kills: " + kills);
        int accuracyInt = (int) (accuracy * 100);
        accuracy = (double) accuracyInt / 100;
        this.accuracy.setText("Accuracy: " + accuracy + "%");
    }

    public void showClusters(int clusters){
        this.clusters.setText("Remained Clusters: " + clusters);
    }

    public void showRadioactive(int radioactive){
        this.radioactive.setText("Remained Radioactive: " + radioactive);
    }

    public void changeHeart(int index, int state){
        Image image = new Image(GameController.class.getResource("/Images/Icons/heart" + state + ".png").toString());
        switch (index){
            case 1:
                heart1.setImage(image);
                break;
            case 2:
                heart2.setImage(image);
                break;
            case 3:
                heart3.setImage(image);
                break;
            case 4:
                heart4.setImage(image);
                break;
        }
    }

    public void changeIce(int index, boolean state){
        switch (index){
            case 1:
                ice1.setOpacity(state ? 1 : 0.4);
                break;
            case 2:
                ice2.setOpacity(state ? 1 : 0.4);
                break;
            case 3:
                ice3.setOpacity(state ? 1 : 0.4);
                break;
            case 4:
                ice4.setOpacity(state ? 1 : 0.4);
                break;
            case 5:
                ice5.setOpacity(state ? 1 : 0.4);
                break;
        }

    }

}
