package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    private static Parent root = null;
    public static Main main = new Main();
    public static Stage primaryStage;
    private String screen = "menu";
    public Scene game;
    private KeyController key;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Main.primaryStage = primaryStage;
        load(screen);
        primaryStage.setResizable(false);
        primaryStage.setTitle(screen);
        primaryStage.setScene(game);
        primaryStage.show();
    }
    public void setScreen(String screen)
    {
        this.screen = screen;

    }
    private void load(String screen) throws Exception {
            if (screen == "menu")
            {
                root = FXMLLoader.load(getClass().getResource("MenuScreen.fxml"));
                game = new Scene(root);
            }
        else if (screen == "game")
            {
                root = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
                game = new Scene(root);
                game.setOnKeyPressed(new GameController());
            }
        else if (screen == "records")
            {
                root = FXMLLoader.load(getClass().getResource("RecordsScreen.fxml"));
                game = new Scene(root);
                game.setOnKeyPressed(new RecordController());
            }
        else if (screen == "settings")
            {
                root = FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));
                game = new Scene(root);
                game.setOnKeyPressed(new SettingsController());
            }


    }
    public static void main(String[] args) {
        launch(args);
    }

}
