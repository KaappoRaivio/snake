package raivio.kaappo.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

import org.w3c.dom.Text;

import raivio.kaappo.snake.misc.OnSwipeTouchListener;
import raivio.kaappo.snake.misc.Point;

public class GameActivity extends AppCompatActivity {
    private SnakeDrawable drawable;
    private Game game;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        game = new Game(findViewById(R.id.text), this, new Point(10, 10), new Point(5, 5));

        drawable = findViewById(R.id.snake);
        drawable.attachSnakeInstance(game.getSnake());

        drawable.setOnTouchListener(new OnSwipeTouchListener(getApplicationContext()) {
            @Override
            public void onSwipeRight () {
                game.updateMovement(new Point(1, 0));

            }

            @Override
            public void onSwipeLeft () {
                game.updateMovement(new Point(-1, 0));
            }

            @Override
            public void onSwipeTop () {
                game.updateMovement(new Point(0, -1));
            }

            @Override
            public void onSwipeBottom () {
                game.updateMovement(new Point(0, 1));
            }

        });

        game.execute();
    }

    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                game.updateMovement(new Point(0, -1));
                return true;
            case KeyEvent.KEYCODE_S:
                game.updateMovement(new Point(0, 1));
                return true;
            case KeyEvent.KEYCODE_A:
                game.updateMovement(new Point(-1, 0));
                return true;
            case KeyEvent.KEYCODE_D:
                game.updateMovement(new Point(1, 0));
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    class Game extends AsyncTask<Void, Void, Integer> {
        volatile private Point movement = new Point(1, 0);
        private Activity context;
        private Snake snake;

        public Game (TextView view, Activity context, Point dimensions, Point startPosition) {

            super();
            this.context = context;
            snake = new Snake(dimensions, startPosition);

        }

        public Snake getSnake () {
            return snake;
        }

        @Override
        protected Integer doInBackground (Void... points) {
            while (!isCancelled() || !snake.isLost()) {
                snake.step(movement);
                context.runOnUiThread(() -> {
                    drawable.reDraw();
                });

                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return snake.getScore();
        }

        void updateMovement (Point movement) {
            this.movement = movement;
        }
    }
}
