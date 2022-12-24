package com.example.snake_demo.controller;

import com.example.snake_demo.App;
import com.example.snake_demo.Window;
import com.example.snake_demo.game.SnakeField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class SelectMenuController {
    Window window = Window.getWindow();

    @FXML
    public void OnPlayQuestionPressed(ActionEvent actionEvent) {
        Scene playScene = App.getSnakeQuestionScene();
        SnakeField fn = (SnakeField) playScene.getRoot();
        window.setScene(playScene);
        playScene.setOnKeyPressed(fn::keyPressed);
        fn.startGame();
    }

    @FXML
    public void OnPlayWord1Pressed(ActionEvent actionEvent) {
        Scene playScene = App.getSnakeWordScene("Hello");
        SnakeField fn = (SnakeField) playScene.getRoot();
        window.setScene(playScene);
        playScene.setOnKeyPressed(fn::keyPressed);
        fn.startGame();
    }

    @FXML
    public void OnPlayClassicPressed(ActionEvent actionEvent) {
        Scene playScene = App.getSnakeClassicScene();
        SnakeField fn = (SnakeField) playScene.getRoot();
        window.setScene(playScene);
        playScene.setOnKeyPressed(fn::keyPressed);
        fn.startGame();
    }

    public void OnPlayWord2Pressed(ActionEvent actionEvent) {
        Scene playScene = App.getSnakeWordScene("할아버지 할머니");
        SnakeField fn = (SnakeField) playScene.getRoot();
        window.setScene(playScene);
        playScene.setOnKeyPressed(fn::keyPressed);
        fn.startGame();
    }
}
