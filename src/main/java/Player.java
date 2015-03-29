package main.java;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Michal on 29. 3. 2015.
 */
public class Player {

    Tuple<Integer, Integer> location;
    Direction currentDirection;
    int moveAmount = 5;
    Color color;
    ArrayList<Tuple<Integer, Integer>> path = new ArrayList();

    public Player(Tuple<Integer, Integer> location, Direction currDirection, Color color) {
        this.location = location;
        currentDirection = currDirection;
        this.color = color;
    }
}
