package com.example.cent_bouton;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HelloApplication extends Application {
    public static int nombreEssais;
    @Override
    public void start(Stage stage) throws IOException {
        int nombre = new Random().nextInt(1, 100);
        System.out.println(nombre);
        System.out.println(nombre);


        ArrayList<Button> boutons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            boutons.add(new Button(String.valueOf(i + 1)));
            boutons.get(i).setId(String.valueOf(i + 1));
            boutons.get(i).setMinSize(50, 15);
        }

        GridPane grid = new GridPane(10, 10);
        int index = 0;
        for (int ver = 0; ver < 10; ver++) {
            for (int hor = 0; hor < 10; hor++) {
                grid.add(boutons.get(index++), hor, ver);
            }
        }

        grid.setPadding(new Insets(50));
        grid.setAlignment(Pos.TOP_CENTER);

        Text reponse = new Text("Premier essai:\t");
        reponse.setFont(Font.font(null, FontWeight.BLACK, 15));

        HBox ligneReponse = new HBox();
        ligneReponse.getChildren().add(reponse);

        ligneReponse.setAlignment(Pos.BOTTOM_CENTER);

        VBox boiteReponse = new VBox();
        boiteReponse.getChildren().addAll(grid, ligneReponse);

        EventHandler<ActionEvent> clickOnButton = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (nombreEssais >= 8) {
                    stage.close();
                }
                Button boutonClique = (Button) actionEvent.getSource();

                if (Integer.parseInt(boutonClique.getId()) < nombre) {
                    reponse.setText("Plus grand");
                    nombreEssais++;
                } else if (Integer.parseInt(boutonClique.getId()) > nombre) {
                    reponse.setText("Plus petit");
                    nombreEssais++;
                }
                else {
                    reponse.setText("Bonne réponse!");
                    boutonClique.setStyle("-fx-background-color: #4CAF50;");
                }
                System.out.println(nombreEssais);
            }
        };

        for (Button bouton: boutons) {
            bouton.setOnAction(clickOnButton);
        }

        Scene scene = new Scene(boiteReponse, 750, 750);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}