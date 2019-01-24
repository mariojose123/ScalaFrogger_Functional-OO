import frogger.screen.object.Car.Car;
import frogger.screen.object.Log.Lake;
import frogger.screen.object.Log.Log;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import frogger.screen.object.Frog.Frog;
import frogger.screen.gamescene.gamescene;

import java.time.LocalTime;



import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

import java.util.concurrent.TimeUnit;

import javafx.scene.input.KeyCode;


public class FroggerApp extends Application {

    private AnimationTimer timer;
    private LocalTime time;
    private Pane root;
    private Lake lake;
    private gamescene gamescene1;

    private Frog frog;
    private List<Car> cars = new ArrayList<>();
    private List<Log> logs = new ArrayList<>();

    private Parent createContent() {
        lake = new Lake((double) 800, (double) 335, Color.BLUE, (double) 3, (double) 5, Color.BROWN, (double) 90, (double) 300);
        lake.initNormalLake();
        root = new Pane();
        BackgroundImage b1 = new BackgroundImage(new Image("background1.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground(new Background(b1));
        root.setPrefSize(800, 600);
        gamescene1 = new gamescene(root, lake);
        frog = new Frog((double) 38, (double) 38, Color.GREEN);
        frog.move((double) 0, (double) 600 - 39);
        gamescene1.addObject(frog);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();

            }
        };
        timer.start();
        return gamescene1.getRoot();
    }

    private void onUpdate() {
        Task1 task1 = new Task1(gamescene1, cars);
        Task1 task3 = new Task1(gamescene1, cars);
        Task2 task2 = new Task2(gamescene1);
        task1.run();
        task3.run();
        task2.run();

        gamescene1.lake().Spawn();
        gamescene1.MoveFrogLake(frog, (double) frog.NodeX(), (double) frog.NodeY());

        checkState();
    }

    private void checkState() {
        gamescene1.FrogCheckState();
        if (gamescene1.win()) {
            timer.stop();
            String win = "YOU WIN";

            HBox hBox = new HBox();
            hBox.setTranslateX(300);
            hBox.setTranslateY(250);
            gamescene1.addHbox(hBox);

            for (int i = 0; i < win.toCharArray().length; i++) {
                char letter = win.charAt(i);

                Text text = new Text(String.valueOf(letter));
                text.setFont(Font.font(48));
                text.setOpacity(0);

                hBox.getChildren().add(text);

                FadeTransition ft = new FadeTransition(Duration.seconds(0.66), text);
                ft.setToValue(1);
                ft.setDelay(Duration.seconds(i * 0.15));
                ft.play();
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    gamescene1.MoveFrogLake(frog, (double) frog.NodeX(), (double) frog.NodeY() - 40);
                    break;
                case S:
                    gamescene1.MoveFrogLake(frog, (double) frog.NodeX(), (double) frog.NodeY() + 40);
                    break;
                case A:
                    gamescene1.MoveFrogLake(frog, (double) frog.NodeX() - 40, (double) frog.NodeY());
                    break;
                case D:
                    gamescene1.MoveFrogLake(frog, (double) frog.NodeX() + 40, (double) frog.NodeY());
                    break;
                default:
                    break;
            }
        });
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    }

    class Task1 extends Thread {
        public gamescene gamescene1;
        public List<Car> cars;

        Task1(gamescene g, List<Car> car) {
            this.gamescene1 = g;
            this.cars = car;
        }

        @Override
         public synchronized void run() {
            gamescene1.SpawnCars();
        }
    }

    class Task2 extends Thread {
        public gamescene gamescene1;

        Task2(gamescene g) {
            this.gamescene1 = g;
        }

        @Override
        public  void run() {
            gamescene1.movelogs(10.0);
        }
    }