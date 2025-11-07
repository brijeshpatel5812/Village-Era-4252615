package com.example.villageera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;
import java.util.Objects;

public class ViewPostActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    Button add_post_btn;
    LottieAnimationView animationView;
    Animation translate_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle(R.string.all_posts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //Setting lottie animation
        animationView = findViewById(R.id.view_post_animation_view);
        animationView.playAnimation();

        //hooks
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        add_post_btn = findViewById(R.id.add_post_button);
        translate_animation = AnimationUtils.loadAnimation(this, R.anim.translate_anim);

        recyclerView.setAnimation(translate_animation);

        add_post_btn.setOnClickListener(view -> startActivity(new Intent(ViewPostActivity.this, PostActivity.class)));

        PostDataBaseHelperClass postDataBaseHelperClass = new PostDataBaseHelperClass(this);
        List<PostModalClass> postModalClasses = postDataBaseHelperClass.getPosts();

        if (postModalClasses.size() > 0) {
            add_post_btn.setAnimation(translate_animation);
            animationView.setVisibility(View.INVISIBLE);
            postAdapter = new PostAdapter(postModalClasses, ViewPostActivity.this);
            recyclerView.setAdapter(postAdapter);
        } else {
            Toast.makeText(this, "પોસ્ટ ઉપલબ્ધ નથી.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                startActivity(new Intent(this, DashBoardActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}