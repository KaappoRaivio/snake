package raivio.kaappo.snake.misc;

public class Point extends Pair<Integer, Integer> {
    public Point (int x, int y) {
        super(x, y);
    }

    public int getX () {
        return key;
    }

    public int getY () {
        return value;
    }

    public Point offsetX (int x) {
        return new Point(getX() + x, getY());
    }

    public Point offsetY (int y) {
        return new Point(getX(), getY() + y);
    }

    public Point offset (Point other) {
        return new Point(getX() + other.getX(), getY() + other.getY());
    }

    @Override
    public String toString () {
        return "(" + getX() + ", " + getY() + ")";
    }
}
