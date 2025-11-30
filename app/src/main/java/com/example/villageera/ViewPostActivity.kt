package com.example.villageera

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.villageera.databinding.ActivityViewPostBinding

class ViewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewPostBinding
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupAnimation()
        setupRecycler()
        loadPosts()
        setupClicks()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            elevation = 0f
            title = getString(R.string.all_posts)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_24)
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        }
    }

    private fun setupAnimation() {
        binding.viewPostAnimationView.playAnimation()

        val translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim)
        binding.recyclerView.animation = translateAnim
    }

    private fun setupRecycler() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ViewPostActivity)
            setHasFixedSize(true)
        }
    }

    private fun setupClicks() {
        binding.addPostButton.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }
    }

    private fun loadPosts() {
        val db = PostDataBaseHelperClass(this)
        val posts = db.getPosts()

        if (posts.isNotEmpty()) {
            val translateAnim = AnimationUtils.loadAnimation(this, R.anim.translate_anim)
            binding.addPostButton.startAnimation(translateAnim)

            binding.viewPostAnimationView.visibility = View.INVISIBLE

            postAdapter = PostAdapter(posts, this)
            binding.recyclerView.adapter = postAdapter
        } else {
            Toast.makeText(this, "પોસ્ટ ઉપલબ્ધ નથી.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.view_post, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                startActivity(Intent(this, DashBoardActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
