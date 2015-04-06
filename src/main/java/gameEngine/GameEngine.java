package main.java.gameEngine;

import java.awt.event.KeyListener;

import main.java.presentation.ScreenManager;
import main.java.presentation.Presentation;

/*
 @author Barton, Rajcan
 */
public abstract class GameEngine {
    protected GameEngine(Presentation presentation, ScreenManager screenManager) {
        this.presentation = presentation;
        this.screenManager = screenManager;
    }

    protected boolean running = true;
    protected Presentation presentation;
    protected ScreenManager screenManager;

    private void gameLoop(int refreshRate) {
        while (running) {
            gameUpdate();

            try {
                Thread.sleep(refreshRate);
            } catch (Exception ex) {
            }
        }
    }

    public void run() {
        run(20);
    }

    public void run(int refreshRate) {
        try {
            init();
            gameLoop(refreshRate);
        } finally {
            screenManager.restoreScreen();
        }
    }

    public void stop() {
        running = false;
    }


    public abstract void init();

    public abstract void gameUpdate();
}
