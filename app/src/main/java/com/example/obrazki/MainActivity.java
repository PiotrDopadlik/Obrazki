package com.example.obrazki;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText numberEditText;
    ImageView choosenImageView;
    Button previous;
    Button next;
    Spinner numberSpinner;
    Spinner numberSpinnerFull;
    ArrayList<Integer> values = new ArrayList<>();
    ArrayList<Integer> pictures = new ArrayList<>();
    int index = 0;

    private void fillWithPictures() {
        pictures.add(R.drawable.ic_android_black_24dp);
        pictures.add(R.drawable.ic_baseline_airplanemode_active_24);
        pictures.add(R.drawable.ic_baseline_back_hand_24);
        pictures.add(R.drawable.ic_baseline_brightness_1_24);
        pictures.add(R.drawable.ic_baseline_brightness_1_24);
    }

    private void fillWithValues() {
        for (int i = 1; i <= pictures.size(); i++) {
            values.add(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberEditText = findViewById(R.id.editTextNumber);
        fillWithPictures();
        fillWithValues();
        choosenImageView = findViewById(R.id.imageView);
        previous = findViewById(R.id.button1);
        next = findViewById(R.id.button2);
        numberSpinner = findViewById(R.id.spinner);
        numberSpinnerFull = findViewById(R.id.spinner2);
        ArrayAdapter<Integer> adapterForSpinner = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_list_item_1, values
        );

        numberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(MainActivity.this, charSequence, Toast.LENGTH_SHORT).show();
                if (!charSequence.toString().isEmpty()) {
                    index = Integer.valueOf(charSequence.toString());
                    if (index < 0 || index >= pictures.size())
                        index = 1;
                    choosenImageView.setImageResource(pictures.get(index));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index--;
                if (index < 0) {
                    index = pictures.size() - 1;
                }
                choosenImageView.setImageResource(pictures.get(index));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index++;
                if (index > pictures.size() - 1) {
                    index = 0;
                }
                choosenImageView.setImageResource(pictures.get(index));
            }
        });

        numberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choosenIndex = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, choosenIndex, Toast.LENGTH_SHORT).show();
                choosenImageView.setImageResource(pictures.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberSpinnerFull.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String choosenIndex = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(MainActivity.this, choosenIndex, Toast.LENGTH_SHORT).show();
                choosenImageView.setImageResource(pictures.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        numberSpinnerFull.setAdapter(adapterForSpinner);
    }
}