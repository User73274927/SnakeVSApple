package com.example.snake_demo.controller;

import com.example.snake_demo.Window;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class GameOverController {
    Window window = Window.getWindow();

    @FXML
    private Label resultLabel; //Текст с результатом игры

    public void OnExitPressed(ActionEvent actionEvent) {
        Scene menuScene = window.getSceneStack().get("menuScene");
        window.setScene(menuScene);
    }

    public Label getResultLabel() {
        return resultLabel;
    }
}
