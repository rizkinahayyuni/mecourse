package org.aplas.mecourse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recview;
    courseAdapter adapter;
    EditText txt_src;
    ImageButton btn_src,btn_clr,btn_fav;
    DatabaseReference favoriteref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public CourseFragment() {
        // Required empty public constructor
    }

    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        txt_src = (EditText)view.findViewById(R.id.txt_src_course);
        btn_src = (ImageButton)view.findViewById(R.id.btn_src_course);
        btn_clr = (ImageButton)view.findViewById(R.id.btn_clear_course);

        recview = (RecyclerView)view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseShowCourse();

        btn_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = "";
                search = txt_src.getText().toString();
                if (!search.equals("")) {
                    firebaseSearchCourse(search);
                }
            }
        });

        btn_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_src.setText("");
                firebaseShowCourse();
            }
        });

        return view;
    }

    private void firebaseShowCourse() {
        FirebaseRecyclerOptions<courseModel> options =
                new FirebaseRecyclerOptions.Builder<courseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("course"), courseModel.class)
                        .build();

        adapter = new courseAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    private void firebaseSearchCourse(String s) {
        FirebaseRecyclerOptions<courseModel> options =
                new FirebaseRecyclerOptions.Builder<courseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("course").orderByChild("title").startAt(s).endAt(s+"\uf8ff"), courseModel.class)
                        .build();

        adapter = new courseAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}