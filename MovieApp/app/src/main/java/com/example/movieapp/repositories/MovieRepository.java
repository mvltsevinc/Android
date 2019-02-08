package com.example.movieapp.repositories;

import android.arch.lifecycle.MutableLiveData;

import com.example.movieapp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private ArrayList<Movie> dataSet = new ArrayList<Movie>();

    public static MovieRepository getInstance(){
        if(instance ==null){
            instance = new MovieRepository();
        }
        return instance;
    }

    // Pretend to get data from a webservice online source
    public MutableLiveData<ArrayList<Movie>> getMovies(){
        setMovies();
        MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setMovies(){
        dataSet.add(new Movie("Mercury","Category1","Desc1","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/1.jpg?alt=media&token=dd51747c-7ed5-4194-9e0c-525f77e92337"));
        dataSet.add(new Movie("Venus","Category1","Desc2","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/2.jpg?alt=media&token=1f27dd26-a873-449c-a06a-dd4bb78d0338"));
        dataSet.add(new Movie("Mars","Category1","Desc3","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/3.png?alt=media&token=d21b3cae-2c57-4ec3-8c2c-3b6e8dd7c1c4"));
        dataSet.add(new Movie("Earth","Category1","Desc4","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/4.jpg?alt=media&token=f324e781-21d2-42a8-8d4b-7427518ac0a0"));
        dataSet.add(new Movie("Jupiter","Category1","Desc5","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/5.jpg?alt=media&token=3ad620c3-45b0-41fb-8018-3dd5602b4b60"));
        dataSet.add(new Movie("Saturn","Category1","Desc6","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/6.png?alt=media&token=2845092c-aa40-4621-9756-79f010af7146"));
        dataSet.add(new Movie("Uranus","Category1","Desc7","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/7.jpg?alt=media&token=344305eb-fb3c-4eef-8021-b2b499f50e07"));
        dataSet.add(new Movie("Neptune","Category1","Desc8","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/8.jpg?alt=media&token=556fdc5d-e737-4630-b345-82fb36dd42fe"));
        dataSet.add(new Movie("Pluton","Category1","Desc9","https://firebasestorage.googleapis.com/v0/b/planets-4e8d4.appspot.com/o/9.jpg?alt=media&token=094545a7-48f4-45ed-9400-aa6311a2bf65"));
    }
}
