package main.java;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Created by Michal on 29. 3. 2015.
 */
public class Player {

    int centrex;
    int centrey;
    Direction currentDirection;
    int moveAmount = 5;
    Color color;
    ArrayList<Tuple<Integer, Integer>> path = new ArrayList();

    public Player(int centrex, int centrey, Direction currDirection, Color color) {
        this.centrex = centrex;
        this.centrey = centrey;
        currentDirection = currDirection;
        this.color = color;
    }
}
