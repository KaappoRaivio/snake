package raivio.kaappo.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Objects;

import raivio.kaappo.snake.misc.Point;

public class SnakeDrawable extends View {
    private static int SQUARE_SIDE_LENGTH = 50;


    private Snake snake;
    private ShapeDrawable blankShape;
    private ShapeDrawable apple;
    private ShapeDrawable worm;

    public SnakeDrawable (Context context) {
        this(context, null);
    }

    public SnakeDrawable (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        blankShape = new ShapeDrawable(new RectShape());
        apple = new ShapeDrawable(new RectShape());
        worm = new ShapeDrawable(new RectShape());

        blankShape.getPaint().setColor(0xff221111);
        apple.getPaint().setColor(0xfffe1111);
        worm.getPaint().setColor(0xffa0a0a0);

    }

    public void attachSnakeInstance (Snake snake) {
        this.snake = snake;
    }

    public void reDraw () {
        invalidate();
    }

    private Rect rect = new Rect();

    @Override
    protected void onDraw (Canvas canvas) {
        Objects.requireNonNull(snake);

        canvas.getClipBounds(rect);

        int width = rect.width();
        int height = rect.height();


        int remainder = width % SQUARE_SIDE_LENGTH;
        float squareDimX = width / SQUARE_SIDE_LENGTH;
        int squareDimY = height / SQUARE_SIDE_LENGTH;
        SQUARE_SIDE_LENGTH += remainder / squareDimX;

        System.out.println(width + ", " + height + ", " + squareDimX + ", " + squareDimY);

        snake.resize(new Point((int) squareDimX, squareDimY));
        System.out.println(snake.dimY());

        for (int y = 0; y < snake.dimY(); y++) {
            for (int x = 0; x < snake.dimX(); x++) {
                int left = x * SQUARE_SIDE_LENGTH;
                int top = y * SQUARE_SIDE_LENGTH;
                int right = x * SQUARE_SIDE_LENGTH + SQUARE_SIDE_LENGTH;
                int bottom = y * SQUARE_SIDE_LENGTH + SQUARE_SIDE_LENGTH;

                switch (snake.getSquare(x, y)) {
                    case Snake.FOOD:
                        apple.setBounds(left, top, right, bottom);
                        apple.draw(canvas);
                        break;

                    case Snake.BLANK:
                        blankShape.setBounds(left, top, right, bottom);
                        blankShape.draw(canvas);
                        break;

                    default:
                        worm.setBounds(left, top, right, bottom);
                        worm.draw(canvas);
                        break;
                }
            }
        }
    }


}
