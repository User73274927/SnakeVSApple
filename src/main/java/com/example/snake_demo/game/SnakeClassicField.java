package com.example.snake_demo.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

public class SnakeClassicField extends SnakeField {
    private Font font = new Font(dot_size);
    private int score;

    public SnakeClassicField(int screen_size, int dots) {
        super(screen_size, dots);
        this.score = 0;
        apples.add(createApple());
    }

    @Override
    protected void paintFrame(GraphicsContext context) {
        super.paintFrame(context);
        context.setFill(Color.BLACK);
        context.setFont(font);
        context.fillText("Очки: " + score, dot_size, dot_size * 2);
    }

    @Override
    protected void eat(Apple apple) {
        apples.remove(apple);
        apples.add(createApple());
        snake.addBody();
        score += 10;
    }

    @Override
    protected void die() {
        System.out.println("You lose");
        frame_updator.stop();
        showGameOverScene();
    }

    @Override
    public String getResult() {
        return "" + score;
    }

    public Apple createApple() {
        Random rd = new Random();
        Apple apple = new Apple(dot_size);
        apple.setX(rd.nextInt(0, dots) * dot_size);
        apple.setY(rd.nextInt(0, dots) * dot_size);
        return apple;
    }

}
