package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {
    private Integer user_Id;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User(Integer user_Id, String username, String salt, String password, String firstName, String lastName) {
        this.user_Id = user_Id;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}