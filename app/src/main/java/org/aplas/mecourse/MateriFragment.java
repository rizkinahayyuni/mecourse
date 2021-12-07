package org.aplas.mecourse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MateriFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MateriFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private FragmentActivity myContext;
    String course,title,imgHeader,content1,img1,content2,img2,content3,img3;

    public MateriFragment() {
        // Required empty public constructor
    }

    public MateriFragment(String course,String title,String imgHeader,String c1,String img1,String c2,String img2,String c3,String img3) {
        this.course = course;
        this.title = title;
        this.imgHeader = imgHeader;
        this.content1 = c1;
        this.content2 = c2;
        this.content3 = c3;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MateriFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MateriFragment newInstance(String param1, String param2) {
        MateriFragment fragment = new MateriFragment();
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
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_materi, container, false);
        FragmentManager fragManager = myContext.getSupportFragmentManager();

        ImageButton btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        TextView txt_title = (TextView) view.findViewById(R.id.txt_titlemateri);
        TextView txt_content1 = (TextView) view.findViewById(R.id.txt_content1);
        TextView txt_content2 = (TextView) view.findViewById(R.id.txt_content2);
        TextView txt_content3 = (TextView) view.findViewById(R.id.txt_content3);
        ImageView img_header = (ImageView) view.findViewById(R.id.img_header);
        ImageView img_1 = (ImageView) view.findViewById(R.id.img1);
        ImageView img_2 = (ImageView) view.findViewById(R.id.img2);
        ImageView img_3 = (ImageView) view.findViewById(R.id.img3);
        CardView cv_img = (CardView) view.findViewById(R.id.cv_img);
        CardView cv_img1 = (CardView) view.findViewById(R.id.cv_img1);
        CardView cv_img2 = (CardView) view.findViewById(R.id.cv_img2);
        CardView cv_img3 = (CardView) view.findViewById(R.id.cv_img3);

        txt_title.setText(title);
        txt_content1.setText(content1);
        txt_content2.setText(content2);
        txt_content3.setText(content3);
        if (!("".equalsIgnoreCase(imgHeader))) {
            cv_img.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,600));
            cv_img.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(imgHeader).into(img_header);
        }
        if (!("".equalsIgnoreCase(img1))) {
            cv_img1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,600));
            cv_img1.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(img1).into(img_1);
        }
        if (!("".equalsIgnoreCase(img2))) {
            cv_img2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,600));
            cv_img2.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(img2).into(img_2);
        }
        if (!("".equalsIgnoreCase(img3))) {
            cv_img3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,600));
            cv_img3.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(img3).into(img_3);
        }

        return view;
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new CourseFragment()).addToBackStack(null).commit();
    }
}