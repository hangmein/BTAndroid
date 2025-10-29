package gk1.huynhducnghia;

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

public class BMI extends AppCompatActivity {

    TextInputLayout heightLayout;
    TextInputEditText heightInput, weightInput;
    Button btnCalculate;
    TextView bmiValue, bmiStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);

        heightLayout = findViewById(R.id.heightLayout);
        heightInput = findViewById(R.id.heightInput);
        weightInput = findViewById(R.id.weightInput);
        btnCalculate = findViewById(R.id.btnCalculate);
        bmiValue = findViewById(R.id.bmiValue);
        bmiStatus = findViewById(R.id.bmiStatus);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });
    }
    void Calculate()
    {
        String strHeightInput = heightInput.getText().toString().trim();
        String strWeightInput = weightInput.getText().toString().trim();
        if (strHeightInput.isEmpty() || strWeightInput.isEmpty())
        {
            Toast.makeText(this,"không được bỏ trống số liệu",Toast.LENGTH_SHORT).show();
            return;
        }
            float heightCm = Float.parseFloat(strHeightInput);
            float weight = Float.parseFloat(strWeightInput);
            if (heightCm <= 0 || weight <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }


            float heightM = heightCm / 100;
            float bmi = weight / (heightM * heightM);

            bmiValue.setText(String.format("%.1f", bmi));
            bmiStatus.setText(setBmiStatus(bmi));
    }
    public String setBmiStatus(float a)
    {
        if (a < 18.5)
            return "Gầy";
        else if (a < 24.9)
            return "Bình thường";
        else if (a < 29.9)
            return "Béo";
        else
            return "Quá Béo";
    }
}
