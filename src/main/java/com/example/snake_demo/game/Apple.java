package com.example.snake_demo.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Apple {
    private final int size;
    private int x, y;
    private String text;

    private final Font font;
    private Color color_stroke = Color.BLACK;
    private Color color_fill = Color.RED;

    public Apple(int size) {
        this.size = size;
        font = Font.font("arial", FontWeight.BOLD, size - 4);
    }

    public Apple(int size, String text) {
        this(size);
        this.text = text;
    }

    public void drawApple(GraphicsContext context) {
        context.setFill(color_stroke);
        context.fillRect(x, y, size, size);
        context.setFill(color_fill);
        context.fillRect(x + 2, y + 2, size - 4, size - 4);

        context.setFill(color_stroke);
        context.setFont(font);
        context.fillText(text, x + 2, y + font.getSize() + 2);
    }

    public String getText() {
        return text;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColorStroke(Color color_stroke) {
        this.color_stroke = color_stroke;
    }

    public void setColorFill(Color color_fill) {
        this.color_fill = color_fill;
    }
}
