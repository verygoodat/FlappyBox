package Game;

import Application.GameController;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.util.ArrayList;

public abstract class GameObject{

    private Id id;
    protected double x, y, vely, velx;
    protected int w, h;
    protected double acceleration = 2; //2px/s
    protected double weigth;
    protected double gravity = 0.4;
    public Image img;
    public int frame = 1;
    public int interval = 0;
    public int bullets = 20;
    public static GraphicsContext draw; //Static context - doesn't change
    public GameObject(double x, double y, Id id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public double getX(){return x;}
    public double getY(){return y;}
    public void setX(double x){this.x = x;}
    public void setY(double y){this.y = y;}
    public double getVelY(){return vely;}
    public double getVelX(){return velx;}
    public void setVelY(double vely){this.vely = vely;}
    public void setVelX(double velx){this.velx = velx;}
    public Id getId() {return id;}
    public int getBullets(){return bullets;}
    public abstract void tick(ArrayList<GameObject> object);
    public abstract void render();
    public abstract Rectangle2D getBoundsTop();
    public abstract Rectangle2D getBoundsDown();
    public abstract Rectangle2D getBoundsLeft();
    public abstract Rectangle2D getBoundsRight();
}
