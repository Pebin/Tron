package main.java.Controlls;

import main.java.model.Player;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by petr on 4/6/15.
 */
public class MouseControl implements MouseListener {
    private Player player;
    private Integer[] controls;

    public MouseControl(Player player, Integer[] controls) {
        this.player = player;
        this.controls = controls;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == controls[0]) {
            player.turnLeft();
        } else if (mouseEvent.getButton() == controls[1]) {
            player.turnRight();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
