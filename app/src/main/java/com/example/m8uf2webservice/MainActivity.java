package com.example.m8uf2webservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etFirstLetter;
    TextView tvNom;
    TextView tvArea;
    ImageView ivThumb;
    Button btnSearch;

    ArrayList<Meal> mealsList = new ArrayList<>();
    CustomAdapter customAdapter;
    ListView listView;

    MealService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        etFirstLetter = findViewById(R.id.etFirstLetter);
        btnSearch = findViewById(R.id.btSearch);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MealService.class);


        customAdapter = new CustomAdapter(this, R.layout.meal_layout, mealsList);
        listView.setAdapter(customAdapter);
    }

    public void onClickRandom(View view) {

        Call<Example> call = service.getMealByID();

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Example example = response.body();
                try {
                    if (example != null) {
                        mealsList.clear();
                        mealsList.addAll(example.getMeals());
                        customAdapter.notifyDataSetChanged();
                    }
                } catch (Exception ex) {
                    Log.i("tagg", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error with the API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickByLetter(View view) {

        Call<Example> call = service.getMealByLetter(etFirstLetter.getText().toString());

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Example example = response.body();

                try {
                    if (example != null) {
                        mealsList.clear();
                        mealsList.addAll(example.getMeals());
                        customAdapter.notifyDataSetChanged();
                    }
                } catch (Exception ex) {
                    Log.i("tagg", ex.toString());
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error with the API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
