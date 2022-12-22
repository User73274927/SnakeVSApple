package com.example.snake_demo.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private final int beginX, beginY;
    private final List<Rectangle> body;
    private int x, y;
    private int speed;
    private int body_size;
    private int direction = LEFT;

    public final static int UP = 0;
    public final static int DOWN = 1;
    public final static int LEFT = 3;
    public final static int RIGHT = 4;

    private Color color_fill = Color.GREEN;
    private Color color_stroke = Color.BLACK;

    public Snake(int body_size, int beginX,
                            int beginY) {
        this.beginX = beginX;
        this.beginY = beginY;
        this.x = beginX;
        this.y = beginY;

        this.body = new LinkedList<>();
        this.body_size = body_size;
        this.speed = body_size;

        addBody();
        body.get(0).setX(x);
        body.get(0).setY(y);

    }

    public Snake(int speed) {
        this(speed, 0, 0);
    }

    public void drawSnake(GraphicsContext context, int screen_size) {
        move(screen_size); update();
        for (Rectangle bd : body) {
            context.setFill(color_stroke);
            context.fillRect((int) bd.getX(), (int) bd.getY(), body_size, body_size);
            context.setFill(color_fill);
            context.fillRect((int) bd.getX() + 2, (int) bd.getY() + 2, body_size - 4, body_size - 4);
        }
    }

    public final void update() {
        int x = this.x, y = this.y, p_x, p_y;
        for (Rectangle r : body) {
            p_x = (int) r.getX();
            p_y = (int) r.getY();
            r.setX(x); r.setY(y);
            x = p_x;
            y = p_y;
        }
    }

    public void addBody() {
        Rectangle new_body = new Rectangle();
        new_body.setStroke(color_stroke);
        new_body.setStrokeWidth(0.8);
        new_body.setFill(color_fill);
        body.add(new_body);
    }

    public void setDirection(int direction) {
        if (Math.abs(this.direction - direction) != 1)
            this.direction = direction;
    }

    public void move(int screen_size) {
        switch (direction) {
            case UP -> moveUp(screen_size);
            case DOWN -> moveDown(screen_size);
            case LEFT -> moveLeft(screen_size);
            case RIGHT -> moveRight(screen_size);
        }
    }

    public void restart() {
        body.clear();
        setDirection(RIGHT);
        addBody();
        x = beginX;
        y = beginY;
    }

    public List<Rectangle> getBody() {
        return body;
    }

    public int getSnakeX() {
        return (int) body.get(0).getX();
    }

    public int getSnakeY() {
        return (int) body.get(0).getY();
    }

    public int getBodySize() {
        return body.size();
    }

    public void moveUp(int screen_size) {
        if (y <= 0)
            y = screen_size;
        else y -= speed;
    }

    public void moveDown(int screen_size) {
        if (y >= screen_size)
            y = 0;
        else y += speed;
    }

    public void moveLeft(int screen_size) {
        if (x <= 0)
            x = screen_size + body_size;
        else x -= speed;
    }

    public void moveRight(int screen_size) {
        if (x >= screen_size - body_size)
            x = 0;
        else x += speed;
    }
}
