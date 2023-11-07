package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {

    private final static int SCENE_HEIGHT = 500;
    private final static int SCENE_WIDTH = 500;
    int counter = 0;
    String counterString = String.valueOf(counter);
    Text counterText = new Text(10, 20, counterString);
    ImageView iv1 = new ImageView();
    Image image = new Image("file:polite-cat.png");


    @Override
    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, SCENE_HEIGHT, SCENE_WIDTH);
        Circle circle = new Circle(20, Color.PURPLE);
        circle.relocate(10,10);
        pane.getChildren().add(circle);
        Text text = new Text(10, 20, "Counter: ");
        text.relocate(2, 2);
        counterText.relocate(50,2);
        iv1.setImage(image);
        iv1.setFitWidth(80);
        iv1.setPreserveRatio(true);
        iv1.relocate(SCENE_WIDTH, SCENE_WIDTH - 100);
        Line topLine = new Line(0, 2, 80, 2);
        Line botLine = new Line(0, 20, 80, 20);
        pane.getChildren().add(text);
        pane.getChildren().add(counterText);
        pane.getChildren().add(iv1);
        pane.getChildren().add(topLine);
        pane.getChildren().add(botLine);

        stage.setScene(scene);
        stage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(25), new EventHandler<ActionEvent>() {
            double dx = 2;
            double dy = 5;
            @Override
            public void handle(ActionEvent actionEvent) {
                Bounds bounds = pane.getBoundsInLocal();

                circle.setLayoutX(circle.getLayoutX() + dx);
                circle.setLayoutY(circle.getLayoutY() + dy);

                if(circle.getLayoutX() <= (bounds.getMinX() + circle.getRadius()) ||
                        circle.getLayoutX() >= (bounds.getMaxX() - circle.getRadius())) {
                    pane.getChildren().remove(counterText);
                    counter++;
                    counterString = String.valueOf(counter);
                    counterText = new Text(10, 20, counterString);
                    counterText.relocate(50,2);
                    pane.getChildren().add(counterText);
                    dx = -dx;
                }

                if(circle.getLayoutY() <= (bounds.getMinY() + circle.getRadius()) ||
                        circle.getLayoutY() >= (bounds.getMaxY() - circle.getRadius())) {
                    pane.getChildren().remove(counterText);
                    counter++;
                    counterString = String.valueOf(counter);
                    counterText = new Text(10, 20, counterString);
                    counterText.relocate(50,2);
                    pane.getChildren().add(counterText);
                    iv1.relocate(circle.getLayoutX(), circle.getLayoutY());
                    dy = -dy;
                }

                /*

                text.setLayoutX(text.getLayoutX() + dx);
                text.setLayoutY(text.getLayoutY() + dy);

                if(text.getLayoutX() <= (bounds.getMinX() + text.getX()) ||
                        text.getLayoutX() >= (bounds.getMaxX() - text.getX())) {
                    System.out.println("Out of bounds X " + text.getLayoutX());

                    dx = -dx;
                }

                if(text.getLayoutY() <= (bounds.getMinY() + text.getY()) ||
                        text.getLayoutY() >= (bounds.getMaxY() - text.getY())) {
                    dy = -dy;
                }

                */
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch();
    }

}