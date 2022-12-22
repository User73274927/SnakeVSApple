module com.example.snake_demo {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake_demo to javafx.fxml;
    exports com.example.snake_demo;
    exports com.example.snake_demo.controller;
    opens com.example.snake_demo.controller to javafx.fxml;
}