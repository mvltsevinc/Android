package com.example.mvltsevinc.retrofitjson.model;

import com.example.mvltsevinc.retrofitjson.model.children.Children;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @SerializedName("modhash")
    @Expose
    private String modhas;

    @SerializedName("children")
    @Expose
    private ArrayList<Children> children;

    public String getModhas() {
        return modhas;
    }

    public void setModhas(String modhas) {
        this.modhas = modhas;
    }

    public ArrayList<Children> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Data{" +
                "modhas='" + modhas + '\'' +
                ", children=" + children +
                '}';
    }

}
