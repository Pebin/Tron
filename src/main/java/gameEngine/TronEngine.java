package main.java.gameEngine;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import main.java.presentation.ScreenManager;
import main.java.model.Controls;
import main.java.model.Direction;
import main.java.model.Player;
import main.java.model.Tuple;
import main.java.presentation.Presentation;

/**
 * Created by Michal on 30. 3. 2015.
 */
public class TronEngine extends GameEngine {
    private List<Player> players;

    public TronEngine(Presentation presentation, ScreenManager screenManager, List<Player> players) {
        super(presentation, screenManager);

        this.players = players;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            stop();
        }
        for (int i = 0; i < players.size(); i++) {
            int tmp = 0;
            for (Integer key : Controls.controls.get(i)) {
                if (e.getKeyCode() == key) {
                    changeDirection(players.get(i), tmp);
                }
                tmp++;
            }
        }
    }

    private void changeDirection(Player player, Integer keyPressed) {
        if (player.getCurrentDirection().ordinal() != ((keyPressed + 2) % 4)) {
            player.setCurrentDirection(parseDirection(keyPressed % 4));
        }
    }

    /**
     * Parses placement of press key according to Controls class array of keys for each player
     *
     */
    private Direction parseDirection(Integer keyPressed) {
        switch (keyPressed) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
        }
        return Direction.LEFT;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            changeDirection(players.get(0), players.get(0).getCurrentDirection().ordinal() - 1);
        } else if (e.getButton() == 3) {
            changeDirection(players.get(0), players.get(0).getCurrentDirection().ordinal() + 1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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
        w.addKeyListener(this);
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.WHITE);
        w.setForeground(Color.RED);
        w.setCursor(w.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
