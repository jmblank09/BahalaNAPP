package com.reginalddc.bahalanapp.Model;

/**
 * Created by reginalddc on 03/04/2017.
 */

public class User {

    private static int user_ID;
    private static String username;
    private static String token;
    private static String firstName;

    public User(){}

    public User(int user_ID, String username, String token, String firstName) {
        this.user_ID = user_ID;
        this.username = username;
        this.token = token;
        this.firstName = firstName;
    }

    public static int getUser_ID() {
        return user_ID;
    }

    public static void setUser_ID(int user_ID) {
        User.user_ID = user_ID;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        User.token = token;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }
}
