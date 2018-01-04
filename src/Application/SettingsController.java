package Application;

import javafx.scene.input.KeyEvent;

public class SettingsController implements KeyController{
    @Override
    public void keyboard(KeyEvent e) {
        switch(e.getCode())
        {
            case ESCAPE:
                Main.main.setScreen("menu");
                try {
                    Main.main.start(Main.primaryStage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
    keyboard(keyEvent);
    }
}
