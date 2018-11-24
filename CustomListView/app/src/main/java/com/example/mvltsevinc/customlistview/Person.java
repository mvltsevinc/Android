package com.example.mvltsevinc.customlistview;

public class Person {
    private String name;
    private String birthday;
    private String sex;
    private String imgURL;

    public Person(String name, String birthday, String sex, String imgURL) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.imgURL = imgURL;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
