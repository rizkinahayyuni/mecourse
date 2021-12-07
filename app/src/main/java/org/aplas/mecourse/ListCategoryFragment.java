package org.aplas.mecourse;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ListCategoryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    String name, iurl;
    RecyclerView recview_listcategory;
    courseAdapter adapter;

    public ListCategoryFragment() {
        // Required empty public constructor
    }

    public ListCategoryFragment(String name, String iurl) {
        this.name = name;
        this.iurl = iurl;
    }

    public static ListCategoryFragment newInstance(String param1, String param2) {
        ListCategoryFragment fragment = new ListCategoryFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_category, container, false);

        TextView text = (TextView) view.findViewById(R.id.txt_titlecategory);
        text.setText("Category " + name);

        recview_listcategory = (RecyclerView)view.findViewById(R.id.recview_listcategory);
        recview_listcategory.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<courseModel> options =
                new FirebaseRecyclerOptions.Builder<courseModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("course").orderByChild("category").startAt(name).endAt(name+"\uf8ff"), courseModel.class)
                        .build();

        adapter = new courseAdapter(options);
        adapter.startListening();
        recview_listcategory.setAdapter(adapter);

        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).addToBackStack(null).commit();
    }

}