package Game;

import Application.GameController;
import Levels.GreenField;
import Levels.Level;
import javafx.scene.paint.Color;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActions {
    public static TimerActions timeractions;
    public static TimerTask tickingdrawing = new TimerTask() {
        @Override
        public void run() {
            Handler.handler.tick();
            Handler.handler.render();
        }
    };


    private static Random r = new Random();
    public static TimerTask enemies = new TimerTask() {
        @Override
        public void run() {
            Handler.handler.addGameObject(new Fly(GameController.WIDTH,
                    r.nextInt((int) GameController.HEIGHT-100), Id.enemy));
        }
    };

    public static TimerTask recharging = new TimerTask() {
        @Override
        public void run() {
            for (int i = 0; i < Handler.handler.gameObjects.size(); i++)
            {
                final GameObject temp = Handler.handler.gameObjects.get(i);
                final Timer time = new Timer();
                if (temp.getId() == Id.player)
                    time.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            if (GreenField.checkBullets() < 20) {
                                temp.bullets++;
                                GameController.gamecontroller.setLife(
                                        GameController.gamecontroller.currentlife);
                            }
                            else
                            {
                                time.cancel();
                            }


                        }
                    },0,2000);


            }
        }
    };
}