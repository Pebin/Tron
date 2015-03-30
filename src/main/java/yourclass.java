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

        players.add(new Player(new Tuple(40, 40), Direction.LEFT, Color.GREEN));
        players.add(new Player(new Tuple(600, 440), Direction.RIGHT, Color.RED));
        players.add(new Player(new Tuple(300, 200), Direction.RIGHT, Color.BLUE));


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
            player.path.add(new Tuple(player.location.x, player.location.y));
        }
    }

    /**
     * Checks if some bike has collision.
     * @return
     */
    private boolean collision() {
        //3 ugly IFs because contains method didn't work
        for(Player player: players) {
            for(Player other: players) {
               for(Tuple<Integer, Integer> pair : other.path) {
                    if (player.location.equals(pair)) {
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
            case UP:
                if (player.location.y > 0){
                    player.location.y -= player.moveAmount;
                } else {
                    player.location.y = sm.getHeight();
                }
                break;
            case RIGHT:
                if (player.location.x < sm.getWidth()){
                    player.location.x += player.moveAmount;
                } else {
                    player.location.x = 0;
                }
                break;
            case DOWN:
                if (player.location.y < sm.getHeight()){
                    player.location.y += player.moveAmount;
                } else {
                    player.location.y = 0;
                }
                break;
            case LEFT:
                if (player.location.x > 0){
                    player.location.x -=player.moveAmount;
                } else {
                    player.location.x = sm.getWidth();
                }
                break;
        }
    }

	public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
        for(int i = 0;i< players.size();i++) {
            int tmp = 0;
            for(Integer key : Controls.controls.get(i)) {
                if(e.getKeyCode() == key) {
                    changeDirection(players.get(i), tmp);
                }
                tmp++;
            }
        }
	}

    private void changeDirection(Player player, Integer keyPressed) {
        if(player.currentDirection.ordinal() != ((keyPressed + 2) % 4)) {
            player.currentDirection = parseDirection(keyPressed%4);
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
        if(e.getButton() == 1) {
            changeDirection(players.get(0), players.get(0).currentDirection.ordinal() - 1);
        } else if (e.getButton() == 3) {
            changeDirection(players.get(0), players.get(0).currentDirection.ordinal() + 1);
        }

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
