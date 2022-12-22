package com.example.snake_demo;

import com.example.snake_demo.game.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    private Window window = Window.getWindow();
    public static int SCREEN_SIZE = 600; //Размер экрана (Квадратный экран)
    public static int DOTS = 25; //Кол-во клеток по длине игрового поля

    @Override
    public void init() throws Exception {
        super.init();

        //Загрузка fxml кода
        FXMLLoader menuView = new FXMLLoader(App.class.getResource("select-menu-view.fxml"));

        //Сцены UI
        Scene menuScene = new Scene(menuView.load(), SCREEN_SIZE, SCREEN_SIZE);

        //Сцены игрового процесса
        Scene snakeWordScene = new Scene(new SnakeWordField(SCREEN_SIZE, DOTS, "Hello!"), SCREEN_SIZE, SCREEN_SIZE);
        Scene snakeQuestionScene = new Scene(new SnakeQuestionField(SCREEN_SIZE, DOTS), SCREEN_SIZE, SCREEN_SIZE);
        Scene snakeClassicScene = new Scene(new SnakeClassicField(SCREEN_SIZE, DOTS), SCREEN_SIZE, SCREEN_SIZE);

        //Добавление всех сцен в словарь
        window.getSceneStack().put("menuScene", menuScene);
        window.getSceneStack().put("snakeQuestionScene", snakeQuestionScene);
        window.getSceneStack().put("snakeWordScene", snakeWordScene);
        window.getSceneStack().put("snakeClassicScene", snakeClassicScene);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage = window;
        stage.setResizable(false);
        stage.setScene(window.getSceneStack().get("menuScene"));
        stage.setTitle("Snake VS Apple");
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }

    public static Scene getSnakeClassicScene() {
        return new Scene(new SnakeClassicField(SCREEN_SIZE, DOTS), SCREEN_SIZE, SCREEN_SIZE);
    }

    public static Scene getSnakeQuestionScene() {
        return new Scene(new SnakeQuestionField(SCREEN_SIZE, DOTS), SCREEN_SIZE, SCREEN_SIZE);
    }

    public static Scene getSnakeWordScene(String text) {
        return new Scene(new SnakeWordField(SCREEN_SIZE, DOTS, text), SCREEN_SIZE, SCREEN_SIZE);
    }

    public static void main(String[] args) {
        launch();
    }
}