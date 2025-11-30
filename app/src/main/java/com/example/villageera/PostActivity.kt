package com.example.villageera

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.villageera.databinding.ActivityPostBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var postDb: PostDataBaseHelperClass

    private val postTypes = arrayOf("ફરિયાદ", "સૂચન સલાહ અથવા", "નોટિસ", "અન્ય")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        setupAnimation()
        setupDatabase()
        setupDropdown()
        setupClicks()
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            elevation = 0f
            title = "ચાલો નવી પોસ્ટ કરીએ"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_24)
            setBackgroundDrawable(ColorDrawable(Color.parseColor("#FFFFFF")))
        }
    }

    private fun setupAnimation() {
        binding.postAnimationView.playAnimation()
    }

    private fun setupDatabase() {
        postDb = PostDataBaseHelperClass(this)
    }

    private fun setupDropdown() {
        val adapter = ArrayAdapter(this, R.layout.category_list_view, postTypes)
        (binding.postType as MaterialAutoCompleteTextView).setAdapter(adapter)
    }

    private fun setupClicks() {

        binding.postCancelBtn.setOnClickListener {
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        }

        binding.postUploadBtn.setOnClickListener {

            val postTypeVal = binding.postType.text.toString()

            if (!validateName() || !validatePhone() || !validateType(postTypeVal) || !validateDesc()) {
                return@setOnClickListener
            }

            val name = binding.postName.text.toString()
            val phone = binding.postPhone.text.toString()
            val postType = binding.postType.text.toString()
            val postDesc = binding.postDescription.text.toString()

            val post = PostModalClass(
                name = name,
                phone = phone,
                postType = postType,
                postDesc = postDesc
            )

            postDb.addPost(post)

            Toast.makeText(this, "તમારી પોસ્ટ સફળતાપૂર્વક બની ગયે છે.", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        }
    }

    // ----------------------
    // VALIDATION FUNCTIONS
    // ----------------------

    private fun validateName(): Boolean {
        val value = binding.postName.text.toString().trim()

        return if (value.isEmpty()) {
            binding.postNameLabel.error = "નામ ખાલી ન હોઈ શકે\n"
            false
        } else {
            binding.postNameLabel.error = null
            true
        }
    }

    private fun validatePhone(): Boolean {
        val phone = binding.postPhone.text.toString().trim()

        return when {
            phone.isEmpty() -> {
                binding.postPhoneLabel.error = "ફોન ખાલી ન હોઈ શકે\n"
                false
            }
            phone.length == 10 -> {
                binding.postPhoneLabel.error = null
                true
            }
            else -> {
                binding.postPhoneLabel.error = "ફોન નંબર 10 અંકનો હોવો જોઈએ\n"
                false
            }
        }
    }

    private fun validateType(type: String): Boolean {
        return if (type.isEmpty()) {
            binding.postTypeLabel.error = "પોસ્ટ નો પ્રકાર ખાલી ન હોઈ શકે\n"
            false
        } else {
            binding.postTypeLabel.error = null
            true
        }
    }

    private fun validateDesc(): Boolean {
        val value = binding.postDescription.text.toString().trim()

        return if (value.isEmpty()) {
            binding.postDescriptionLabel.error = "પોસ્ટ નુ વર્ણન ખાલી ન હોઈ શકે\n"
            false
        } else {
            binding.postDescriptionLabel.error = null
            true
        }
    }

    // ----------------------

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_post -> {
                startActivity(Intent(this, DashBoardActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
