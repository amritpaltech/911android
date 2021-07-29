package com.soft.credit911.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.soft.credit911.ui.casemanagement.Fragement.CaseManagementFragment;
import com.soft.credit911.ui.Chat.Fragment.ChatFragment;
import com.soft.credit911.ui.dashboard.Dashboard.fragment.DashboardFragment;
import com.soft.credit911.R;
import com.soft.credit911.ui.dashboard.UserProfile.Fragment.UserProfileFragment;
import com.soft.credit911.databinding.ActivityLandingBinding;

public class LandingActivity extends AppCompatActivity {
    private ActivityLandingBinding layoutLanding;


    int isError=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layoutLanding = ActivityLandingBinding.inflate(getLayoutInflater());
        View view = layoutLanding.getRoot();
        setContentView(view);

        if(getIntent().hasExtra("isError")){
            isError=getIntent().getIntExtra("isError",0);
        }

        layoutLanding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {

                    case R.id.Dashboard:
                        fragment = new DashboardFragment();
                        Bundle bundle=new Bundle();
                        bundle.putInt("isError",isError);
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        break;
                    case R.id.Contact_message:
                        fragment = new ChatFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.Case_management:
                        fragment = new CaseManagementFragment();
                        loadFragment(fragment);
                        break;
                    case R.id.More:
                        fragment = new UserProfileFragment();
                        loadFragment(fragment);
                        break;
                }
                return true;
            }
        });
        layoutLanding.bottomNavigationView.setSelectedItemId(R.id.Dashboard);
    }

    public void loadFragment(Fragment fragment){
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flFragment, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}