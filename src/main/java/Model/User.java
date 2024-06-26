package Model;

import javafx.scene.image.Image;

import java.util.ArrayList;


public class User {
    private static User loggedInUser = null;
    private static ArrayList<User> allUsers = new ArrayList<User>();
    private String username;
    private String password;
    private Avatar avatar;
    private Image image;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.avatar = Avatar.getRandomAvatar();
        image = null;
        allUsers.add(this);
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void deleteUser(User user) {
        allUsers.remove(user);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        image = null;
        this.avatar = avatar;
    }


    public void setImage(Image image) {
        avatar = null;
        this.image = image;
    }


    public Image getImage() {
        return avatar != null ? avatar.getImage() : image;
    }
}
