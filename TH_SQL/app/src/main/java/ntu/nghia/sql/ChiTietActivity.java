package ntu.nghia.sql;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ChiTietActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        ImageView imgHinh = findViewById(R.id.imgHinhLon);
        TextView txtTen = findViewById(R.id.txtTenLon);
        TextView txtMoTa = findViewById(R.id.txtMoTaChiTiet);

        if (getIntent().hasExtra("object_dia_danh")) {
            DiaDanh diaDanh = (DiaDanh) getIntent().getSerializableExtra("object_dia_danh");

            if (diaDanh != null) {
                txtTen.setText(diaDanh.getTen());
                txtMoTa.setText(diaDanh.getMoTa());
                imgHinh.setImageResource(diaDanh.getHinhAnhResId());
            }
        }
    }
}
