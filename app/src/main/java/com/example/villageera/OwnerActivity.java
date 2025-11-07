package com.example.villageera;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.Objects;

public class OwnerActivity extends AppCompatActivity {
    Animation translate_animation;
    Button contact_btn;
    ConstraintLayout main_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("ડેવલપર ની માહિતી");

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //hooks
        contact_btn = findViewById(R.id.contact_button);
        main_frame = findViewById(R.id.owner_main);
        translate_animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);

        main_frame.setAnimation(translate_animation);

        contact_btn.setOnClickListener(view -> {
            String phone = "9016694506";
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            startActivity(callIntent);
        });
    }
}