package main.java;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import main.java.gameEngine.BGEngine;
import main.java.gameEngine.GameEngine;
import main.java.model.Direction;
import main.java.model.Player;
import main.java.model.Tuple;
import main.java.presentation.Presentation;
import main.java.presentation.ScreenManager;

public class Tron {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(new Tuple(40, 40), Direction.LEFT, Color.GREEN));
        players.add(new Player(new Tuple(600, 440), Direction.RIGHT, Color.RED));
        //        players.add(new Player(new Tuple(300, 200), Direction.RIGHT, Color.BLUE));

        ScreenManager screenManager = new ScreenManager();
        Presentation presentation = new Presentation();

        GameEngine gameEngine = new BGEngine(presentation, screenManager, players);
        gameEngine.run();
    }
}
