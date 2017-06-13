package com.stevie.clarity;

/**
 * Created by csimon on 5/03/14.
 * Thanks csimon!
 */
public class User {
    public long userId;
    public String username;
    public String password;

    public User(long userId, String username, String password){
        this.userId=userId;
        this.username=username;
        this.password=password;
    }

}