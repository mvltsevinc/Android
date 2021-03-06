package com.example.mvltsevinc.instagram.Profile;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.mvltsevinc.instagram.R;
import com.example.mvltsevinc.instagram.Utils.BottomNavigationViewHelper;
import com.example.mvltsevinc.instagram.Utils.SectionsStatePagerAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;


public class AccountSettingsActivity extends AppCompatActivity {
    private static final String TAG = "AccountSettingsActivity";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext = AccountSettingsActivity.this;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        viewPager = findViewById(R.id.container);
        relativeLayout = findViewById(R.id.relLayout1);

        setupSettingsList();
        setupFragments();
        setupBottomNavigationView();

        // setup backarrow for back to ProfileActivity
        ImageView backArrow = findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: back to ProfileActivity");
                finish();
            }
        });
    }

    private void setupFragments(){
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(),getString(R.string.edit_profile_fragment));
        pagerAdapter.addFragment(new SignOutFragment(),getString(R.string.sign_out_fragment));
    }

    // Responsible for navigating fragments
    private void setupViewPager(int fragmentNumber){
        relativeLayout.setVisibility(View.GONE);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList(){
        Log.d(TAG, "setupSettingsList: initializing settings list");
        ListView listView = findViewById(R.id.lvAccountSettings);

        ArrayList<String> options = new ArrayList<String>();
        options.add(getString(R.string.edit_profile_fragment));
        options.add(getString(R.string.sign_out_fragment));

        ArrayAdapter adapter = new ArrayAdapter(mContext,android.R.layout.simple_list_item_1,options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setupViewPager(position);
            }
        });
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        // For set color
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
}
