package com.example.snake_demo.game;

import com.example.snake_demo.App;
import com.example.snake_demo.Window;
import com.example.snake_demo.controller.GameOverController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.swing.Timer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class SnakeField extends Pane {
    public final int scene_size;
    public final int dots; //Кол-во клеток по ширине
    public final int dot_size; //Размер одной клетки
    protected final Canvas canvas;

    protected final List<Apple> apples; //Массив яблок на поле
    protected Timer frame_updator; //Обновляет кадры
    protected Snake snake;
    private Color color_background = Color.WHITE;

    public SnakeField(int scene_size, int dots) {
        this.scene_size = scene_size;
        this.dots = dots;
        this.dot_size = scene_size / dots;

        apples = new ArrayList<>();
        canvas = new Canvas(scene_size, scene_size);
        snake = new Snake(this.dot_size,
                dots / 2 * dot_size,
                dots / 2 * dot_size
        );
        frame_updator = new Timer(200, e -> {
            paintFrame(canvas.getGraphicsContext2D());
        });

        getChildren().add(canvas);
    }

    //Отрисовка и проверка
    protected void paintFrame(GraphicsContext context) {
        paintBackGround(context);
        snake.drawSnake(context, scene_size);

        //Проверить на смерть змейки
        if (isDied()) {
            die();
        }

        for (Apple apple : apples) {
            apple.drawApple(context);
        }

        //Проверить на съедение яблока змейкой
        for (Apple apple : apples) {
            if (isAppleEat(apple)) {
                eat(apple);
                break;
            }
        }
    }

    protected void showGameOverScene() {
        Window window = Window.getWindow();
        frame_updator.stop();
        Platform.runLater(() -> {
            try {
                FXMLLoader gameOverView = new FXMLLoader(App.class.getResource("game-over-view.fxml"));
                Scene gameOverScene = new Scene(gameOverView.load(), scene_size, scene_size);
                gameOverView.<GameOverController>getController().getResultLabel().setText(getResult());
                window.setScene(gameOverScene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    //Закрашивает задний фон
    protected void paintBackGround(GraphicsContext context) {
        context.setFill(color_background);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    //проверка съел ли яблоко?
    protected boolean isAppleEat(Apple apple) {
        return (snake.getSnakeX() == apple.getX() &&
                snake.getSnakeY() == apple.getY());
    }

    //Проверка на смерть змейки
    protected boolean isDied() {
        for (int i = 1; i < snake.getBody().size(); i++)
            if (snake.getBody().get(0).getX() == snake.getBody().get(i).getX() &&
                    snake.getBody().get(0).getY() == snake.getBody().get(i).getY()) {
                return true;
            }
        return false;
    }

    //Клавиши управления
    public void keyPressed(KeyEvent e) {
        switch (e.getCode()) {
            case W -> {
                snake.setDirection(Snake.UP);
            }
            case S -> {
                snake.setDirection(Snake.DOWN);
            }
            case A -> {
                snake.setDirection(Snake.LEFT);
            }
            case D -> {
                snake.setDirection(Snake.RIGHT);
            }
            case C -> {
                frame_updator.stop();
            }
            case V -> {
                frame_updator.start();
            }
        }
    }

    //Условие на правильные координаты яблока на поле
    public boolean isOnFreeDot(int x, int y) {
        if (y < 0 && x < 0) {
            return (false);
        }
        if (y == snake.getSnakeY() || x == snake.getSnakeX()) {
            return (false);
        }
        for (Apple apl : apples) {
            if (x == apl.getX() && y == apl.getY()) {
                return (false);
            }
        }
        return (true);
    }

    public void startGame() {
        frame_updator.start();
    }

    public void stopGame() {
        frame_updator.stop();
    }

    public boolean isAppleOnFreeDot(Apple new_apple) {
        return (this.isOnFreeDot(new_apple.getX(), new_apple.getY()));
    }

    public void setColorBackground(Color color_background) {
        this.color_background = color_background;
    }

    public Snake getSnake() {
        return snake;
    }

    protected abstract void die();
    protected abstract void eat(Apple apple);
    public abstract String getResult();
}
