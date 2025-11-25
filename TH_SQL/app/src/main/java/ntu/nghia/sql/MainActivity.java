package ntu.nghia.sql;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DiaDanhAdapter adapter;
    ArrayList<DiaDanh> listDiaDanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewDiaDanh);

        // 1. Tạo dữ liệu mẫu (Hardcode)
        listDiaDanh = new ArrayList<>();

        // Lưu ý: R.drawable.ic_launcher_background là hình mặc định.
        // Bạn hãy chép ảnh của bạn vào res/drawable và đổi lại tên ở đây.
        listDiaDanh.add(new DiaDanh("Hồ Gươm",
                "Hồ Gươm hay hồ Hoàn Kiếm là một hồ nước ngọt tự nhiên nằm ở trung tâm thành phố Hà Nội. Hồ có diện tích khoảng 12 ha...",
                R.drawable.ic_launcher_background));

        listDiaDanh.add(new DiaDanh("Vịnh Hạ Long",
                "Vịnh Hạ Long là một vịnh nhỏ thuộc phần bờ tây vịnh Bắc Bộ tại khu vực biển Đông Bắc Việt Nam, bao gồm vùng biển đảo của thành phố Hạ Long...",
                R.drawable.ic_launcher_background));

        listDiaDanh.add(new DiaDanh("Phố Cổ Hội An",
                "Phố cổ Hội An là một đô thị cổ nằm ở hạ lưu sông Thu Bồn, thuộc vùng đồng bằng ven biển tỉnh Quảng Nam...",
                R.drawable.ic_launcher_background));

        // 2. Cài đặt Adapter
        adapter = new DiaDanhAdapter(listDiaDanh, this);

        // 3. Cài đặt RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}