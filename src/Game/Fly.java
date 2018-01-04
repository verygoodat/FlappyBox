package Game;

import Application.GameController;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Fly extends GameObject {
    private Random r = new Random();
    private int m = 3;
    private int following = r.nextInt(3);
    private int dive = 0;
    private boolean dead = false;
    private int interval = 0;
    private String path = "Sources/Birds/RedFly/flying/frame-";
    public Fly(double x, double y, Id id) {
        super(x, y, id);
        frame = 1;
        w = 70;
        h = 50;
        weigth = 2;
        velx = -r.nextInt(10)-5;
        Bullet.lifetime = 40;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {
    if (frame <= 2)
    {img = new Image(path+frame+".png");
    frame++;}
    else
        frame = 1;
    draw.clearRect(x,y,w,h);
    x += velx;
    y += vely;
        draw.drawImage(img, x,y,w,h);
        if (x+w < 0)
        {draw.clearRect(x,y,w,h);
        Handler.handler.gameObjects.remove(this);
        GameController.gamecontroller.setScore(-15);}
        else if (y+h < 0 || y > GameController.HEIGHT)
        {draw.clearRect(x,y,w,h);
            Handler.handler.gameObjects.remove(this);
            }

        Collision(object);
    }

    private void Collision(ArrayList<GameObject> objects)
    {

        for (int i = 0; i < objects.size(); i++)
        {
            GameObject tempObject = objects.get(i);
            if (dead != true) {
            if (tempObject.getId() == Id.bullet) {
                if (getBoundsTop().intersects(tempObject.getBoundsTop())) {
                    path = "Sources/Birds/RedFly/gameover/frame-";
                    vely = 20;
                    velx = 0;
                    draw.clearRect(tempObject.x, tempObject.y, tempObject.w, tempObject.h);
                    Handler.handler.gameObjects.remove(tempObject);
                    File s = new File("src/Sources/die.wav");
                    Media sound = new Media(s.toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    dead = true;
                    GameController.gamecontroller.setScore(+5);
                }
            }
            if (tempObject.getId() == Id.player)
            {
                if (m > 0 && interval % 10 == 0)
                if (y - 10 < tempObject.getY() && y + h + 10 > tempObject.getY())
                {
                    Bullet b = new Bullet(x-w-10, y, Id.bullet);
                    b.setVelX(-30);
                    Handler.handler.gameObjects.add(b);
                    m--;
                }
                else
                    m = 3;
                interval++;
            }
                }

        }
    }
    @Override
    public void render() {
    draw.drawImage(img, x,y,w,h);
    }

    @Override
    public Rectangle2D getBoundsTop() {
        return new Rectangle2D(x,y,w,h);
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
