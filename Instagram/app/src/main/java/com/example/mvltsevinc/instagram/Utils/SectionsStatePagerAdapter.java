package com.example.mvltsevinc.instagram.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SectionsStatePagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private final HashMap<Fragment, Integer> fragments = new HashMap<>();
    private final HashMap<String, Integer> fragmentNumbers = new HashMap<>();
    private final HashMap<Integer, String> fragmentNames = new HashMap<>();

    public SectionsStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String fragmentName){
        fragmentList.add(fragment);
        fragments.put(fragment,fragmentList.size() -1);
        fragmentNumbers.put(fragmentName,fragmentList.size() -1);
        fragmentNames.put(fragmentList.size() -1, fragmentName);
    }

    /**
     *  Return the fragment number for fragment name parameter
     */
    public Integer getFragmentNumber(String fragmentName){
        if(fragmentNumbers.containsKey(fragmentName)){
            return fragmentNumbers.get(fragmentName);
        }else{
            return null;
        }
    }

    /**
     *  Return the fragment number for fragment object parameter
     */
    public Integer getFragmentNumber(Fragment fragment){
        if(fragments.containsKey(fragment)){
            return fragments.get(fragment);
        }else{
            return null;
        }
    }

    /**
     *  Return the fragment name for fragment number parameter
     */
    public String getFragmentName(Integer fragmentNumber){
        if(fragmentNames.containsKey(fragmentNumber)){
            return fragmentNames.get(fragmentNumber);
        }else{
            return null;
        }
    }


}
