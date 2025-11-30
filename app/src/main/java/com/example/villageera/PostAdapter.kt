package com.example.villageera

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.villageera.databinding.ActivityPostDisplayBinding

class PostAdapter(
    private val posts: List<PostModalClass>,
    private val context: Context
) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ActivityPostDisplayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = posts.size

    inner class PostViewHolder(private val binding: ActivityPostDisplayBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostModalClass) {

            binding.nameShow.text = post.name
            binding.phoneShow.text = post.phone
            binding.postTypeShow.text = post.postType
            binding.postDescriptionShow.text = post.postDesc

            binding.postCallShow.setOnClickListener {
                val phone = post.phone
                val callIntent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phone")
                }
                context.startActivity(callIntent)
            }
        }
    }
}
