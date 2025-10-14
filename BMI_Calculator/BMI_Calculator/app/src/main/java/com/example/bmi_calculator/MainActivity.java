package com.example.bmi_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout heightLayout;
    TextInputEditText heightInput, weightInput;
    Button btnCalculate;
    TextView bmiValue, bmiStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightLayout = findViewById(R.id.heightLayout);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        btnCalculate = findViewById(R.id.btnCalculate);
        bmiValue = findViewById(R.id.bmiValue);
        bmiStatus = findViewById(R.id.bmiStatus);
        //.setHintTextAppearance(R.style.CustomHintStyle);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });

    }
    void Calculate(){
        String strHeightInput = heightInput.getText().toString().trim();
        String strWeightInput = weightInput.getText().toString().trim();
        if (strHeightInput.isEmpty() || strWeightInput.isEmpty())
        {
            Toast.makeText(this,"Nhập vào thằng lồn",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            float heightCm = Float.parseFloat(strHeightInput);
            float weight = Float.parseFloat(strWeightInput);
            if (heightCm <= 0 || weight <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }


            float heightM = heightCm / 100; // Đổi cm sang m
            float bmi = weight / (heightM * heightM);

            bmiValue.setText(String.format("%.1f", bmi));
            bmiStatus.setText(getBMIStatus(bmi));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "lỗi gì rồi thằng lồn", Toast.LENGTH_SHORT).show();
        }

    }
    private String getBMIStatus(float bmi)
    {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 24.9)
            return "Normal weight";
        else if (bmi < 29.9)
            return "Overweight";
        else
            return "Obese";
    }
}