package Model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Plane extends Rectangle {

    static int sizeX = 890 / 9;
    static int sizeY = 325 / 9;

    private double dir;
    private double speed;
    Image image;

    public Plane(){
        super(sizeX, sizeY);
        setX(100);
        setY(150);
        dir = 0;
        speed = 2;
        //Image = new Image(Plane.class.getResource("/Images/Icons/plane.png").toString());
        setFill(new ImagePattern(new Image(Plane.class.getResource("/Images/Icons/plane.png").toString())));
    }


    public void move(double speed) {
        setX(getX() + speed * Math.cos(dir));
        setY(getY() + speed * Math.sin(dir));
    }

    public void passTime() {
        move(speed);
        if(getX() < -sizeX)
            setX(1291);
        if(getX() > 1291)
            setX(-sizeX);
        if(getY() < 0)
            setDir(-dir);
        if(getY() > 849 - sizeY)
            setDir(-dir);
    }

    public void setDir(double v) {
        v += 10 * Math.PI;
        v %= 2 * Math.PI;
        if(Math.abs(v) < 0.0001)
            v = 0;
        if(Math.abs(v - Math.PI) < 0.0001)
            v = Math.PI;
        if(Math.abs(v - Math.PI / 2) < 0.0001)
            v = Math.PI / 2;
        if(Math.abs(v - 3 * Math.PI / 2) < 0.0001)
            v = 3 * Math.PI / 2;
        dir = v;
        if(v >= Math.PI / 2 && v <= 3 * Math.PI / 2)
            setScaleY(-1);
        else
            setScaleY(1);
        setRotate(Math.toDegrees(dir));
    }

    public double getDir() {
        return dir;
    }

    public void makeDirCloseTo(double v) {
        int scale = 20;
        if(dir == v)
            return;
        if(getMinRotationDistance(dir, v) < getMinRotationDistance(v, dir))
            setDir(dir + Math.PI / scale);
        else
            setDir(dir - Math.PI / scale);
    }

    private double getMinRotationDistance(double dir1, double dir2) {
        if(dir1 <= dir2)
            return dir2 - dir1;
        return 2 * Math.PI - dir1 + dir2;
    }
}
