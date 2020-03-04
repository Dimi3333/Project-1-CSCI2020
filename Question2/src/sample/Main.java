package sample;

import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static java.lang.Math.pow;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Question 2");
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(10);
        pane.setStyle("-fx-background-color: lightgray");

        Text invAmntTxt = new Text("Investment Amount");
        pane.add(invAmntTxt,0,0);
        TextField invAmnt = new TextField();
        invAmnt.setAlignment(Pos.CENTER_RIGHT);
        pane.add(invAmnt, 1,0);

        Text yrsTxt = new Text("Years");
        pane.add(yrsTxt,0,1);
        TextField yrs = new TextField();
        yrs.setAlignment(Pos.CENTER_RIGHT);
        pane.add(yrs, 1,1);

        Text anlIntTxt = new Text("Annual Interest Rate");
        pane.add(anlIntTxt,0,2);
        TextField anlInt = new TextField();
        anlInt.setAlignment(Pos.CENTER_RIGHT);
        pane.add(anlInt, 1,2);

        Text ftrValTxt = new Text("Future Value");
        pane.add(ftrValTxt,0,3);
        TextField ftrVal = new TextField();
        ftrVal.setAlignment(Pos.CENTER_RIGHT);
        ftrVal.setEditable(false);
        ftrVal.setStyle("-fx-text-box-border: black");

        ftrVal.setBorder(Border.EMPTY);
        pane.add(ftrVal, 1,3);

        Button calculate = new Button("Calculate");
        calculate.setAlignment(Pos.CENTER_RIGHT);
        pane.add(calculate, 1, 4);

        calculate.setOnAction(e -> {
            int investment = Integer.parseInt(invAmnt.getText());
            int years = Integer.parseInt(yrs.getText());
            double interestRate = Double.parseDouble(anlInt.getText());

            double futureValue = investment * pow((1 + (interestRate)),years * 12);
            ftrVal.setText(String.valueOf(futureValue));
        });

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
