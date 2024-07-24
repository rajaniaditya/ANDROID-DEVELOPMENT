package com.example.pr_9_2;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.im);
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnimator.setDuration(10000); // 10 seconds
        rotateAnimator.setRepeatCount(ObjectAnimator.INFINITE); // Repeat indefinitely
        rotateAnimator.start();}
}
