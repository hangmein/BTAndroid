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

    // --- Biến toàn cục cho các View ---
    TextInputLayout heightLayout;
    TextInputEditText heightInput, weightInput;
    Button btnCalculate;
    TextView bmiValue, bmiStatus;
    // ---------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Đảm bảo bạn đang dùng đúng file layout (XML)
        setContentView(R.layout.activity_bmi);

        // --- Logic tìm View và gán sự kiện ---
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
        // --------------------------------------

        // Giữ lại code EdgeToEdge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // --- Các phương thức tính toán ---
    void Calculate(){
        String strHeightInput = heightInput.getText().toString().trim();
        String strWeightInput = weightInput.getText().toString().trim();

        // Kiểm tra đầu vào rỗng
        if (strHeightInput.isEmpty() || strWeightInput.isEmpty())
        {
            // Tôi đã sửa lại câu thông báo cho lịch sự hơn
            Toast.makeText(this,"Vui lòng nhập đầy đủ",Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            float heightCm = Float.parseFloat(strHeightInput);
            float weight = Float.parseFloat(strWeightInput);

            // Kiểm tra đầu vào hợp lệ
            if (heightCm <= 0 || weight <= 0) {
                Toast.makeText(this, "Chiều cao và cân nặng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            float heightM = heightCm / 100; // Đổi cm sang m
            float bmi = weight / (heightM * heightM);

            // Cập nhật kết quả lên TextView
            bmiValue.setText(String.format("%.1f", bmi));
            bmiStatus.setText(getBMIStatus(bmi));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Đầu vào không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMIStatus(float bmi)
    {
        if (bmi < 18.5)
            return "Underweight"; // Gầy
        else if (bmi < 24.9)
            return "Normal weight"; // Bình thường
        else if (bmi < 29.9)
            return "Overweight"; // Thừa cân
        else
            return "Obese"; // Béo phì
    }
    // ---------------------------------
}