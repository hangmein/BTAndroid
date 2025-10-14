package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText a, b, ketqua;
    private Button btnPlus, btnMinus, btnMulti, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = findViewById(R.id.a_variable);
        b = findViewById(R.id.b_variable);
        ketqua = findViewById(R.id.result_variable);

        btnPlus = findViewById(R.id.button_plus);
        btnMinus = findViewById(R.id.button_minus);
        btnMulti = findViewById(R.id.button_multi);
        btnDivide = findViewById(R.id.button_divide);
        IListener l = new IListener();
        btnPlus.setOnClickListener(l);
        btnMinus.setOnClickListener(l);
        btnMulti.setOnClickListener(l);
        btnDivide.setOnClickListener(l);


    }
    class IListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            int id = v.getId();
            if (a.getText().toString().trim().isEmpty() || b.getText().toString().trim().isEmpty())
            {
                Toast.makeText(MainActivity.this,"Nhập số vào, bướng à ?",Toast.LENGTH_SHORT).show();
                return;
            }
            double numA = Double.parseDouble(a.getText().toString().trim());
            double numB = Double.parseDouble(b.getText().toString().trim());
            try {
                if (id == R.id.button_plus){
                    ketqua.setText(String.format("%.2f",numA + numB));
                }
                if (id == R.id.button_minus){
                    ketqua.setText(String.format("%.2f",numA - numB));
                }
                if (id == R.id.button_multi){
                    ketqua.setText(String.format("%.2f",numA * numB));
                }
                if (id == R.id.button_divide){
                    if (numB == 0)
                    {
                        ketqua.getText();
                        Toast.makeText(MainActivity.this,"Mày thích b = 0 không ?",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ketqua.setText(String.format("%.2f",numA / numB));
                }
            } catch (Exception e)
            {
                Toast.makeText(MainActivity.this,"Lỗi gì rồi thangngu",Toast.LENGTH_SHORT).show();
            }

        }
    }
}