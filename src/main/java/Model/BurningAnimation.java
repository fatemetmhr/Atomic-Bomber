package Model;

import javafx.scene.image.ImageView;

public abstract class BurningAnimation {

    private Game game;
    protected ImageView imageView = new ImageView();

    protected Timer burningTimer;

    public BurningAnimation(int x, int y, Game game) {
        this.game = game;
        imageView.setX(x);
        imageView.setY(y);
        game.getPane().getChildren().add(imageView);
        burningTimer = new Timer(0.5);
    }

    public void remove() {
        game.getPane().getChildren().remove(imageView);
        game.getAllBurningAnimations().remove(this);
    }

    public int getBurnTime() {
        return burningTimer.getTimeCounter();
    }
}
