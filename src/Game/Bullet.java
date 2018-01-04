package Game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Bullet extends GameObject{
    public static int lifetime = 17;
    public Bullet(double x, double y, Id id) {
        super(x, y, id);
        gravity = 0;
        w = 70;
        h = 40;
        velx = 10;
    }

    @Override
    public void tick(ArrayList<GameObject> object) {

        if (img == null)
        img = new Image("Sources/hd_flame_0.png");
        draw.clearRect(x,y, w,h);
        x += velx;
        draw.drawImage(img, x,y, w,h );
        lifetime--;
        if (lifetime == 0)
        {
            draw.clearRect(x,y,w,h);
            Handler.handler.gameObjects.remove(this);
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
