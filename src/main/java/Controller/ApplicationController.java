package Controller;

import Model.User;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class ApplicationController {


    public static MediaPlayer mediaPlayer = null;
    private static boolean isSoundMuted = false;
    private static int gameDifficulty = 1;
    private static boolean isWASD = false;
    private static boolean isBlackAndWhite = false;
    private static boolean isWin = false;
    private static String lastMusicPath = null;

    public static void startMusic(String path) {
        if (lastMusicPath != null && lastMusicPath.equals(path))
            return;
        if (mediaPlayer != null)
            mediaPlayer.stop();
        lastMusicPath = path;
        try {
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void muteMusic() {
        isSoundMuted = true;
        if (mediaPlayer != null)
            mediaPlayer.setMute(true);
    }

    public static void unmuteMusic() {
        isSoundMuted = false;
        if (mediaPlayer != null)
            mediaPlayer.setMute(false);
    }

    public static void resetGameSetting() {
        gameDifficulty = 1;
        isWASD = false;
        isBlackAndWhite = false;
        isSoundMuted = false;
    }

    public static boolean isSoundMuted() {
        return isSoundMuted;
    }

    public static void setGameDifficulty(int difficulty) {
        gameDifficulty = difficulty;
    }

    public static int getGameDifficulty() {
        return gameDifficulty;
    }

    public static void setWASD(boolean wasd) {
        isWASD = wasd;
    }

    public static boolean isWASD() {
        return isWASD;
    }

    public static void setBlackAndWhite(boolean blackAndWhite) {
        isBlackAndWhite = blackAndWhite;
    }

    public static boolean isBlackAndWhite() {
        return isBlackAndWhite;
    }

    public static void applyColorAffects(Scene scene) {
        if (isBlackAndWhite) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setSaturation(-1);
            scene.getRoot().setEffect(colorAdjust);
        } else {
            scene.getRoot().setEffect(null);
        }
    }

    public static KeyCode getDownKeyCode() {
        return isWASD ? KeyCode.S : KeyCode.DOWN;
    }

    public static KeyCode getUpKeyCode() {
        return isWASD ? KeyCode.W : KeyCode.UP;
    }

    public static KeyCode getLeftKeyCode() {
        return isWASD ? KeyCode.A : KeyCode.LEFT;
    }

    public static KeyCode getRightKeyCode() {
        return isWASD ? KeyCode.D : KeyCode.RIGHT;
    }

    public static void setGameResult(boolean isWin) {
        ApplicationController.isWin = isWin;
    }

    public static boolean isWin() {
        return isWin;
    }

    public static String getUsername() {
        return User.getLoggedInUser().getUsername();
    }

    public static String getPassword() {
        return User.getLoggedInUser().getPassword();
    }

    public static void setIsWin(boolean isWin) {
        ApplicationController.isWin = isWin;
    }
}
