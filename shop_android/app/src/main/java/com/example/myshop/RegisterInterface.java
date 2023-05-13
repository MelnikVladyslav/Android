package com.example.myshop;

public interface RegisterInterface {
    @POST("/register")
    Call<User> registerUser(@Body User user);
}
