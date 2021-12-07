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

public class listMaterialAdapter extends FirebaseRecyclerAdapter<listMaterialModel, listMaterialAdapter.myviewholder> {

    public listMaterialAdapter(@NonNull FirebaseRecyclerOptions<listMaterialModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull listMaterialModel model) {
        holder.titleListMaterial.setText(model.getTitle());

        holder.titleListMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =  (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new MateriFragment(model.getCourse(),model.getTitle(),model.getUimg(),model.getContent1(),model.getUimg1(),model.getContent2(),model.getUimg2(),model.getContent3(),model.getUimg3())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_material_row,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {

        TextView titleListMaterial;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            titleListMaterial = (TextView)itemView.findViewById(R.id.lm_title);
        }
    }
}
