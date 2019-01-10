package com.example.mvltsevinc.twitchedits.Login;


import android.support.design.widget.TabLayout;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.mvltsevinc.twitchedits.R;
import com.example.mvltsevinc.twitchedits.Utils.SectionsPagerAdapter;

public class LoginActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        setupPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LoginFragment(),"LOGIN");
        adapter.addFragment(new RegisterFragment(),"REGISTER");
        viewPager.setAdapter(adapter);
    }
}
