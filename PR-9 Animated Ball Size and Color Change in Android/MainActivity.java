package com.example.pr_9;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private ImageView ballImageView;
    private int currentBallIndex = 0;
    private int[] ballDrawables = {R.drawable.red_ball, R.drawable.blue_ball, R.drawable.green_ball};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballImageView = findViewById(R.id.ballImageView);
        rotateBall();
    }
    private void rotateBall() {
        ObjectAnimator rotation = ObjectAnimator.ofFloat(ballImageView, "rotation", 0f, 360f);
        rotation.setDuration(6000); 
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentBallIndex = (currentBallIndex + 1) % ballDrawables.length;
                ballImageView.setImageResource(ballDrawables[currentBallIndex]);
                rotateBall(); // Restart rotation with the new ball
            }
        }, 6000);
    }
}
