package com.example.samplenoteapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "word")
    private String word;

    @NonNull
    @ColumnInfo(name = "date")
    private String datetime;

    public Word(@NonNull String word, @NonNull String datetime) {
        this.word = word;
        this.datetime = datetime;
    }

    @Ignore
    public Word(@NonNull int id,@NonNull String word, @NonNull String datetime) {
        this.id = id;
        this.word = word;
        this.datetime = datetime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    @NonNull
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(@NonNull String datetime) {
        this.datetime = datetime;
    }
}
