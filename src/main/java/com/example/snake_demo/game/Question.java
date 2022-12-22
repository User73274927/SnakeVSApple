package com.example.snake_demo.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {
    private SnakeField pane;
    private final List<Apple> vars = new ArrayList<>();
    private final List<String> vars_answer = new ArrayList<>();
    private final List<Boolean> vars_bool = new ArrayList<>();
    private String question;

    public Question(SnakeField pane, String question) {
        this.pane = pane;
        this.question = question;
    }

    public void drawQuestion(GraphicsContext context, Font font, int x, int y) {
        int counter = 1;

        context.setFont(font);
        context.setFill(Color.BLACK);

        context.fillText(question, x, y);
        y += font.getSize() + 10;

        for (String text : vars_answer) {
            context.fillText(counter + ") " + text, x, y);
            y += font.getSize() + 10;
            counter++;
        }
    }

    public boolean checkAns(Apple answer) {
        return (vars_bool.get(Integer.parseInt(answer.getText()) - 1));
    }

    public void setVar(String ans, boolean isRight) {
        vars.add(createApple(vars.size() + 1));
        vars_answer.add(ans);
        vars_bool.add(isRight);
    }

    public Apple createApple(int num) {
        Random rd = new Random();
        Apple apple = new Apple(pane.dot_size, Integer.toString(num));
        for (;;) {
            int x = rd.nextInt(0, pane.dots) * pane.dot_size;
            int y = rd.nextInt(0, pane.dots) * pane.dot_size;

            if (pane.isOnFreeDot(x, y)) {
                apple.setX(x);
                apple.setY(y);
                break;
            }
        }
        return apple;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Apple> getVars() {
        return vars;
    }
}
