package raivio.kaappo.snake;

import raivio.kaappo.snake.misc.Pair;
import raivio.kaappo.snake.misc.Point;

public class Snake {
    private int[][] matrix;
    private Point snakeHeadPosition;
    private Point snakeSpeed;

    public Snake (Point dimensions, Point startPosition) {
        matrix = new int[dimensions.getY()][dimensions.getX()];
        snakeSpeed = new Point(1, 0);
        initializeSnake(startPosition);
    }

    private void setSquare (Point point, int value) {
        matrix[point.getY()][point.getX()] = value;
    }

    private int getSquare (Point point) {
        return matrix[point.getY()][point.getX()];
    }

    private void initializeSnake (Point startPosition) {
        setSquare(startPosition, -1);
        snakeHeadPosition = startPosition;
    }

    private void tick () {
        
    }


}
