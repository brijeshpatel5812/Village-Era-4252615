package com.example.villageera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

public class DashBoardActivity extends AppCompatActivity {

    long backPressedTime;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button post_btn, post_view_btn;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle(R.string.app_name);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //Setting lottie animation
        animationView = findViewById(R.id.dashboard_animation_view);
        animationView.playAnimation();

        //hooks
        sharedPreferences = getSharedPreferences("user_log", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        post_view_btn = findViewById(R.id.post_view_button);
        post_btn = findViewById(R.id.post_button);

        post_view_btn.setOnClickListener(view -> startActivity(new Intent(DashBoardActivity.this, ViewPostActivity.class)));

        post_btn.setOnClickListener(view -> startActivity(new Intent(DashBoardActivity.this, PostActivity.class)));
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(DashBoardActivity.this);
            materialAlertDialogBuilder.setBackground(getResources().getDrawable(R.drawable.dialog_background));
            materialAlertDialogBuilder.setTitle(R.string.app_name);
            materialAlertDialogBuilder.setMessage("શું તમે એપ્લિકેશનમાંથી બહાર નીકળવા માંગો છો?");
            materialAlertDialogBuilder.setPositiveButton("હા\n", (dialogInterface, i) -> {
                Toast.makeText(DashBoardActivity.this, "સફળતાપૂર્વક બહાર.", Toast.LENGTH_SHORT).show();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            });
            materialAlertDialogBuilder.setNegativeButton("ના\n", (dialogInterface, i) -> {
                Toast.makeText(DashBoardActivity.this, "તમે એપ્લિકેશનમાં છો.", Toast.LENGTH_SHORT).show();
                backPressedTime = 0;
            });
            materialAlertDialogBuilder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.owner_detail:
                startActivity(new Intent(this, OwnerActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}