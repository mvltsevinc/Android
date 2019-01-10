package com.example.mvltsevinc.twitchedits.models;

public class User {
    private String user_id;
    private String profile_photo;
    private String email;
    private String username;

    public User() {

    }

    public User(String user_id, String email, String username,String profile_photo) {
        this.user_id = user_id;
        this.email = email;
        this.username = username;
        this.profile_photo = profile_photo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", profile_photo='" + profile_photo + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
