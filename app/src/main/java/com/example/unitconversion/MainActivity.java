package com.example.unitconversion;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editTextLengthInput, editTextWeightInput;
    TextView textViewLengthResultValue, textViewWeightResultValue;
    Spinner spinnerLength, spinnerWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLengthInput = findViewById(R.id.editTextLengthInput);
        editTextWeightInput = findViewById(R.id.editTextWeightInput);
        textViewLengthResultValue = findViewById(R.id.textViewLengthResultValue);
        textViewWeightResultValue = findViewById(R.id.textViewWeightResultValue);
        spinnerLength = findViewById(R.id.spinnerLength);
        spinnerWeight = findViewById(R.id.spinnerWeight);

        // Populate spinners with unit options
        ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.length_units, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLength.setAdapter(lengthAdapter);

        ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_units, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWeight.setAdapter(weightAdapter);
    }

    public void convertUnits(View view) {
        // Convert Length
        String lengthInputText = editTextLengthInput.getText().toString();
        if (!lengthInputText.isEmpty()) {
            double lengthInput = Double.parseDouble(lengthInputText);
            String selectedLengthUnit = spinnerLength.getSelectedItem().toString();
            double lengthResult;
            if (selectedLengthUnit.equals("cm")) {
                lengthResult = lengthInput / 100; // Convert cm to m
            } else {
                lengthResult = lengthInput; // No conversion needed if the input is already in meters
            }
            textViewLengthResultValue.setText(String.format("%.2f m", lengthResult));
        } else {
            textViewLengthResultValue.setText("Please enter a value.");
        }

        // Convert Weight
        String weightInputText = editTextWeightInput.getText().toString();
        if (!weightInputText.isEmpty()) {
            double weightInput = Double.parseDouble(weightInputText);
            String selectedWeightUnit = spinnerWeight.getSelectedItem().toString();
            double weightResult;
            if (selectedWeightUnit.equals("g")) {
                weightResult = weightInput / 1000; // Convert g to kg
            } else {
                weightResult = weightInput; // No conversion needed if the input is already in kilograms
            }
            textViewWeightResultValue.setText(String.format("%.2f kg", weightResult));
        } else {
            textViewWeightResultValue.setText("Please enter a value.");
        }
    }
}
