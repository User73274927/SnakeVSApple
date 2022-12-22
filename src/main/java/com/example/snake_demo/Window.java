package com.example.snake_demo;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Window extends Stage {
    private static Window window;
    private final Map<String, Scene> sceneStack;

    private Window() {
        super();
        sceneStack = new HashMap<>();
    }

    public static Window getWindow() {
        if (window == null) {
            window = new Window();
        }
        return window;
    }

    public Map<String, Scene> getSceneStack() {
        return sceneStack;
    }

}
