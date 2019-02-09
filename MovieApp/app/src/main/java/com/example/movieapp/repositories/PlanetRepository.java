package com.example.movieapp.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.example.movieapp.models.Planet;

import java.util.ArrayList;

public class PlanetRepository {

    private static PlanetRepository instance;
    private ArrayList<Planet> dataSet = new ArrayList<Planet>();

    public static PlanetRepository getInstance(){
        if(instance ==null){
            instance = new PlanetRepository();
        }
        return instance;
    }

    // Pretend to get data from a webservice online source
    public MutableLiveData<ArrayList<Planet>> getPlanets(){
        setPlanets();
        MutableLiveData<ArrayList<Planet>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setPlanets(){
        dataSet.add(new Planet("Mercury","Category1","Desc1","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/1.jpg?alt=media&token=dd51747c-7ed5-4194-9e0c-525f77e92337"));
        dataSet.add(new Planet("Venus","Category1","Desc2","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/2.jpg?alt=media&token=1f27dd26-a873-449c-a06a-dd4bb78d0338"));
        dataSet.add(new Planet("Mars","Category1","Desc3","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/3.png?alt=media&token=d21b3cae-2c57-4ec3-8c2c-3b6e8dd7c1c4"));
        dataSet.add(new Planet("Earth","Category1","Desc4","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/4.jpg?alt=media&token=f324e781-21d2-42a8-8d4b-7427518ac0a0"));
        dataSet.add(new Planet("Jupiter","Category1","Desc5","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/5.jpg?alt=media&token=3ad620c3-45b0-41fb-8018-3dd5602b4b60"));
        dataSet.add(new Planet("Saturn","Category1","Desc6","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/6.png?alt=media&token=2845092c-aa40-4621-9756-79f010af7146"));
        dataSet.add(new Planet("Uranus","Category1","Desc7","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/7.jpg?alt=media&token=344305eb-fb3c-4eef-8021-b2b499f50e07"));
        dataSet.add(new Planet("Neptune","Category1","Desc8","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/8.jpg?alt=media&token=556fdc5d-e737-4630-b345-82fb36dd42fe"));
        dataSet.add(new Planet("Pluton","Category1","Desc9","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/9.jpg?alt=media&token=094545a7-48f4-45ed-9400-aa6311a2bf65"));
    }

    private void getPlanetsFromFirebaseAndSet(){

    }
}
