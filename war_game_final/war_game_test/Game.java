// package ca.sheridancollege.project;

import java.util.ArrayList;

public abstract class Game {

    private final String name;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void play();

    public abstract void declareWinner();
}
