package Levels;

import Application.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Level {
    public static double x = 30, y = 200;
    private int id = 1;
    public static GraphicsContext gc;
    public void setLevel(int id){this.id = id;};
    public int getLevel(){return id;};
    public abstract void loadLevel();
    public abstract void drawBack();
}
