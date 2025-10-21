package ntu.nghia.money;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<loaiTien> tatCaLoaiTien;
    loaiTien tienChonFrom;
    loaiTien tienChonTo;
    ImageView flagFrom, flagTo;
    TextView textCurrencyFrom, textCurrencyTo, textResult;
    ConstraintLayout currencySelectorFrom, currencySelectorTo;
    EditText editTextAmount;
    Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        taoDanhSachTien();
        anhXaView();

        tienChonFrom = tatCaLoaiTien.get(0);
        tienChonTo = tatCaLoaiTien.get(1);
        capNhatGiaoDien();

        currencySelectorFrom.setOnClickListener(v -> {
            showCurrencyDialog(true);
        });

        currencySelectorTo.setOnClickListener(v -> {
            showCurrencyDialog(false);
        });

        buttonConvert.setOnClickListener(v -> {
            tinhToanChuyenDoi(); // Gọi hàm tính toán
        });
    }
    private void showCurrencyDialog(boolean isFrom) {
        String[] tenTienArray = new String[tatCaLoaiTien.size()];
        for (int i = 0; i < tatCaLoaiTien.size(); i++) {
            tenTienArray[i] = tatCaLoaiTien.get(i).getTenTien();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn loại tiền");
        builder.setItems(tenTienArray, (dialog, which) -> {
            if (isFrom) {
                tienChonFrom = tatCaLoaiTien.get(which);
            } else {
                tienChonTo = tatCaLoaiTien.get(which);
            }
            capNhatGiaoDien();
        });
        builder.create().show();
    }
    private void taoDanhSachTien() {
        tatCaLoaiTien = new ArrayList<>();
        tatCaLoaiTien.add(new loaiTien("USD", R.drawable.flag_of_the_united_states, 1.0));
        tatCaLoaiTien.add(new loaiTien("VND", R.drawable.flag_of_vietnam, 25000.0));
        tatCaLoaiTien.add(new loaiTien("BAHT", R.drawable.flag_of_thailand, 36.5));
    }
    private void anhXaView() {
        currencySelectorFrom = findViewById(R.id.currencySelectorFrom);
        currencySelectorTo = findViewById(R.id.currencySelectorTo);

        flagFrom = findViewById(R.id.flagFrom);
        textCurrencyFrom = findViewById(R.id.textCurrencyFrom);

        flagTo = findViewById(R.id.flagTo);
        textCurrencyTo = findViewById(R.id.textCurrencyTo);

        editTextAmount = findViewById(R.id.editTextAmount);
        textResult = findViewById(R.id.textResult);
        buttonConvert = findViewById(R.id.buttonConvert);
    }
    private void capNhatGiaoDien() {
        flagFrom.setImageResource(tienChonFrom.getFlag());
        textCurrencyFrom.setText(tienChonFrom.getTenTien());

        flagTo.setImageResource(tienChonTo.getFlag());
        textCurrencyTo.setText(tienChonTo.getTenTien());
    }
    private void tinhToanChuyenDoi() {
        String amountString = editTextAmount.getText().toString();
        if (amountString.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số tiền", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double amountToConvert = Double.parseDouble(amountString);
            double tiGiaFrom = tienChonFrom.getTiGia();
            double tiGiaTo = tienChonTo.getTiGia();
            double result = (amountToConvert / tiGiaFrom) * tiGiaTo;
            DecimalFormat formatter = new DecimalFormat("#,##0.00");
            String resultString = formatter.format(result);
            textResult.setText(resultString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Số tiền không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
