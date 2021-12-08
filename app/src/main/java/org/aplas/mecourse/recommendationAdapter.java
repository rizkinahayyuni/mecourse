package org.aplas.mecourse;

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

import org.w3c.dom.Text;

public class recommendationAdapter extends FirebaseRecyclerAdapter<courseModel, recommendationAdapter.myviewholder> {

    public recommendationAdapter(@NonNull FirebaseRecyclerOptions<courseModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull courseModel model) {
        holder.title.setText(model.getTitle());
        Glide.with(holder.imgCourse.getContext()).load(model.getIurl()).into(holder.imgCourse);

        holder.imgCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =  (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new ListMaterialFragment(model.getTitle(),model.getIurl())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommendation_row,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView imgCourse;
        TextView title;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imgCourse = (ImageView) itemView.findViewById(R.id.img_rec_course);
            title = (TextView) itemView.findViewById(R.id.txt_rec_course);

        }
    }
}
