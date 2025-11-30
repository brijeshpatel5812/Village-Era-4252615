package com.example.villageera

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.villageera.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val splashTime = 2500L
    private lateinit var bottomAnim: Animation
    private lateinit var sideAnim: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        // Load animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        // Apply animations
        binding.mainImg.startAnimation(sideAnim)
        binding.logoNameFir.startAnimation(bottomAnim)
        binding.logoNameSec.startAnimation(bottomAnim)

        // Delay using coroutines (modern replacement for Handler)
        lifecycleScope.launch {
            delay(splashTime)
            startActivity(Intent(this@SplashScreenActivity, DashBoardActivity::class.java))
            finish()
        }
    }
}
