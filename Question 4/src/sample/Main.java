package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Collections.max;

public class Main extends Application {
    //Used to store frequency of letters
    Map<Character, Integer> letters = new HashMap<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 4");
        //initialize map letters
        initLetters();

        //hold the graph(center) file location(bottom)
        BorderPane out = new BorderPane();
        out.setMinSize(1000,400);
        out.setStyle("-fx-background-color: white");

        //hold the canvas in row 1 and text in row 2
        GridPane inner = new GridPane();
        inner.setMinSize(100,100);
        inner.setPadding(new Insets(10));
        inner.setHgap(20);
        inner.setVgap(10);

        //hold the text, textfield, and button
        HBox bottom = new HBox();
        bottom.setStyle("-fx-border-color: black");

        Text filenameTxt = new Text("filename");
        //default textfile
        TextField filelocation = new TextField("src/textFile.txt");
        HBox.setHgrow(filelocation, Priority.ALWAYS);
        Button view = new Button("View");

        bottom.setAlignment(Pos.BOTTOM_CENTER);
        bottom.getChildren().addAll(filenameTxt, filelocation, view);
        out.setBottom(bottom);
        out.setCenter(inner);
        Scene scene = new Scene(out);
        primaryStage.setScene(scene);
        primaryStage.show();

        filelocation.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                try {
                    count(new File(filelocation.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("File does not exist");
                }

                //Create graph
                graph(inner);
            }
        });

        view.setOnMouseClicked(e -> {
            //check if file exists
            try {
                count(new File(filelocation.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("File does not exist");
            }

            //Create graph
            graph(inner);
        });


    }

    private void graph(GridPane gp){
        int count = 0;
        double max = max(letters.values());
        Canvas canvas;
        GraphicsContext gc;

        for(char a:letters.keySet()) {
            canvas = new Canvas(15, gp.getHeight() - 50);
            gp.setHgrow(canvas, Priority.ALWAYS);
            gp.setVgrow(canvas, Priority.ALWAYS);
            gc = canvas.getGraphicsContext2D();
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(1);

            double num = letters.get(a) / max;
            double y = canvas.getHeight() * num;
            gc.strokeRect(0, y, 10, canvas.getHeight() - y);

            gp.add(canvas, count, 0);
            gp.add(new Text(Character.toString(a)), count, 1);
            count++;
        }
    }

    private void initLetters(){
        for(int i = 0; i < 26; i++) {
            letters.put((char) (65+i), 0);
        }
    }

    private void count(File file) throws Exception{
        Scanner input = new Scanner(file);
        String line;
        char c;
        while(input.hasNext()){
            line = input.next();
            line = line.toUpperCase();
            for(int i = 0; i < line.length(); i++){
                c = line.charAt(i);
                if(letters.containsKey(c)){
                    letters.put(c, letters.get(c) + 1);
                }
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
