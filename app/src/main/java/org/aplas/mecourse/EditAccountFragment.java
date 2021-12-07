package org.aplas.mecourse;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditAccountFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String userID, username, email, phone, location;
    EditText edit_name, edit_email, edit_phone, edit_location;
    Button btn_update;
    DatabaseReference reference;

    public EditAccountFragment() {
        // Required empty public constructor
    }

    public EditAccountFragment(String ui,String u, String e, String p, String l) {
        this.userID = ui;
        this.username = u;
        this.email = e;
        this.phone = p;
        this.location = l;
    }

    public static EditAccountFragment newInstance(String param1, String param2) {
        EditAccountFragment fragment = new EditAccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit_account, container, false);

        reference = FirebaseDatabase.getInstance().getReference("Users");

        btn_update = (Button) view.findViewById(R.id.btn_update);

        edit_name = (EditText)view.findViewById(R.id.edit_name);
        edit_email = (EditText)view.findViewById(R.id.edit_email);
        edit_phone = (EditText)view.findViewById(R.id.edit_phone);
        edit_location = (EditText)view.findViewById(R.id.edit_location);

        edit_name.setText(username);
        edit_email.setText(email);
        edit_phone.setText(phone);
        edit_location.setText(location);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNameChanged() || isEmailChanged() || isPhoneChanged() || isLocationChanged()) {
                    Toast.makeText(getActivity(),"Data has been updated.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(),"Data is same and can not be updated.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private boolean isEmailChanged(){
        if(!email.equals(edit_email.getText().toString()))
        {
            reference.child(userID).child("email").setValue(edit_email.getText().toString());
            email=edit_email.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isNameChanged(){
        if(!username.equals(edit_name.getText().toString())){
            reference.child(userID).child("name").setValue(edit_name.getText().toString());
            username=edit_name.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isPhoneChanged(){
        if(!phone.equals(edit_phone.getText().toString())){
            reference.child(userID).child("phone").setValue(edit_phone.getText().toString());
            phone=edit_phone.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    private boolean isLocationChanged(){
        if(!location.equals(edit_location.getText().toString())){
            reference.child(userID).child("location").setValue(edit_location.getText().toString());
            location=edit_location.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public void onBackPressed() {
        AppCompatActivity activity = (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new AccountFragment()).addToBackStack(null).commit();
    }
}