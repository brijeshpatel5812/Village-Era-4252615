package com.example.villageera;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    List<PostModalClass> post;
    Context context;
    com.example.villageera.PostDataBaseHelperClass postDataBaseHelperClass;

    public PostAdapter(List<PostModalClass> post, Context context) {
        this.post = post;
        this.context = context;
        postDataBaseHelperClass = new com.example.villageera.PostDataBaseHelperClass(context);
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_post_display, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        final PostModalClass postModalClass = post.get(position);

        holder.name_show.setText(postModalClass.getName());
        holder.phone_show.setText(postModalClass.getPhone());
        holder.post_type_show.setText(postModalClass.getPost_type());
        holder.post_desc_show.setText(postModalClass.getPost_desc());

        holder.call_btn.setOnClickListener(v -> {
            String phone = postModalClass.getPhone();
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phone));
            context.startActivity(callIntent);
        });
    }

    @Override
    public int getItemCount() {
        return post.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name_show, phone_show, post_type_show, post_desc_show;
        Button call_btn;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_show = itemView.findViewById(R.id.name_show);
            phone_show = itemView.findViewById(R.id.phone_show);
            post_type_show = itemView.findViewById(R.id.post_type_show);
            post_desc_show = itemView.findViewById(R.id.post_description_show);
            call_btn = itemView.findViewById(R.id.post_call_show);

        }
    }
}