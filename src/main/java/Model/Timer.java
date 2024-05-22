package Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Timer {
    private int timeCounter = 0;
    private Timeline timeline;

    public Timer(double duration) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(duration), e -> increaseTime()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void increaseTime() {
        timeCounter++;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void reset() {
        timeCounter = 0;
    }
}
