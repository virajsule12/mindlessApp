package virajsule.com.mindlessapp;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    float lineTop;
    float lineBottom;
    int score=0;
    ImageView line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doStuff();
        line = (ImageView) findViewById(R.id.line);




    }

    public void doStuff(){
        Button start = (Button) findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineTop = line.getTop();
                System.out.println(lineTop);
                lineBottom = line.getY()+line.getHeight();
                generate();

            }
        });
    }

    public void generate(){
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.layout);
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.leftImages);
//        linearLayout.addView(imageView,130,130);
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.left);
        constraintLayout.addView(imageView,130,130);
        imageView.setX(42f);
//        System.out.println(imageView.getY());

        animate(imageView);

    }

    public void animate(final ImageView image){
//
//        TranslateAnimation anim = new TranslateAnimation(
//                TranslateAnimation.RELATIVE_TO_SELF, 0f,
//                TranslateAnimation.RELATIVE_TO_SELF, 0f,
//                TranslateAnimation.RELATIVE_TO_SELF, 0f,
//                TranslateAnimation.RELATIVE_TO_SELF, 7.5f); // this is distance of top and bottom form current positiong
//
//        anim.setDuration(1000);
//        image.startAnimation(anim);
//        System.out.println();
//
//        if (image.getY()+image.getHeight()>= lineTop && image.getY()<=lineBottom){
//            score++;
//            System.out.println(image.getY());
//
////            System.out.println(score);
////            System.out.println(image.getBottom());
////            System.out.println(lineBottom);
//
//        }
//        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "translationY", 500f);
//        animation.setDuration(1000);
//        animation.start();
        ArrowAnimator arrowAnimator = new ArrowAnimator(10,image,this, 1700);
        arrowAnimator.start();
//        System.out.println("line: " + lineTop);

        if (image.getY()+image.getHeight()>= lineTop && image.getY()<=lineBottom){
            score++;
//            System.out.println(image.getY());

//            System.out.println(score);
//            System.out.println(image.getBottom());
//            System.out.println(lineBottom);

        }


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                generate();
            }
        }.start();

    }
}
