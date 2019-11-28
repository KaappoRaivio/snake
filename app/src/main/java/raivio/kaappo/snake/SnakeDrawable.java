package raivio.kaappo.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SnakeDrawable extends View {
    private ShapeDrawable drawable;
    private ShapeDrawable drawable2;

    public SnakeDrawable (Context context) {
        this(context, null);
    }

    public SnakeDrawable (Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        drawable = new ShapeDrawable(new OvalShape());
        drawable2 = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(0xcafebabe);
        drawable2.getPaint().setColor(0xffabcdef);

        drawable.setBounds(0, 0, 640, 400);
        drawable2.setBounds(640, 400, 680, 500);

    }

    @Override
    protected void onDraw (Canvas canvas) {
        


        super.onDraw(canvas);
        drawable.draw(canvas);
        drawable2.draw(canvas);
    }
}
