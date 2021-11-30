package org.aplas.mecourse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        bottomNavigationView = findViewById(R.id.buttom_navigation_menu);
//        bottomNavigationView.inflateMenu(R.menu.bottom_nav_menu);
        fragmentManager = getSupportFragmentManager();
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentManager.beginTransaction().replace(R.id.frame_container, new HomeFragment()).commit();

//        Bundle bundle = getIntent().getExtras();
//        name = bundle.getString("name");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
//            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.home:
//                    Bundle bundle = new Bundle();
//                    bundle.putString("name", name);
                    fragment = new HomeFragment();
//                    fragment.setArguments(bundle);
                    break;
                case R.id.course:
                    fragment = new CourseFragment();
                    break;
                case R.id.account:
                    fragment = new AccountFragment();
                    break;
            }
//            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
            return true;
        }
    };
}