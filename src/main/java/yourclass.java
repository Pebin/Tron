package main.java;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class yourclass extends Core implements KeyListener, MouseListener,
		MouseMotionListener {
	ArrayList<Player> players = new ArrayList<Player>();

	public void init() {
		super.init();

        players.add(new Player(40, 40, Direction.LEFT, Color.GREEN));
        players.add(new Player(600, 440, Direction.RIGHT, Color.RED));


		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		new yourclass().run();
	}

	public void draw(Graphics2D g) {
	    for(Player player: players) {
            position(player);
        }
        if (collision()) {
            System.exit(0);
	    }

        addToPath();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, sm.getWidth(), sm.getHeight());

        printLines(g);
	}

    /**
     * Print colored lines- walls that are created behind the tron vehicle
     * @param g
     */
    private void printLines(Graphics2D g) {
        for(Player player: players) {
            for(int i = 0;i< player.path.size();i++) {
                g.setColor(player.color);
                g.fillRect(player.path.get(i).x, player.path.get(i).y, 10, 10);
            }
        }
    }

    /**
     * Adds current position of tron vehicle into it's already passed path.
     */
    private void addToPath() {
        for(Player player: players) {
            player.path.add(new Tuple(player.centrex, player.centrey));
        }
    }

    /**
     * Checks if some bike has collision.
     * @return
     */
    private boolean collision() {
        for(Player player: players) {
            for(int i = players.indexOf(player); i < players.size(); i++) {
                for(Tuple position : players.get(i).path) {
                    if (player.centrex == position.x && player.centrey == position.y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Moves player according its position.
     * @param player
     */
    private void position(Player player) {
        switch(player.currentDirection){
            case DOWN:
                if (player.centrey > 0){
                    player.centrey -= player.moveAmount;
                } else {
                    player.centrey = sm.getHeight();
                }
                break;
            case RIGHT:
                if (player.centrex < sm.getWidth()){
                    player.centrex +=player.moveAmount;
                } else {
                    player.centrex = 0;
                }
                break;
            case UP:
                if (player.centrey < sm.getHeight()){
                    player.centrey += player.moveAmount;
                } else {
                    player.centrey = 0;
                }
                break;
            case LEFT:
                if (player.centrex > 0){
                    player.centrex -=player.moveAmount;
                } else {
                    player.centrex = sm.getWidth();
                }
                break;
        }
    }

	public void keyPressed(KeyEvent e) {
		for(int i = 0;i< players.size();i++) {
            for(Integer key : Controls.controls.get(i)) {
                if(e.getKeyCode() == key) {
                    changeDirection(players.get(i), key);
                }
            }
        }
	}

    private void changeDirection(Player player, Integer keyPressed) {
        if(player.currentDirection.ordinal() != (keyPressed+2) % 4) {
            player.currentDirection = parseDirection(keyPressed);
        }
    }

    /**
     * Parses placement of presse key according to Controls class array of keys for each player
     * @param keyPressed
     * @return
     */
    private Direction parseDirection(Integer keyPressed) {
        switch(keyPressed) {
            case 0: return Direction.UP;
            case 1: return Direction.RIGHT;
            case 2: return Direction.DOWN;
        }
        return Direction.LEFT;
    }

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}
}
