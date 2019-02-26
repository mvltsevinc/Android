package com.example.samplenoteapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.samplenoteapp.repo.WordRepository;
import com.example.samplenoteapp.model.Word;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private final WordRepository mRepository;
    private final LiveData<List<Word>> mAllWords;

    public WordViewModel (Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //Get All Words
    public LiveData<List<Word>> getAllWords() { return mAllWords; }
    //Insert A Word
    public void insert(Word word) { mRepository.insert(word); }
    //Delete All Words
    public void deleteAll(){
        mRepository.deleteAll();
    }
}
