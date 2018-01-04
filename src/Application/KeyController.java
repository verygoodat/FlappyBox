package Application;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public interface KeyController extends EventHandler<KeyEvent> {
    public void keyboard(KeyEvent e);
}
