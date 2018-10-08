package com.mvltsevinc.landmarkbook;

import android.graphics.Bitmap;

public class Globals {
    private Bitmap chosenImage;
    private static Globals instance;

    public Globals(){

    }

    public void setData(Bitmap chosenImage){
        this.chosenImage = chosenImage;
    }

    public Bitmap getData(){
        return this.chosenImage;
    }

    public static Globals getInstance() {
        if(instance == null) {
            instance = new Globals();
        }
        return instance;
    }
}
