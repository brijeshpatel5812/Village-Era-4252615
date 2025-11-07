package com.example.villageera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class PostActivity extends AppCompatActivity {

    String[] post_type_array = {"ફરિયાદ", "સૂચન સલાહ અથવા", "નોટિસ", "અન્ય"};
    EditText name_txt, phone_txt, post_desc_txt;
    TextInputLayout name_label, phone_label, post_type_label, post_desc_label;
    MaterialAutoCompleteTextView materialAutoCompleteTextView;
    ArrayAdapter<String> adapter;
    Button post_btn, cancel_btn;
    String name, phone, post_type, post_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Objects.requireNonNull(getSupportActionBar()).setElevation(0);

        getSupportActionBar().setTitle("ચાલો નવી પોસ્ટ કરીએ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar aBar;
        aBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
        aBar.setBackgroundDrawable(cd);

        //Setting lottie animation
        LottieAnimationView animationView = findViewById(R.id.post_animation_view);
        animationView.playAnimation();

        //hooks
        materialAutoCompleteTextView = findViewById(R.id.post_type);
        cancel_btn = findViewById(R.id.post_cancel_btn);
        post_btn = findViewById(R.id.post_upload_btn);
        name_label = findViewById(R.id.post_name_label);
        name_txt = findViewById(R.id.post_name);
        phone_label = findViewById(R.id.post_phone_label);
        phone_txt = findViewById(R.id.post_phone);
        post_type_label = findViewById(R.id.post_type_label);
        post_desc_label = findViewById(R.id.post_description_label);
        post_desc_txt = findViewById(R.id.post_description);
        PostDataBaseHelperClass postDataBaseHelperClass = new PostDataBaseHelperClass(this);

        adapter = new ArrayAdapter<>(this, R.layout.category_list_view, post_type_array);
        materialAutoCompleteTextView.setAdapter(adapter);

        cancel_btn.setOnClickListener(view -> {
            startActivity(new Intent(PostActivity.this, DashBoardActivity.class));
            finish();
        });

        post_btn.setOnClickListener(view -> {
            String val = materialAutoCompleteTextView.getText().toString();
            if (!validatePostNameData() | !validatePostPhoneData() | !validatePostTypeData(val) | !validatePostDescData()) {
                return;
            }
            name = name_txt.getText().toString();
            phone = phone_txt.getText().toString();
            post_type = materialAutoCompleteTextView.getText().toString();
            post_desc = post_desc_txt.getText().toString();
            PostModalClass postModalClass = new PostModalClass(name, phone, post_type, post_desc);
            postDataBaseHelperClass.addPost(postModalClass);
            Toast.makeText(PostActivity.this, "તમારી પોસ્ટ સફળતાપૂર્વક બની ગયે છે.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(PostActivity.this, DashBoardActivity.class));
            finish();
            //Toast.makeText(this, "we are sorry for our inconvenience.", Toast.LENGTH_SHORT).show();
        });
    }

    //Validating Name
    private boolean validatePostNameData() {
        String val = name_txt.getText().toString().trim();
        if (val.isEmpty()) {
            name_label.setError("નામ ખાલી ન હોઈ શકે\n");
            return false;
        } else {
            name_label.setError(null);
            name_label.setErrorEnabled(false);
            return true;
        }
    }

    //Validating Phone
    private boolean validatePostPhoneData() {
        String val = phone_txt.getText().toString().trim();
        if (val.isEmpty()) {
            phone_label.setError("ફોન ખાલી ન હોઈ શકે\n");
            return false;
        } else if (val.length() < 11 & val.length() > 9) {
            phone_label.setError(null);
            phone_label.setErrorEnabled(false);
            return true;
        } else {
            phone_label.setError("ફોન નંબર 10 અંકનો હોવો જોઈએ\n");
            return false;
        }
    }

    //Validating Name
    private boolean validatePostTypeData(String val) {
        if (val.isEmpty()) {
            post_type_label.setError("પોસ્ટ નો પ્રકાર ખાલી ન હોઈ શકે\n");
            return false;
        } else {
            post_type_label.setError(null);
            post_type_label.setErrorEnabled(false);
            return true;
        }
    }

    //Validating Name
    private boolean validatePostDescData() {
        String val = post_desc_txt.getText().toString().trim();
        if (val.isEmpty()) {
            post_desc_label.setError("પોસ્ટ નુ વર્ણન ખાલી ન હોઈ શકે\n");
            return false;
        } else {
            post_desc_label.setError(null);
            post_desc_label.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_post:
                startActivity(new Intent(this, DashBoardActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}