package main.java.gameEngine;

import java.awt.*;
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
    protected GameEngine(Presentation presentation, ScreenManager screenManager) {
        this.presentation = presentation;
        this.screenManager = screenManager;
    }

    protected boolean running = true;
    protected Presentation presentation;
    protected ScreenManager screenManager;

    private void gameLoop(int refreshRate){
        while (running) {
            gameUpdate();

            try {
                Thread.sleep(refreshRate);
            } catch (Exception ex) {}
        }
    }

    public void run(){
        run(20);
    }

    public void run(int refreshRate){
        try{
            init();
            gameLoop(refreshRate);
        }finally{
            screenManager.restoreScreen();
        }
    }

    public void stop(){
        running = false;
    }


    public abstract void init();
    public abstract void gameUpdate();
}
