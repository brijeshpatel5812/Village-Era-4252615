package com.example.villageera

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.villageera.databinding.ActivityDashBoardBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupSharedPrefs()
        setupAnimations()
        setupButtons()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            elevation = 0f
            title = getString(R.string.app_name)
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        }
    }

    private fun setupSharedPrefs() {
        sharedPreferences = getSharedPreferences("user_log", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    private fun setupAnimations() {
        binding.dashboardAnimationView.playAnimation()
    }

    private fun setupButtons() {
        binding.postViewButton.setOnClickListener {
            startActivity(Intent(this, ViewPostActivity::class.java))
        }

        binding.postButton.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
        } else {
            MaterialAlertDialogBuilder(this)
                .setBackground(getDrawable(R.drawable.dialog_background))
                .setTitle(R.string.app_name)
                .setMessage("શું તમે એપ્લિકેશનમાંથી બહાર નીકળવા માંગો છો?")
                .setPositiveButton("હા") { _, _ ->
                    Toast.makeText(this, "સફળતાપૂર્વક બહાર.", Toast.LENGTH_SHORT).show()
                    moveTaskToBack(true)
                    android.os.Process.killProcess(android.os.Process.myPid())
                    System.exit(1)
                }
                .setNegativeButton("ના") { _, _ ->
                    Toast.makeText(this, "તમે એપ્લિકેશનમાં છો.", Toast.LENGTH_SHORT).show()
                    backPressedTime = 0
                }
                .show()

            backPressedTime = System.currentTimeMillis()
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.owner_detail -> {
                startActivity(Intent(this, OwnerActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
