package com;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DictionaryApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        Font.loadFont(DictionaryApplication.class.getResourceAsStream("/font/SVN-A Love Of Thunder.ttf"), 10);
        Font.loadFont(DictionaryApplication.class.getResourceAsStream("/font/SVN-Poky_s.ttf"), 10);

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });


        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);

        stage.show();
    }

    public void runApplication(String[] args) {
        launch(args);
    }

    public static void main(String[] args)
    {
        new DictionaryApplication().runApplication(args);
    }
}
