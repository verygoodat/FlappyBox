package Levels;

import Application.GameController;
import Game.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GreenField extends Level {
    private Random r = new Random();
    Timer enemies = Timers.createTimer(TimerActions.enemies,r.nextInt(2000)+2000);
    private static int bullets;
    @Override
    public void loadLevel() {
        Handler.handler.addGameObject(new Player(30, 300, Id.player));
//        Handler.handler.addGameObject(new Fly(800,300, Id.enemy));

    }
    public static int checkBullets()
    {
        for (int i = 0; i < Handler.handler.gameObjects.size(); i++)
        {
            GameObject temp = Handler.handler.gameObjects.get(i);
            if (temp.getId() == Id.player)
                bullets = temp.getBullets();
        }
        return bullets;
    }
    @Override
    public void drawBack() {
        Image image = new Image("Sources/Menu.jpg");
        gc.drawImage(image, 0 ,0);

    }
}
