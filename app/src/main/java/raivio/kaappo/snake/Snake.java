package raivio.kaappo.snake;

import java.util.Random;

import raivio.kaappo.snake.misc.Point;

public class Snake {
    public static final int BIG = 0x1234;

    public static final int FOOD = 1;
    public static final int BLANK = 0;

    private int[][] matrix;
    private Point dimensions;
    private Point snakeHeadPosition;
    private boolean isLost = false;
    private int score = 0;

    public Snake (Point dimensions, Point startPosition) {
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
        matrix[point.getY() % dimX()][point.getX() % dimY()] = value;
    }

    public void summonFood () {
        while (true) {
            int y = new Random().nextInt(matrix.length);
            int x = new Random().nextInt(matrix[y].length);

            if (getSquare(new Point(x, y)) == 0) {
                setSquare(new Point(x, y), FOOD);
                return;
            }

        }
    }

    private int getSquare (Point point) {
        return matrix[point.getY() % matrix.length][point.getX() % matrix[point.getY() % matrix.length].length];
    }

    public static void main (String[] args) {
        Snake snake = new Snake(new Point(10, 10), new Point(5, 5));

        int c = 0;
        while (true) {
            snake.step(new Point(FOOD, 0));
            System.out.println(snake);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (c % 2 == 0) {
                snake.eat();
                snake.step(new Point(0, FOOD));
            }

            c++;
        }
    }

    int getSquare (int x, int y) {
        return matrix[y % matrix.length][x % matrix[y % matrix.length].length];
    }

    public void step (Point direction) {
        Point newSquare = snakeHeadPosition.offset(direction);

        if (getSquare(newSquare) < 0 || isLost) {
            isLost = true;
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
                    setSquare(new Point(x, y), value - 1);
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

    public boolean isLost () {
        return isLost;
    }

    public int getScore () {
        return score;
    }

    public int[][] getMatrix () {
        return matrix;
    }

    public int dimX () {
        return dimensions.getX();
    }

    public int dimY () {
        return dimensions.getX();
    }
}
