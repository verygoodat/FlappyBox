package Levels;

import Application.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GrayCity extends Level {
    @Override
    public void loadLevel() {
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0, 600,400);
    }

    @Override
    public void drawBack() {

    }
}
