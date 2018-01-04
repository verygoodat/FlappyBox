package Game;

import Application.GameController;
import javafx.geometry.Rectangle2D;

import java.util.ArrayList;

public class Block extends GameObject {

    public Block(double x, double y, Id id) {
        super(x, y, id);
    }

    @Override
    public void tick(ArrayList<GameObject> object) {

    }

    @Override
    public void render() {

    }

    @Override
    public Rectangle2D getBoundsTop() {
        return null;
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
