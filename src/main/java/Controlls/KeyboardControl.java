package main.java.Controlls;

import main.java.model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by petr on 4/6/15.
 */
public class KeyboardControl implements KeyListener {
    private Player player;
    private Integer[] controls;

    public KeyboardControl(Player player, Integer[] controls) {
        this.player = player;
        this.controls = controls;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int tmp = 0;
        for (Integer key : controls) {
            if (keyEvent.getKeyCode() == key) {
                player.changeDirection(tmp);
            }
            tmp++;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
