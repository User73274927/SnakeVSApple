package com.example.snake_demo.game;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class SnakeQuestionField extends SnakeField {
    private Question[] quiz = new Question[5];
    private int quiz_num = 0;

    public SnakeQuestionField(int screen_size, int dots) {
        super(screen_size, dots);
        for (int i = 0; i < quiz.length; i++) {
            quiz[i] = new Question(this, "");
        }
        quiz[0].setQuestion("Какая Столица кореи?");
        quiz[0].setVar("Пусан", false);
        quiz[0].setVar("Сеул", true);
        quiz[0].setVar("Инчон", false);

        quiz[1].setQuestion("Поэтическое название Кореи (Как у Японии)?");
        quiz[1].setVar("Страна утренней свежести", true);
        quiz[1].setVar("Страна тысячи погод", false);
        quiz[1].setVar("Страна восходящего солнца", false);

        quiz[2].setQuestion("Традиционная одежда корейцев?");
        quiz[2].setVar("Ханбок", true);
        quiz[2].setVar("Кимоно", false);
        quiz[2].setVar("Ханьфу", false);

        quiz[3].setQuestion("Как называется корейский алфавит?");
        quiz[3].setVar("Хирагана", false);
        quiz[3].setVar("Катакана", false);
        quiz[3].setVar("Хангыль", true);

        quiz[4].setQuestion("Вам понравилась викторина?");
        quiz[4].setVar("Да", true);
        quiz[4].setVar("Нет", true);

        apples.addAll(quiz[quiz_num].getVars());
    }

    @Override
    protected void paintFrame(GraphicsContext context) {
        super.paintFrame(context);
        quiz[quiz_num].drawQuestion(context, new Font(dot_size / 2.0), 50 ,50);
    }

    @Override
    protected void die() {
        System.out.println("You lose");
        frame_updator.stop();
        showEndField();
    }

    @Override
    protected void eat(Apple apple) {
        apples.clear();
        if (quiz[quiz_num].checkAns(apple)) {
            snake.addBody();
        }
        if (quiz_num < quiz.length - 1) {
            apples.addAll(quiz[++quiz_num].getVars());
        } else {
            frame_updator.stop();
            showEndField();
        }
    }

    @Override
    public String getResult() {
        return (snake.getBody().size() - 2) + " / " + (quiz.length - 1);
    }

}
