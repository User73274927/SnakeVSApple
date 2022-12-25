package com.example.snake_demo;

import com.example.snake_demo.game.SnakeField;

public class SnakeBuilder {
    public static SnakeField createSnakeField(SnakeField field) {
        field.setColorBackground(GameSettings.background_color);
        field.setColorAppleStroke(GameSettings.apple_stroke);
        field.setColorAppleFill(GameSettings.apple_fill);
        field.getSnake().setFill(GameSettings.snake_color_fill);
        field.getSnake().setStroke(GameSettings.snake_color_stroke);
        return field;
    }
}
