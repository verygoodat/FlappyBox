package Application;

import Game.Handler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    private String screen = "menu";
    @FXML
    Button play;
    @FXML
    Button settings;
    @FXML
    Button records;
    @FXML
    Canvas menu = new Canvas(GameController.WIDTH,GameController.HEIGHT);
    public void initialize(URL url, ResourceBundle resourceBundle) {
    GraphicsContext gc = menu.getGraphicsContext2D();
    Image image = new Image("Sources/menu.jpg");
    gc.drawImage(image, 0,0,GameController.WIDTH, GameController.HEIGHT );


    }

    @FXML
    public void onPlay() throws Exception {
    GameController.run = true;
    Main.main.setScreen("game");
    Main.main.start(Main.primaryStage);
    }
    @FXML
    public void onSettings() throws Exception {
        Main.main.setScreen("settings");
        Main.main.start(Main.primaryStage);
    }
    @FXML
    public void onRecords() throws Exception {
        Main.main.setScreen("records");
        Main.main.start(Main.primaryStage);
    }

}
