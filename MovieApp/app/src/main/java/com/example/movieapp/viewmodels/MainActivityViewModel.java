package com.example.movieapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.movieapp.models.Movie;
import com.example.movieapp.repositories.MovieRepository;

import java.util.ArrayList;

public class MainActivityViewModel  extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> mMovies;
    private MovieRepository mRepo;

    public void init(){
            if(mMovies != null){
                return;
            }
            mRepo = MovieRepository.getInstance();
            mMovies = mRepo.getMovies();
    }

    public LiveData<ArrayList<Movie>> getMovies(){
        return mMovies;
    }
}
