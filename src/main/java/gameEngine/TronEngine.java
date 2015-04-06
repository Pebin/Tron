package main.java.gameEngine;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.java.Controlls.KeyboardControl;
import main.java.Controlls.MouseControl;
import main.java.presentation.ScreenManager;
import main.java.model.Player;
import main.java.model.Tuple;
import main.java.presentation.Presentation;


/*
 @author Barton, Rajcan
 */
public class TronEngine extends GameEngine {
    private List<Player> players;


    public TronEngine(Presentation presentation, ScreenManager screenManager, List<Player> players) {
        super(presentation, screenManager);

        this.players = players;
    }
    @Override
    public void gameUpdate() {
        for (Player player : players) {
            updatePlayerPosition(player);
        }
        if (collides()) {
            stop();
        } else {
            addToPath();
            presentation.draw(screenManager, players);
        }
    }

    @Override
    public void init() {
        List<DisplayMode> displayModes = Arrays.asList(screenManager.getCompatibleDisplayModes());
        Collections.reverse(displayModes);
        DisplayMode dm = screenManager.findFirstCompatibaleMode((DisplayMode[]) displayModes.toArray());

        screenManager.setFullScreen(dm);
        Window w = screenManager.getFullScreenWindow();

        for (Player player : players){
            if (player.getPlayerControl() instanceof KeyboardControl) {
                w.addKeyListener((KeyListener) player.getPlayerControl());
            }
            else if (player.getPlayerControl() instanceof  MouseControl){
                w.addMouseListener((MouseListener) player.getPlayerControl());
            } else {
//                TODO: throw exception or something
            }

        }

        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
    }

    /**
     * Adds current updatePlayerPosition of tron vehicle into it's already passed path.
     */
    private void addToPath() {
        for (Player player : players) {
            player.extendPath(new Tuple(player.getLocation().x, player.getLocation().y));
        }
    }

    /**
     * Checks if some bike has collides.
     *
     * @return
     */
    private boolean collides() {
        for (Player player : players) {
            for (Player other : players) {
                for (Tuple<Integer, Integer> pair : other.getPath()) {
                    if (player.getLocation().equals(pair)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Moves player according its position.
     *
     * @param player
     */
    private void updatePlayerPosition(Player player) {
        switch (player.getCurrentDirection()) {
            case UP:
                if (player.getLocation().y > 0) {
                    player.getLocation().y -= player.getMoveAmount();
                } else {
                    player.getLocation().y = screenManager.getHeight();
                }
                break;
            case RIGHT:
                if (player.getLocation().x < screenManager.getWidth()) {
                    player.getLocation().x += player.getMoveAmount();
                } else {
                    player.getLocation().x = 0;
                }
                break;
            case DOWN:
                if (player.getLocation().y < screenManager.getHeight()) {
                    player.getLocation().y += player.getMoveAmount();
                } else {
                    player.getLocation().y = 0;
                }
                break;
            case LEFT:
                if (player.getLocation().x > 0) {
                    player.getLocation().x -= player.getMoveAmount();
                } else {
                    player.getLocation().x = screenManager.getWidth();
                }
                break;
        }
    }
}
