package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

    public class MainActivity extends AppCompatActivity {

        ListView cityList;
        ArrayAdapter<String> cityAdapter;
        ArrayList<String> dataList;

        Button buttonAdd, buttonDelete;
        EditText inputCity;

        int selectedIndex = -1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            cityList = findViewById(R.id.city_list);
            buttonAdd = findViewById(R.id.button_add);
            buttonDelete = findViewById(R.id.button_delete);

            String[] cities = {"Edmonton", "Calgary", "Toronto", "Winnipeg", "Vancouver"};
            dataList = new ArrayList<>(Arrays.asList(cities));

            cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
            cityList.setAdapter(cityAdapter);


            // tap a city to "select" it
            cityList.setOnItemClickListener((parent, view, position, id) -> {
                selectedIndex = position;
                Toast.makeText(this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
            });

            inputCity = findViewById(R.id.input_city);

            // ADD: just add a default new city name
            buttonAdd.setOnClickListener(v -> {
                String city = inputCity.getText().toString().trim();

                if (city.isEmpty()) {
                    Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataList.add(city);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");   // clear input
            });

            // DELETE: remove the selected city
            buttonDelete.setOnClickListener(v -> {
                if (selectedIndex == -1) {
                    Toast.makeText(this, "Tap a city first", Toast.LENGTH_SHORT).show();
                    return;
                }

                dataList.remove(selectedIndex);
                selectedIndex = -1;
                cityAdapter.notifyDataSetChanged();
            });
        }
    }
