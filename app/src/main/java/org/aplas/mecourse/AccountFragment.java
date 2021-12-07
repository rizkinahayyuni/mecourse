package org.aplas.mecourse;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.awt.font.NumericShaper;

public class AccountFragment extends Fragment {


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    Button btn_logout,btn_edit;
    TextView txt_username, txt_email, txt_phone, txt_location;
    String username,email, phone, location;

    public AccountFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btn_logout = (Button)view.findViewById(R.id.btn_logout);
        btn_edit = (Button)view.findViewById(R.id.btn_edit);
        txt_username = (TextView)view.findViewById(R.id.txt_username);
        txt_email = (TextView)view.findViewById(R.id.txt_email);
        txt_phone = (TextView)view.findViewById(R.id.txt_phone);
        txt_location = (TextView)view.findViewById(R.id.txt_loc);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(),LoginActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    username = userProfile.name;
                    email = userProfile.email;
                    phone = userProfile.phone;
                    location = userProfile.location;

                    txt_username.setText(username);
                    txt_email.setText(email);
                    txt_phone.setText(phone);
                    txt_location.setText(location);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something wrong happend!!",Toast.LENGTH_SHORT).show();
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =  (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new EditAccountFragment(userID,username,email,phone,location)).addToBackStack(null).commit();
            }
        });

        return view;
    }
}