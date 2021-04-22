package com.example.mortagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtResultLabel, resultValue;
    EditText edPrincipalAmount, edInterestRate, edPeriod;
    Spinner spinnerFrequency;
    Button btnCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultLabel = findViewById(R.id.textViewResultLabel);
        resultValue = findViewById(R.id.txtViewResultValue);
        btnCalculate = findViewById(R.id.buttonCalculate);
        edPrincipalAmount = findViewById(R.id.editTextPrincipalAmount);
        edInterestRate = findViewById(R.id.editTextInterestRate);
        edPeriod = findViewById(R.id.editTextPeriod);
        spinnerFrequency = findViewById(R.id.spinnerFrequency);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // P = amount of loan
                // n = number of installments
                // i = interest rate

                double P = Double.parseDouble(edPrincipalAmount.getText().toString());
                double i = Double.parseDouble(edInterestRate.getText().toString());
                double n = Double.parseDouble(edPeriod.getText().toString());
                double total = 0;
                String resultLabel = "";
                switch (spinnerFrequency.getSelectedItemPosition()){
                    case 0:
                        n= n*12;
                        i = i/12;
                        resultLabel = "Monthly Payment";
                        break;
                    case 1:
                        n= n*(12*4);
                        i = i/(12*4);
                        resultLabel = "Weekly Payment";
                        break;
                    case 2:
                        n= n*(12*2);
                        i = i/(12*2);
                        resultLabel = "Bi-Weekly Payment";
                        break;
                }

                double numerator = i * Math.pow((1 + i),n);
                double denominator = Math.pow((1 + i),n) - 1;
                total = (P * numerator / denominator);

                txtResultLabel.setText(resultLabel);
                resultValue.setText(Math.ceil(total)+"");


            }
        });

    }
}