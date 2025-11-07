package com.example.villageera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    //Declaring variables
    private static int SPLASH_TIMER = 2500;
    Animation bottom_anim, side_anim;
    ImageView Image;
    TextView logoOne, logoTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //hiding the action bar from the activity
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //hooks
        Image = findViewById(R.id.main_img);
        logoOne = findViewById(R.id.logo_name_fir);
        logoTwo = findViewById(R.id.logo_name_sec);

        //Animation hooks
        side_anim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottom_anim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //set animations to elements
        Image.setAnimation(side_anim);
        logoOne.setAnimation(bottom_anim);
        logoTwo.setAnimation(bottom_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, DashBoardActivity.class));
                finish();
            }
        }, SPLASH_TIMER);
    }
}