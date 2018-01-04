package Application;

import Game.*;
import Levels.GreenField;
import Levels.Level;
import Levels.LevelLoader;
import com.sun.media.jfxmedia.events.PlayerEvent;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Header1_1Impl;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.WindowEvent;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class GameController implements Initializable, KeyController{
    public static final double WIDTH = 800;
    public static final double HEIGHT = 600;
    public static GameController gamecontroller;
    public static boolean run = false;
    public static boolean loaded = false;
    private boolean gameover = true;
    public int currentlevel = 1;
    public int currentlife = 3;
    public int currenttime;
    public int bullets = 10;
    public int score = 0;
    @FXML
    public Canvas game = new Canvas(WIDTH, HEIGHT);
    @FXML
    public Canvas character = new Canvas(WIDTH, HEIGHT);
    @FXML
    public Canvas pre = new Canvas(WIDTH, HEIGHT);
    private Timer time = new Timer();
    public static GraphicsContext gc;
    public AudioClip back = new AudioClip(this.getClass().getResource("/Sources/Fire.wav").toString());
    double opacity = 1;
    public GameObject object;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Game was loaded");
        gamecontroller = this;
        GameObject.draw = character.getGraphicsContext2D();
        Level.gc = game.getGraphicsContext2D();

        gc = pre.getGraphicsContext2D();
        Level lvl = LevelLoader.loadLevel(currentlevel);
        if (!loaded)
        {
            lvl.loadLevel();
        }
        lvl.drawBack();
        delay();
        back.play();
        Main.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                Main.primaryStage.close();
                System.exit(0);
                System.out.print("STOPPED");
            }
        });
    loaded = true;

    }

    @Override
    public void keyboard(KeyEvent e) {
        if (!gamecontroller.gameover)
        for (int i = 0; i < Handler.handler.gameObjects.size(); i++)
        {   object = Handler.handler.gameObjects.get(i);

            if (object.getId() == Id.player)
            switch (e.getCode()) {
                case UP:
                    object.setVelY(-15);
                    File s = new File("src/Sources/wing.wav");
                    Media sound = new Media(s.toURI().toString());
                    MediaPlayer player = new MediaPlayer(sound);
                    player.play();
                    break;
                case P:
                    if (run)
                        setState(0);
                    else
                        setState(1);
                    break;
//                case ESCAPE:
//                    Timers.stopTimers();
//                    loaded = false;
//                    gameover = true;
//                    run = false;
//                    back.stop();
//                    Level.gc.clearRect(0,0,WIDTH,HEIGHT);
//                    GameObject.draw.clearRect(0,0,WIDTH,HEIGHT);
//                    gc.clearRect(0,0,WIDTH,HEIGHT);
//                    gc = null;
//                    gamecontroller = null;
//                    Handler.handler.gameObjects.clear();
//                    Main.main.setScreen("menu");
//                    try {
//                        Main.main.start(Main.primaryStage);
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                    break;
                case LEFT:
//                    object.setVelX(-2);

                    break;
                case RIGHT:

                    break;
                case SPACE:
                    File sf = null;
                    if (object.bullets > 1)
                    { Handler.handler.addGameObject(new Bullet
                            (object.getX()+60, object.getY()+25,
                                    Id.bullet));
                    object.bullets--;
                    setBullets();
                    sf = new File("src/Sources/fireball.wav");
                    }
                    Media play = new Media(sf.toURI().toString());
                    MediaPlayer pl = new MediaPlayer(play);
                    pl.play();

                    break;
                case R:
                   //Another sound
                    TimerActions.recharging.run();
                    break;
//                case R: //Doesn't work right
//                    loaded = false;
//                    Handler.handler.gameObjects.clear();
//                    Timers.stopTimers();
//                    Level.gc.clearRect(0,0,WIDTH,HEIGHT);
//                    GameObject.draw.clearRect(0,0,WIDTH,HEIGHT);
//                    run = true;
//                    back.stop();
//                    Main.main.setScreen("game");
//                    try {
//                        Main.main.start(Main.primaryStage);
//                    } catch (Exception e1) {
//                        e1.printStackTrace();
//                    }
//                    break;
            }

        }
        else
            switch (e.getCode())
            {
                case ESCAPE:
                    Timers.stopTimers();
                    loaded = false;
                    gameover = true;
                    run = false;
                    back.stop();
                    Level.gc.clearRect(0,0,WIDTH,HEIGHT);
                    GameObject.draw.clearRect(0,0,WIDTH,HEIGHT);
                    gc.clearRect(0,0,WIDTH,HEIGHT);
                    gc = null;
                    gamecontroller = null;
                    Handler.handler.gameObjects.clear();
                    Main.main.setScreen("menu");
                    try {
                        Main.main.start(Main.primaryStage);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
            }

    }
    public void delay()
    {
Timers.stopTimers();
        time.schedule(new TimerTask() {
            int a = 4;
            GraphicsContext pr = gc;
            @Override
            public void run() {

                pr.setFill(Color.rgb(0,0,0, 0.5));
                pr.clearRect(0,0,WIDTH,HEIGHT);
                pr.fillRect(0,0,WIDTH, HEIGHT);
                Font f = Font.loadFont
                        (GameController.class.getResource("/Sources/tf2build.ttf")
                        .toExternalForm(), 80);

                pr.setFont(f);
                pr.setFill(Color.WHITE);

                if (a > 1)
                    pr.fillText(String.valueOf(--a), WIDTH/2, HEIGHT/2);
                else
                {
                pr.clearRect(0,0,WIDTH,HEIGHT);
                Timers.startTimers();
                lifes(currentlife);
                gameover = false;
                    time.cancel();
                }
                }
        },0,1000);


    }

    public void setState(Integer state)
    {
        GraphicsContext pr = gc;
        System.out.print("pr = "+pr);

        Font f = Font.loadFont
                (GameController.class.getResource("/Sources/tf2build.ttf")
                        .toExternalForm(), 50);
        pr.setFont(f);
        String stat;
        switch (state)
        {
            case 0://pause
                Timers.stopTimers();
                run = false;
                pr.setFill(Color.rgb(0,0,0,0.5));
                pr.clearRect(0,0,pre.getWidth(), pre.getHeight());
                pr.fillRect(0,0,pre.getWidth(), pre.getHeight());
                pr.setFill(Color.WHITE);
                stat = "ПАУЗА";
                pr.fillText(stat, WIDTH/2-90, HEIGHT/2);
                pr.fillText( "\nPOINTS: "+gamecontroller.score, WIDTH/2-170, HEIGHT/2);
                break;
            case 1: //unpause
                Timers.startTimers();
                run = true;
                pr.clearRect(0,0,WIDTH, HEIGHT);
                lifes(currentlife);
                break;
            case 2://pause
                Timers.stopTimers();
                pr.setFill(Color.rgb(0,0,0,0.5));
                pr.clearRect(0,0,pre.getWidth(), pre.getHeight()-50);
                pr.fillRect(0,0,pre.getWidth(), pre.getHeight());
                pr.setFill(Color.WHITE);
                stat = "КОНЕЦ ИГРЫ!";
                pr.fillText(stat, WIDTH/2-150, HEIGHT/2);
                pr.fillText( "\nPOINTS: "+gamecontroller.score, WIDTH/2-170, HEIGHT/2);
                gameover = true;
                TextInputDialog tid = new TextInputDialog("Ваше имя");
                tid.setTitle("Таблица рекордов");
                tid.setHeaderText("Вы побили чей-то рекорд!");
                tid.setContentText("Введите ваше имя: ");
                tid.showAndWait();
                break;
        }


    }
    public Level changeLevel()
    {
        Level lvl = LevelLoader.loadLevel(currentlevel);
        currentlevel++;
//        Handler.handler.play();
        return lvl;
    }

    public void lifes(int currentlife)
    {   Image heart = null;
        GraphicsContext pr = gc;
        heart = new Image("Sources/Heart/frame-"+1+".png");

        Font f = Font.loadFont
                (GameController.class.getResource("/Sources/tf2build.ttf")
                        .toExternalForm(), 30);
        pr.setFont(f);
        pr.setFill(Color.BLACK);
        pr.clearRect(0,HEIGHT-50 , WIDTH, HEIGHT);
        pr.fillText(String.valueOf(currentlife), WIDTH - 70, HEIGHT - 15);
        pr.drawImage(heart, WIDTH-45, HEIGHT-45, 40, 40);
        heart = new Image("Sources/fire.png");
        pr.drawImage(heart, 20, HEIGHT - 45, 40, 40);
        pr.fillText(String.valueOf(GreenField.checkBullets()), 70, HEIGHT - 15);
        pr.fillText(String.valueOf(gamecontroller.score), 380, HEIGHT-15);

    }
    public void  setBullets()
    {
        GraphicsContext pr = gc;
        pr.clearRect(70,HEIGHT-50 , 50, HEIGHT);
        pr.fillText(String.valueOf(GreenField.checkBullets()), 70, HEIGHT - 15);
    }
    public void setLife(int currentlife)
    {
        this.currentlife = currentlife;
        lifes(currentlife);
    }
    public void setScore(int mod)
    {
        score = score + mod;
        System.out.print(score+":");
        lifes(currentlife);
    }
    @Override
    public void handle(KeyEvent keyEvent) {
        keyboard(keyEvent);
    }
}
