package com.example.mvltsevinc.logintabsuidesign;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment tabLogin = new LoginFragment();
                return tabLogin;
            case 1:
                RegisterFragment tabRegister = new RegisterFragment();
                return tabRegister;
             default:
                 return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "LOGIN";
            case 1:
                return "REGISTER";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
