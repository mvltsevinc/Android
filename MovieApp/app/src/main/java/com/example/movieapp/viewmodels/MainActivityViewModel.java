package com.example.movieapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.movieapp.models.Planet;
import com.example.movieapp.repositories.PlanetRepository;

import java.util.ArrayList;

public class MainActivityViewModel  extends ViewModel {

    private MutableLiveData<ArrayList<Planet>> mPlanets;
    private PlanetRepository mRepo;

    // Repository den verileri View Model e cek
    public void init(){
            if(mPlanets != null){
                return;
            }
            mRepo = PlanetRepository.getInstance();
        mPlanets = mRepo.getPlanets();
    }

    // ViewModel den View a verileri vermek icin
    public LiveData<ArrayList<Planet>> getPlanets(){
        return mPlanets;
    }
}
