package main.java.gameEngine;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import main.java.presentation.ScreenManager;
import main.java.model.Player;
import main.java.presentation.Presentation;

/**
 * Created by Michal on 30. 3. 2015.
 */
public abstract class GameEngine implements KeyListener, MouseListener, MouseMotionListener {

    protected GameEngine(Presentation presentation, ScreenManager screenManager, List<Player> players) {
        this.presentation = presentation;
        this.screenManager = screenManager;
        this.players = players;
    }

    protected boolean running = true;
    protected Presentation presentation;
    protected ScreenManager screenManager;
    protected List<Player> players;

    public abstract void gameLoop();

    public void run(){
        try{
            init();
            gameLoop();
        }finally{
            screenManager.restoreScreen();
        }
    }

    public void stop(){
        running = false;
    }


    public abstract void init();
}
