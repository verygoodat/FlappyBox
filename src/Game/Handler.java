package Game;

import Application.GameController;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Handler {
    public static Handler handler = new Handler();
    public ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    private Timer t = Timers.createTimer(TimerActions.tickingdrawing, 50);

    public void addGameObject(GameObject object)
    {
        gameObjects.add(object);
    }

    public void tick()
    {
        for (int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.get(i).tick(gameObjects);
        }
    }

    public void render()
    {
        for (int i = 0; i < gameObjects.size(); i++)
        {
            gameObjects.get(i).render();
        }
    }


}
