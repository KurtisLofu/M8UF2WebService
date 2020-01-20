package com.example.m8uf2webservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Meal> {
    Context ctx;
    int mealLayout;
    List<Meal> llistaMeals;


    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.mealLayout = resource;
        this.llistaMeals = objects;
    }

    //Aquest mètode es llança automaticament cada vegada per element
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = LayoutInflater.from(ctx).inflate(mealLayout, parent, false);
        //parent: el carga dins de l'elemnt pare, el listView que rep de l'activity

        //Obtenim el elements de la llista
        Meal meal = llistaMeals.get(position);

        //Rescatem els elements de la IU de la plantillaLayout
        TextView name = v.findViewById(R.id.textViewNom);
        TextView area = v.findViewById(R.id.textViewArea);
        ImageView thumb = v.findViewById(R.id.ivThumbb);

        //Fem un set de la info de l'element actual

        name.setText(meal.getStrMeal());
        area.setText(meal.getStrArea());
        Picasso.get().load(meal.getStrMealThumb()).into(thumb);
        return v;
    }
}