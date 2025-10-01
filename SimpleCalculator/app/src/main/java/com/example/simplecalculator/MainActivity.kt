package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aInput = findViewById<EditText>(R.id.a_variable)
        val bInput = findViewById<EditText>(R.id.b_variable)
        val resultField = findViewById<EditText>(R.id.result_variable)

        val btnPlus = findViewById<Button>(R.id.button_plus)
        val btnMinus = findViewById<Button>(R.id.button_minus)
        val btnMulti = findViewById<Button>(R.id.button_multi)
        val btnDivide = findViewById<Button>(R.id.button_divide)

        fun getValues(): Pair<Double, Double>? {
            val aText = aInput.text.toString()
            val bText = bInput.text.toString()

            if (aText.isEmpty() || bText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ a và b", Toast.LENGTH_SHORT).show()
                return null
            }

            val a = aText.toDoubleOrNull()
            val b = bText.toDoubleOrNull()

            if (a == null || b == null) {
                Toast.makeText(this, "Giá trị nhập không hợp lệ", Toast.LENGTH_SHORT).show()
                return null
            }
            return Pair(a, b)
        }

        // Sự kiện nút cộng
        btnPlus.setOnClickListener {
            val values = getValues() ?: return@setOnClickListener
            val result = values.first + values.second
            resultField.setText(result.toString())
        }

        // Sự kiện nút trừ
        btnMinus.setOnClickListener {
            val values = getValues() ?: return@setOnClickListener
            val result = values.first - values.second
            resultField.setText(result.toString())
        }

        // Sự kiện nút nhân
        btnMulti.setOnClickListener {
            val values = getValues() ?: return@setOnClickListener
            val result = values.first * values.second
            resultField.setText(result.toString())
        }

        // Sự kiện nút chia
        btnDivide.setOnClickListener {
            val values = getValues() ?: return@setOnClickListener
            if (values.second == 0.0) {
                Toast.makeText(this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show()
            } else {
                val result = values.first / values.second
                resultField.setText(result.toString())
            }
        }
    }
}
