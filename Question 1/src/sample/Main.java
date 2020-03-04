package sample;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static java.lang.Math.random;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 1");
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);

        int[] chosenCards = new int[3];
        boolean check;

        for(int i = 0; i < chosenCards.length; i++) {
            int card = 0;
            for(int k=0; k < k+1; k++) {
                check = true;
                //generate random number
                card = (int) (1 + 52 * random());
                //check if it is unique
                for(int chosen:chosenCards) {
                    if(card == chosen){
                        check = false;
                        break;
                    }
                }
                if (check){
                    break;
                }
            }

            chosenCards[i] = card;
            System.out.println(card);
            FileInputStream fileInput = new FileInputStream("Cards/" + card + ".png");
            Image image = new Image(fileInput);
            ImageView cardImg = new ImageView(image);
            pane.add(cardImg, i, 0);
        }

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
