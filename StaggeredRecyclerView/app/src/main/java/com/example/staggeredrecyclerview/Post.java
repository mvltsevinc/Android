package com.example.staggeredrecyclerview;

public class Post {
    private String image;
    private String name;

    public Post(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Post{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
