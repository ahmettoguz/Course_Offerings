package com.ctis487.ahmetoguzergin.hw2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ctis487.ahmetoguzergin.hw2.R;

import java.util.ArrayList;

public class custom_Spinner_Adapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> years;

    public custom_Spinner_Adapter(@NonNull Context context, ArrayList<String> years) {
        super(context, R.layout.custom_spinner_year, years);
        this.context = context;
        this.years = years;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return prepareView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return prepareView(position, convertView, parent);
    }

    private View prepareView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_spinner_year, parent, false);

        LinearLayout ll = view.findViewById(R.id.yearSpinner_ll);
        RatingBar rb = view.findViewById(R.id.yearSpinner_rb);
        TextView tv = view.findViewById(R.id.yearSpinner_tv);

        String currentYearStr = years.get(position);
        int currentYearInt = position;
        if (position == 0)
            currentYearInt = 5;

        rb.setRating(currentYearInt);
        rb.setEnabled(false);
        tv.setText(currentYearStr);

        return view;
    }
}
