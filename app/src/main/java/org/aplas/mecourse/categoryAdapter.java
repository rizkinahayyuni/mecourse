package org.aplas.mecourse;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class categoryAdapter extends FirebaseRecyclerAdapter<categoryModel, categoryAdapter.myviewholder> {

    public categoryAdapter(@NonNull FirebaseRecyclerOptions<categoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull categoryModel model) {
        holder.catTitle.setText(model.getName());
        Glide.with(holder.imgCategory.getContext()).load(model.getIurl()).into(holder.imgCategory);

        holder.imgCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =  (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ListCategoryFragment(model.getName(),model.getIurl())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_row,parent,false);
        return new categoryAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView catTitle;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgCategory = (ImageView)itemView.findViewById(R.id.imgCategory);
            catTitle = (TextView)itemView.findViewById(R.id.catTitle);
        }
    }
}
