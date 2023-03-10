package com.example.snake_demo.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

import java.util.Random;

public class SnakeWordField extends SnakeField {
    private final Font font = new Font(dot_size);
    private final Font question_font = new Font(dot_size / 2.0);
    private String question;
    private String word;
    private String player_word;

    public SnakeWordField(int screen_size, int dots, String word) {
        super(screen_size, dots);
        this.word = word;
        question = "";
        player_word = "";
    }

    @Override
    protected void paintFrame(GraphicsContext context) {
        super.paintFrame(context);
        context.setFont(font);
        context.fillText(player_word, dot_size, scene_size - dot_size * 2);
        context.setFont(question_font);
        context.fillText(question, 50, 50);
    }

    @Override
    protected void initEnvironment() {
        setDigitsOnField();
    }

    @Override
    protected void die() {
        snake.restart();
        setDigitsOnField();
        player_word = "";
    }

    @Override
    protected void eat(Apple apple) {
        player_word += apple.getText();
        apples.remove(apple);
        snake.addBody();

        if (apples.size() == 0) {
            if (word.equals(player_word)) {
                frame_updator.stop();
                showGameOverScene();
            } else {
                snake.restart();
                setDigitsOnField();
                player_word = "";
            }
        }
    }

    @Override
    public String getResult() { //Возврат результата игры
        return player_word;
    }

    private void setDigitsOnField() {
        Random rd = new Random();

        for (char ch : word.toCharArray()) {
            int x = -1, y = -1;

            while (!isOnFreeDot(x, y)){
                x = rd.nextInt(0, dots) * dot_size;
                y = rd.nextInt(0, dots) * dot_size;
            }

            Apple apple = new Apple(dot_size, "" + ch);
            apple.setX(x);
            apple.setY(y);
            addDefaultApple(apple);
        }
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
