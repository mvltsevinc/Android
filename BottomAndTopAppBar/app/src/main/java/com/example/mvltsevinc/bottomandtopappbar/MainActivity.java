package com.example.mvltsevinc.bottomandtopappbar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_assignment);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_autorenew);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_attach_file);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavViewBar);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.ic_arrow:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                    case  R.id.ic_android:
                        Intent intent1 = new Intent(getApplicationContext(),ActivityOne.class);
                        startActivity(intent1);
                        break;
                    case    R.id.ic_books:
                        Intent intent2 = new Intent(getApplicationContext(),ActivityTwo.class);
                        startActivity(intent2);
                        break;
                    case    R.id.ic_center_focus:
                        Intent intent3 = new Intent(getApplicationContext(),ActivityThree.class);
                        startActivity(intent3);
                        break;
                    case    R.id.ic_backup:
                        Intent intent4 = new Intent(getApplicationContext(),ActivityFour.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
         SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
         adapter.addFragment(new Fragment1());
         adapter.addFragment(new Fragment2());
         adapter.addFragment(new Fragment3());
         viewPager.setAdapter(adapter);
    }
}
