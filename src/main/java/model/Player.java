package main.java.model;

import java.awt.Color;
import java.util.ArrayList;

/*
 @author Barton, Rajcan
 */
public class Player {

    private Tuple<Integer, Integer> location;
    private Direction currentDirection;
    private int moveAmount = 5;
    private Color color;
    private ArrayList<Tuple<Integer, Integer>> path = new ArrayList();

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }

    public Tuple<Integer, Integer> getLocation() {
        return location;
    }

    public void setLocation(Tuple<Integer, Integer> location) {
        this.location = location;
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

    public void setColor(Color color) {
        this.color = color;
    }

    public Player(Tuple<Integer, Integer> location, Direction currDirection, Color color) {
        this.location = location;
        currentDirection = currDirection;
        this.color = color;
    }
}
