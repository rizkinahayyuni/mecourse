package org.aplas.mecourse;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListMaterialFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String title, iurl;
    RecyclerView recview_listmaterial;
    listMaterialAdapter adapter;

    public ListMaterialFragment() {

    }

    public ListMaterialFragment(String title, String iurl) {
        this.title = title;
        this.iurl = iurl;
    }

    public static ListMaterialFragment newInstance(String param1, String param2) {
        ListMaterialFragment fragment = new ListMaterialFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_material, container, false);

        ImageView imageholder = view.findViewById(R.id.img_banner);
        TextView titleholder = view.findViewById(R.id.txt_title);
        ImageButton btnBack = view.findViewById(R.id.btn_back);

        titleholder.setText(title);
        Glide.with(getContext()).load(iurl).into(imageholder);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        recview_listmaterial = (RecyclerView)view.findViewById(R.id.recview_listmateri);
        recview_listmaterial.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<listMaterialModel> options =
                new FirebaseRecyclerOptions.Builder<listMaterialModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("listmateri").orderByChild("course").startAt(title).endAt(title+"\uf8ff"), listMaterialModel.class)
                        .build();

        adapter = new listMaterialAdapter(options);
        adapter.startListening();
        recview_listmaterial.setAdapter(adapter);

        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new CourseFragment()).addToBackStack(null).commit();
    }
}