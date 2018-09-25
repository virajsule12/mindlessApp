package virajsule.com.mindlessapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private GestureDetector gestureDetector;
    float lineTop;
    float lineBottom;
    private int score = 0;
    private int lives = 10;
    private boolean swipeDown,swipeUp,swipeLeft,swipeRight,lifeLost;
    ImageView line;
    private ArrayList<Float> XVals = new ArrayList<>();
    private ArrayList<Integer> imgArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doStuff();
        line = (ImageView) findViewById(R.id.line);
        XVals.add(20f);
        XVals.add(290f);
        XVals.add(580f);
        XVals.add(840f);

        imgArray.add(R.drawable.left);
        imgArray.add(R.drawable.right);
        imgArray.add(R.drawable.down);
        imgArray.add(R.drawable.up);

        swipeUp=false;
        swipeDown=false;
        swipeLeft=false;
        swipeRight=false;
        lifeLost=false;



        gestureDetector = new GestureDetector(MainActivity.this, MainActivity.this);






    }

    public void doStuff(){
        final Button start = (Button) findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=0;
                lives=10;
                updateText();
                lineTop = line.getTop();
                lineBottom = line.getY()+line.getHeight();
                start.setEnabled(false);
                generate();

            }
        });
    }

    public void generate(){
        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.layout);
        constraintLayout.post(new Runnable() {
            @Override
            public void run() {
                if (lives > 0) {
                    final int randNum = (int) Math.floor(Math.random() * 4);
                    final ImageView imageView = new ImageView(MainActivity.this);
                    imageView.setImageResource(imgArray.get(randNum));
                    constraintLayout.addView(imageView, 130, 130);
                    imageView.setX(XVals.get(randNum));
                    final ArrowAnimator arrowAnimator = new ArrowAnimator(20, imageView, MainActivity.this, 8000);
                    arrowAnimator.start();
                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (!lifeLost) {
                                if (arrowAnimator.getArrowY() >= lineTop) {
                                    if (randNum == 0) {
                                        if (swipeUp || swipeDown || swipeRight) {
                                            lives--;
                                            lifeLost = true;
                                        } else if (swipeLeft) {
                                            score++;
                                        }
                                    }
                                    if (randNum == 1) {
                                        if (swipeUp || swipeDown || swipeLeft) {
                                            lives--;
                                            lifeLost = true;
                                        } else if (swipeRight) {
                                            score++;
                                        }
                                    }
                                    if (randNum == 2) {
                                        if (swipeUp || swipeLeft || swipeRight) {
                                            lives--;
                                            lifeLost = true;
                                        } else if (swipeDown) {
                                            score++;
                                        }
                                    }
                                    if (randNum == 3) {
                                        if (swipeLeft || swipeDown || swipeRight) {
                                            lives--;
                                            lifeLost = true;
                                        } else if (swipeUp) {
                                            score++;
                                        }
                                    }
                                }
                            }
                            swipeUp = false;
                            swipeDown = false;
                            swipeLeft = false;
                            swipeRight = false;

                            updateText();
                            imageView.postDelayed(this, 10);
                        }
                    });
                    lifeLost = false;
                    constraintLayout.postDelayed(this, 1500);
                }
            }

        });


    }


    public void updateText(){
        TextView scoreT = (TextView) findViewById(R.id.scoretxt);
        TextView livesT = (TextView) findViewById(R.id.livestxt);
        Button start = (Button) findViewById(R.id.startBtn);
        scoreT.setText("Score: " + score);
        if (lives>0){
            livesT.setText("Lives: " + lives);
        }
        else {
            start.setEnabled(true);
            livesT.setText("GAME OVER!");
        }
    }


    @Override
    public boolean onFling(MotionEvent motionEvent1, MotionEvent motionEvent2, float X, float Y) {

        if (motionEvent1.getX() - motionEvent2.getX() > 50) {
            //Toast.makeText(MainActivity.this, "You Swiped Left!", Toast.LENGTH_LONG).show();
            swipeLeft=true;
            return true;
        }

        if (motionEvent2.getX() - motionEvent1.getX() > 50) {
            //Toast.makeText(MainActivity.this, "You Swiped Right!", Toast.LENGTH_LONG).show();
            swipeRight=true;
            return true;
        }
        if (motionEvent2.getY() - motionEvent1.getY() > 50) {
            //Toast.makeText(MainActivity.this, "You Swiped Down!", Toast.LENGTH_LONG).show();
            swipeDown=true;
            return true;
        }
        if (motionEvent2.getY() - motionEvent1.getY() < 50) {
            //Toast.makeText(MainActivity.this, "You Swiped Up!", Toast.LENGTH_LONG).show();
            swipeUp=true;
            return true;
        }

        return true;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2, float arg3) {

        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        return false;
    }



}
