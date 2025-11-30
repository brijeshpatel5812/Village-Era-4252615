package com.example.villageera

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils

class OwnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setupActionBar()
        setupAnimations()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            elevation = 0f
            title = "ડેવલપર ની માહિતી"
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        }
    }

    private fun setupAnimations() {

    }
}


