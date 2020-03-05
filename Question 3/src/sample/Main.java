package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static java.lang.Math.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 3");
        //create the pane
        Pane pane = new Pane();
        pane.setMinSize(350,350);

        //create the circle path
        Circle circle = new Circle(200,200,100, Color.WHITE);
        circle.setStroke(Color.BLACK);
        //create the points
        Circle dot1 = new Circle(circle.getCenterX(),circle.getCenterX()-circle.getRadius(),5, Color.RED);
        Circle dot2 = new Circle(circle.getCenterX(),circle.getCenterX()+circle.getRadius(),5, Color.BLUE);
        Circle dot3 = new Circle(circle.getCenterX()+circle.getRadius(),circle.getCenterY(),5, Color.YELLOW);
        //create all the lines between two points
        Line line12 = new Line();
        line12.setStartX(dot1.getCenterX());
        line12.setStartY(dot1.getCenterY());
        line12.setEndX(dot2.getCenterX());
        line12.setEndY(dot2.getCenterY());

        Line line23 = new Line();
        line23.setStartX(dot2.getCenterX());
        line23.setStartY(dot2.getCenterY());
        line23.setEndX(dot3.getCenterX());
        line23.setEndY(dot3.getCenterY());

        Line line13 = new Line();
        line13.setStartX(dot1.getCenterX());
        line13.setStartY(dot1.getCenterY());
        line13.setEndX(dot3.getCenterX());
        line13.setEndY(dot3.getCenterY());
        //create the angle texts
        //angle() calculates the angle for the first element
        Text ang12 = new Text(Integer.toString(angle(dot1, dot3, dot2)));
        Text ang23 = new Text(Integer.toString(angle(dot2, dot1, dot3)));
        Text ang13 = new Text(Integer.toString(angle(dot3, dot1, dot2)));

        ang12.setX(circle.getCenterX());
        ang12.setY(circle.getCenterY() - circle.getRadius() + 20);
        ang23.setX(circle.getCenterX());
        ang23.setY(circle.getCenterY() + circle.getRadius() - 20);
        ang13.setX(circle.getCenterX() + circle.getRadius() - 20);
        ang13.setY(circle.getCenterY());
        //add everything so far to the children of the pane
        pane.getChildren().addAll(circle,dot1,dot2,dot3,line12,line13,line23, ang12, ang13, ang23);

        //the code for mouse event below could have been made into a function to lessen clutter

        //moving the red dot
        dot1.setOnMouseDragged(e -> {
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D origin = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D newP = mouse.subtract(origin).normalize().multiply(circle.getRadius());
            dot1.setCenterX(newP.getX() + circle.getCenterX());
            dot1.setCenterY(newP.getY() + circle.getCenterY());

            Point2D newTextP = mouse.subtract(origin).normalize().multiply(circle.getRadius() - 20);
            ang12.setX(newTextP.getX() + circle.getCenterX());
            ang12.setY(newTextP.getY() + circle.getCenterY());

            //calls angle() for all dots and rewrites the texts
            setAngle(dot1, dot2, dot3, ang12, ang23, ang13);

            line12.setStartX(dot1.getCenterX());
            line12.setStartY(dot1.getCenterY());
            line13.setStartX(dot1.getCenterX());
            line13.setStartY(dot1.getCenterY());
        });

        //moving the blue dot
        dot2.setOnMouseDragged(e -> {
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D origin = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D newP = mouse.subtract(origin).normalize().multiply(circle.getRadius());
            dot2.setCenterX(newP.getX() + circle.getCenterX());
            dot2.setCenterY(newP.getY() + circle.getCenterY());

            Point2D newTextP = mouse.subtract(origin).normalize().multiply(circle.getRadius() - 20);
            ang23.setX(newTextP.getX() + circle.getCenterX());
            ang23.setY(newTextP.getY() + circle.getCenterY());

            setAngle(dot1, dot2, dot3, ang12, ang23, ang13);

            line23.setStartX(dot2.getCenterX());
            line23.setStartY(dot2.getCenterY());
            line12.setEndX(dot2.getCenterX());
            line12.setEndY(dot2.getCenterY());
        });
        //moving the yellow dot
        dot3.setOnMouseDragged(e -> {
            Point2D mouse = new Point2D(e.getX(), e.getY());
            Point2D origin = new Point2D(circle.getCenterX(), circle.getCenterY());
            Point2D newP = mouse.subtract(origin).normalize().multiply(circle.getRadius());
            dot3.setCenterX(newP.getX() + circle.getCenterX());
            dot3.setCenterY(newP.getY() + circle.getCenterY());

            Point2D newTextP = mouse.subtract(origin).normalize().multiply(circle.getRadius() - 20);
            ang13.setX(newTextP.getX() + circle.getCenterX());
            ang13.setY(newTextP.getY() + circle.getCenterY());

            setAngle(dot1, dot2, dot3, ang12, ang23, ang13);

            line13.setEndX(dot3.getCenterX());
            line13.setEndY(dot3.getCenterY());
            line23.setEndX(dot3.getCenterX());
            line23.setEndY(dot3.getCenterY());
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setAngle(Circle a, Circle b, Circle c, Text A, Text B, Text C){
        int ang = angle(a,b,c);
        A.setText(Integer.toString(ang));
        ang = angle(b,a,c);
        B.setText(Integer.toString(ang));
        ang = angle(c,b,a);
        C.setText(Integer.toString(ang));
    }

    private int angle(Circle a, Circle b, Circle c){
        Point2D pointA = new Point2D(a.getCenterX(), a.getCenterY());
        Point2D pointB = new Point2D(b.getCenterX(), b.getCenterY());
        Point2D pointC = new Point2D(c.getCenterX(), c.getCenterY());

        double sideAB = pointA.distance(pointB);
        double sideBC = pointB.distance(pointC);
        double sideAC = pointA.distance(pointC);
        double num = acos(((sideBC*sideBC) - (sideAC*sideAC) - (sideAB*sideAB))/(-2.0*sideAB*sideAC));

        return (int)(Math.toDegrees(num));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
