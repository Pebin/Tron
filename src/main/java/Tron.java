package main.java;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import main.java.Controlls.KeyboardControl;
import main.java.Controlls.MouseControl;
import main.java.gameEngine.TronEngine;
import main.java.gameEngine.GameEngine;
import main.java.model.Direction;
import main.java.model.Player;
import main.java.model.Tuple;
import main.java.presentation.Presentation;
import main.java.presentation.ScreenManager;

/*
 @author Barton, Rajcan
 */
public class Tron {
    private static Integer[] KEY_CONTROLS1 = new Integer[]{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};
    private static Integer[] KEY_CONTROLS2 = new Integer[]{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A};
    private static Integer[] KEY_CONTROLS3 = new Integer[]{KeyEvent.VK_NUMPAD8, KeyEvent.VK_NUMPAD6, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD4};
    private static Integer[] KEY_CONTROLS4 = new Integer[]{KeyEvent.VK_G, KeyEvent.VK_N, KeyEvent.VK_B, KeyEvent.VK_V};
    private static Integer[] KEY_CONTROLS5 = new Integer[]{KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J};

    private static Integer[] MOUSE_CONTROLS1 = new Integer[]{MouseEvent.BUTTON1, MouseEvent.BUTTON3};

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        Player p = new Player(new Tuple(40, 40), Direction.LEFT, Color.GREEN);
        players.add(p);
        p.assignControls(new MouseControl(p, MOUSE_CONTROLS1));

        Player p2 = new Player(new Tuple(600, 440), Direction.RIGHT, Color.RED);
        players.add(p2);
        p2.assignControls(new KeyboardControl(p2, KEY_CONTROLS2));

        //        players.add(new Player(new Tuple(300, 200), Direction.RIGHT, Color.BLUE));

        ScreenManager screenManager = new ScreenManager();
        Presentation presentation = new Presentation();

        GameEngine gameEngine = new TronEngine(presentation, screenManager, players);
        gameEngine.run();
    }
}
