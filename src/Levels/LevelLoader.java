package Levels;

import Game.GameObject;
import Game.Handler;
import Game.TimerActions;
import Game.Timers;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class LevelLoader{

    private static HashMap<Integer, Class<? extends Level>> levels
            = new HashMap<Integer, Class<? extends Level>>();
    static
    {
        levels.put(1, GreenField.class);
        levels.put(2, GrayCity.class);
    }

    public static Level loadLevel(int id) {

        try {
            return levels.get(id).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
