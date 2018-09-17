package virajsule.com.mindlessapp;

import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doStuff();
//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.leftImages);
//        ImageView imageView = new ImageView(MainActivity.this);
//        imageView.setImageResource(R.drawable.left);
//        linearLayout.addView(imageView);
//        ObjectAnimator animation = ObjectAnimator.ofFloat(imageView, "translationY", 400f);
//        animation.setDuration(1000);
//        animation.start();
    }

    public void doStuff(){
        Button start = (Button) findViewById(R.id.startBtn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();


            }
        });
    }

    public void generate(){
        int[] coords = new int[2];
        coords[0] = 0;
        coords[1] = 0;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.leftImages);
        ImageView imageView = new ImageView(MainActivity.this);
        imageView.setImageResource(R.drawable.left);
        linearLayout.addView(imageView,130,130);

        imageView.setLeft(11);
        imageView.getLocationOnScreen(coords);
        System.out.println("Beginning: " + coords[1]);

        animate(imageView);

    }

    public void animate(final ImageView image){

        TranslateAnimation anim = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 5f); // this is distance of top and bottom form current positiong

        anim.setDuration(1000);
        image.startAnimation(anim);

//        int[] coords = new int[2];
//        coords[0] = 0;
//        coords[1] = 0;
//        ObjectAnimator animation = ObjectAnimator.ofFloat(image, "translationY", 500f);
//        animation.setDuration(1000);
//        animation.start();
//        image.getLocationOnScreen(coords);
//        System.out.println("End: " + coords[1]);
//        new CountDownTimer(500, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//
//            }
//
//            public void onFinish() {
//                image.setVisibility(View.INVISIBLE);
//                generate();
//            }
//        }.start();

    }
}
