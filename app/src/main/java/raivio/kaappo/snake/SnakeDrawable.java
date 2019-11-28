package raivio.kaappo.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Objects;
import java.util.Optional;

import raivio.kaappo.snake.misc.Point;

public class SnakeDrawable extends View {
    private static final int SQUARE_SIDE_LENGTH = 100;


    private Snake snake;
    private ShapeDrawable blankShape;
    private ShapeDrawable apple;
    private ShapeDrawable worm;

    public SnakeDrawable (Context context) {
        this(context, null);
    }

    public SnakeDrawable (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

//        drawable = new ShapeDrawable(new OvalShape());
//        drawable2 = new ShapeDrawable(new OvalShape());
//        drawable.getPaint().setColor(0xcafebabe);
//        drawable2.getPaint().setColor(0xffabcdef);
//
//        drawable.setBounds(0, 0, 640, 400);
//        drawable2.setBounds(640, 400, 680, 500);
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

    @Override
    protected void onDraw (Canvas canvas) {
        Objects.requireNonNull(snake);

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
