package raivio.kaappo.snake;

import java.util.Random;

import raivio.kaappo.snake.misc.Point;

public class Snake {
    private static final int BIG = 0x1234;

    static final int FOOD = 1;
    static final int BLANK = 0;
    public static final int GROWTH_WHEN_EATING = 5;

    private int[][] matrix;
    volatile private Point dimensions;
    private Point snakeHeadPosition;
    private boolean lost = false;
    private int score = 0;

    Snake (Point dimensions, Point startPosition) {
        matrix = new int[BIG][BIG];
        this.dimensions = dimensions;
        initializeSnake(startPosition);
    }

    private void initializeSnake (Point startPosition) {
        setSquare(startPosition, -FOOD);
        snakeHeadPosition = startPosition;
        summonFood();
    }

    private void setSquare (Point point, int value) {
        matrix[Math.abs(point.getY() % dimY())][Math.abs(point.getX() % dimX())] = value;
    }

    private void summonFood () {
        while (true) {
            int y = new Random().nextInt(dimY());
            int x = new Random().nextInt(dimX());

            if (getSquare(new Point(x, y)) == 0) {
                setSquare(new Point(x, y), FOOD);
                return;
            }

        }
    }

    private int getSquare (Point point) {
        return getSquare(point.getX(), point.getY());
    }

    int getSquare (int x, int y) {
        return matrix[Math.abs(y % dimY())][Math.abs(x % dimX())];
    }

    void step (Point direction) {
        Point newSquare = snakeHeadPosition.offset(direction);

        if (getSquare(newSquare) < 0 || lost) {
            System.out.println("LOST");
            lost = true;
            return;
        } else if (getSquare(newSquare) == FOOD) {
            eat();
            summonFood();
        }

        setSquare(newSquare, getSquare(snakeHeadPosition) - FOOD);
        snakeHeadPosition = newSquare;

        for (int y = 0; y < dimY(); y++) {
            for (int x = 0; x < dimX(); x++) {
                int value = getSquare(new Point(x, y));
                if (value < 0) {
                    setSquare(new Point(x, y), value + 1);
                }
            }
        }
    }

    private void eat () {
        score += 1;

        for (int y = 0; y < dimY(); y++) {
            for (int x = 0; x < dimX(); x++) {
                int value = getSquare(new Point(x, y));
                if (value < 0) {
                    setSquare(new Point(x, y), value - GROWTH_WHEN_EATING);
                }
            }
        }
    }

    @Override
    public String toString () {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < dimY(); y++) {
            for (int x = 0; x < dimX(); x++) {
                int value = getSquare(new Point(x, y));
                String current;
                switch (value) {
                    case 1:
                        current = "X";
                        break;
                    case 0:
                        current = ".";
                        break;
                    default:
                        current = "o";
                }

                builder.append(current).append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    boolean isLost () {
        return lost;
    }

    int getScore () {
        return score;
    }

    public int[][] getMatrix () {
        return matrix;
    }

    int dimX () {
        return dimensions.getX();
    }

    int dimY () {
        return dimensions.getY();
    }

    void resize (Point dimensions) {
        if (dimensions.getX() < matrix[0].length && dimensions.getY() < matrix.length) {
            System.out.println("Resizing! " + dimensions);
            this.dimensions = dimensions;
        }
    }
}
