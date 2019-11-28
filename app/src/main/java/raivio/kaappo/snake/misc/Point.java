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
}
