package com.example.m8uf2webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

    @GET("random.php")
    Call<Example> getMealByID();

    @GET("search.php")
    Call<Example> getMealByLetter(@Query("f") String f);
}
