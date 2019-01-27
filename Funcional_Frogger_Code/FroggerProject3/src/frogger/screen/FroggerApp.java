package frogger.screen;

import frogger.screen.functions$;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FroggerApp extends Application {

    private AnimationTimer timer;

    private Pane root;

    private List<Rectangle> carsL=new ArrayList<>();
    private List<Rectangle> carsR=new ArrayList<>();
    private List<Double> carsLv = new ArrayList<>();
    private List<Double> carsRv = new ArrayList<>();
    private Rectangle Goal;
    private Rectangle LogsR;
    private Rectangle LogsL1;
    private Rectangle LogsL2;
    private List<Double> LogsRX;
    private List<Double> LogsLX;
    private List<Double>  CarsXL;
    private List<Double>  CarsXR;
    private Rectangle Lake;
    private Node frog;



    private Parent createContent() {
        for(int i =0;i<1000;i++) {
            carsR.add(new Rectangle(0, 0, 40, 40));
            carsR.get(i).setTranslateX(10000);
            carsR.get(i).setTranslateY(10000);
            carsR.get(i).setFill(Color.RED);
            carsL.add(new Rectangle(0, 0, 40, 40));
            carsL.get(i).setTranslateX(10000);
            carsL.get(i).setTranslateY(10000);
            carsL.get(i).setFill(Color.RED);
        }
        Goal=new Rectangle(0,0,50,50);
        Goal.setFill(Color.YELLOW);
        Goal.setTranslateX(400);
        Goal.setTranslateY(50);
        Lake=new Rectangle(0,0,800,335);
        Lake.setFill(new Color(63.0/255.0,72.0/255.0,204.0/255.0,1.0));
        LogsR=new Rectangle(0,0,300,90);
        LogsR.setTranslateY(168);
        LogsR.setTranslateX(0);
        LogsR.setFill(Color.BROWN);
        LogsL1=(new Rectangle(0,0,300,90));
        LogsL1.setTranslateY(224);
        LogsL1.setTranslateX(800);
        LogsL1.setFill(Color.BROWN);
        LogsL2=(new Rectangle(0,0,300,90));
        LogsL2.setTranslateY(280);
        LogsL2.setTranslateX(800);
        LogsL2.setFill(Color.BROWN);
        CarsXR =(functions$.MODULE$.Movecars(carsR,40.0));
        CarsXL = (functions$.MODULE$.Movecars(carsL,40.0));
        //Todo a função tem como output uma lista de double fazer setTranslate em todas contidas e que são diferentes no update na lISTA DEPOIS DO UPDATE SE DIFERENTE ADICIONAR AO PAINEL ,
        //DIZER TAmanho da lista e depois da anterior colocar ,fazer o mesmo com Log Fim.
        root = new Pane();
        root.setPrefSize(800, 600);
        frog = initFrog();
        BackgroundImage b1  = new BackgroundImage(new Image("background.png"),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        root.setBackground( new Background(b1));
        root.getChildren().add(LogsR);
        root.getChildren().add(LogsL1);
        root.getChildren().add(LogsL2);
        root.getChildren().addAll(carsR);
        root.getChildren().addAll(carsL);
        root.getChildren().addAll(frog);
        root.getChildren().add(Goal);
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                onUpdate();
            }
        };
        timer.start();

        return root;
    }

    private Rectangle initFrog() {

        Rectangle rect = new Rectangle(38, 38, Color.GREEN);
        rect.setTranslateY(600 - 39);
        return rect;
    }

    private void onUpdate() {
        Double RandomR = Math.random();
        Double RandomL = Math.random();
        Double RandomP= Math.random();
        System.out.print(RandomP);
        frog.setTranslateY(functions$.MODULE$.frogDeath(frog, Lake, LogsR,LogsL1,LogsL2, carsR, carsL, 560.0, frog.getTranslateY()));
        functions$.MODULE$.SpawnCarY(RandomR,carsR,0.01,true,7,9,RandomP);
        functions$.MODULE$.SpawnCarX(RandomR,carsR,0.01,true);
        functions$.MODULE$.SpawnCarY(RandomL,carsL,0.01,false,7,9,RandomP);
        functions$.MODULE$.SpawnCarX(RandomL,carsL,0.01,false);
        CarsXL=functions$.MODULE$.Movecars(carsL,-3.0);
        CarsXR=functions$.MODULE$.Movecars(carsR,3.0);

        functions$.MODULE$.LogMain(LogsR,true,4);
        functions$.MODULE$.LogMain(LogsL2,false,-2);
        functions$.MODULE$.LogMain(LogsL1,false,-5);

        functions$.MODULE$.frogDeath(frog, Lake, LogsR,LogsL1,LogsL2, carsR, carsL, 560.0, frog.getTranslateY());
        //CarsXR  = (functions$.MODULE$.Movecars(carsR,40.0));
        //CarsXL = (functions$.MODULE$.Movecars(carsL,40.0));
        functions$.MODULE$.Recursivemain(carsR,CarsXR);
        functions$.MODULE$.Recursivemain(carsL,CarsXL);
        functions$.MODULE$.Win(frog,Goal);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));

        stage.getScene().setOnKeyPressed(event -> {
            functions$.MODULE$.MainFrogMov(frog,event.getCode());
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

