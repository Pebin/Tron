package main.java.model;


import java.awt.Color;
import java.util.ArrayList;
import java.util.EventListener;

/*
 @author Barton, Rajcan
 */
public class Player {

    private Tuple<Integer, Integer> location;
    private Direction currentDirection;
    private int moveAmount = 5;
    private Color color;
    private ArrayList<Tuple<Integer, Integer>> path = new ArrayList();
    private EventListener playerControl;

    public EventListener getPlayerControl() {
        return playerControl;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    public Tuple<Integer, Integer> getLocation() {
        return location;
    }

    public ArrayList<Tuple<Integer, Integer>> getPath() {
        return path;
    }

    public void extendPath(Tuple<Integer, Integer> newPoint) {
        this.path.add(newPoint);
    }

    public Color getColor() {
        return color;
    }

    public void assignControls(EventListener listener){
        playerControl = listener;
    }

    public void changeDirection(Integer keyPressed) {
        if (getCurrentDirection().ordinal() != ((keyPressed + 2) % 4)) {
            setCurrentDirection(parseDirection(keyPressed % 4));
        }
    }

    public void turnLeft(){
        changeDirection(getCurrentDirection().ordinal() - 1);
    }

    public void turnRight(){
        changeDirection(getCurrentDirection().ordinal() + 1);
    }

    /**
     * Parses placement of press key according to Controls class array of keys for each player
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

    public Player(Tuple<Integer, Integer> location, Direction currDirection, Color color) {
        this.location = location;
        currentDirection = currDirection;
        this.color = color;
    }
}
