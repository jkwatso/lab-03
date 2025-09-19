package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;


import android.app.DialogFragment;

public class AddCityFragment extends DialogFragment {


    public void show(FragmentManager supportFragmentManager, String editCity) {
    }

    interface AddCityDialogListener {
        void addCity(City city);
        void updateCity(City old, String newCity, String newProvince);
    }

    public City editCity;
    //public updateCity UpCity;

    public AddCityFragment(){

    }

    public AddCityFragment(City city) {
        this.editCity = city;
    }



    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        if(editCity == null) {
            return builder
                    .setView(view)
                    .setTitle("Add a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Add", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        listener.addCity(new City(cityName, provinceName));
                    })
                    .create();
        } else {
            return builder
                    .setView(view)
                    .setTitle("Edit a city")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("Update", (dialog, which) -> {
                        String cityName = editCityName.getText().toString();
                        String provinceName = editProvinceName.getText().toString();
                        listener.updateCity(editCity, cityName, provinceName);
                    })
                    .create();
        }

    }
}



