package main.java.model;

/**
 * Created by Michal on 29. 3. 2015.
 */
public class Tuple<X, Y> {
    public X x;
    public Y y;
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public void setX(X x) {
        this.x = x;
    }

    public Y getY() {
        return y;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public boolean equals(Tuple<X,Y> o) {
        if (o == null) {
            return false;
        }
        Tuple<Integer,Integer> obj = (Tuple<Integer, Integer>) o;
        if(this.x.equals(obj.x)) {
            if(this.y.equals(obj.y)) {
                return true;
            }
        }
            return false;
    }
}
