package Game;

import Application.GameController;
import Application.KeyController;
import Application.Main;
import Levels.Level;
import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.InputStream;
import java.util.ArrayList;

public class Player extends GameObject{
    public double maxspeed = 8;
    public double minspeed = 0;
    private int a = 1;

    public Player(double x, double y, Id id) {
        super(x, y, id);
        w = 60;
        h = 50;
        weigth = 2; //kg
        velx = 0;

    }

    @Override
    public void tick(ArrayList<GameObject> object) {

        vely = weigth*(acceleration)*gravity+vely;
        //velx = velx/(0.6*acceleration*weigth);
        if (velx > maxspeed)
        velx = maxspeed;
//        else if (velx < minspeed)
//            velx = minspeed;
        if (x+w+velx >= GameController.WIDTH)
            velx = 2;
        if (a < 9)
           img = new Image("Sources/Birds/YellowMonster/frame-"+(a++)+".png");
        else
            a = 1;
        draw.clearRect(x,y,w,h);
        x += velx;
        y += vely;

        if (y + h < 0)
            setY(GameController.HEIGHT);
        if (y > GameController.HEIGHT)
            setY(-h);
        draw.drawImage(img, x,y, w,h );
        Collision(object);
    }

    private void Collision(ArrayList<GameObject> object)
    {   GameObject tempObject;
        for (int i = 0; i < object.size(); i++)
        {
            tempObject = object.get(i);
            if (tempObject.getId() == Id.enemy || tempObject.getId() == Id.bullet)
            {
                if (getBoundsTop().intersects(tempObject.getBoundsTop()))
                {
                    draw.clearRect(tempObject.getX(), tempObject.getY(),
                            tempObject.w, tempObject.h);
                    Handler.handler.gameObjects.remove(tempObject);
                    int l = --GameController.gamecontroller.currentlife;
                    if (l != 0)
                    {
                        GameController.gamecontroller.setLife(l);
                        GameController.gamecontroller.setScore(-10);}
                    else
                    {
                        GameController.gamecontroller.setState(2);

                    }

                }
            }
        }
    }
    @Override
    public void render() {
        draw.drawImage(img, x,y, w,h );
    }

    @Override
    public Rectangle2D getBoundsTop() {
        return new Rectangle2D(x,y,w,h-20);
    }

    @Override
    public Rectangle2D getBoundsDown() {
        return null;
    }

    @Override
    public Rectangle2D getBoundsLeft() {
        return null;
    }

    @Override
    public Rectangle2D getBoundsRight() {
        return null;
    }


}
