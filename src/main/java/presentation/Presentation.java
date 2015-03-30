package main.java.presentation;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import main.java.model.Player;

/**
 * Created by Michal on 30. 3. 2015.
 */
public class Presentation {
    public void draw(ScreenManager screenManager, List<Player> players) {
        Graphics2D graphics2D = screenManager.getGraphics();
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());

        printLines(graphics2D, players);

        graphics2D.dispose();
        screenManager.update();
    }

    /**
     * Print colored lines- walls that are created behind the tron vehicle
     */
    private void printLines(Graphics2D graphics2D, List<Player> players) {
        for(Player player: players) {
            for(int i = 0;i< player.getPath().size();i++) {
                graphics2D.setColor(player.getColor());
                graphics2D.fillRect(player.getPath().get(i).x, player.getPath().get(i).y, 10, 10);
            }
        }
    }
}
