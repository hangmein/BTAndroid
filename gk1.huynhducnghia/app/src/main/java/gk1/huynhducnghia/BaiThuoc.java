package gk1.huynhducnghia;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BaiThuoc extends AppCompatActivity {

    RecyclerView recyclerViewBaiThuoc;
    BaiThuocAdapter adapter;
    List<BaiThuocModel> listBaiThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai_thuoc);

        recyclerViewBaiThuoc = findViewById(R.id.recyclerViewBaiThuoc);
        listBaiThuoc = new ArrayList<>();

        listBaiThuoc.add(new BaiThuocModel("Bài thuốc 1", "Trị cảm cúm", R.mipmap.thuoc1));
        listBaiThuoc.add(new BaiThuocModel("Bài thuốc 2", "Trị đau họng", R.mipmap.thuoc2));
        listBaiThuoc.add(new BaiThuocModel("Bài thuốc 3", "Trị đau dạ dày", R.mipmap.thuoc3));
        listBaiThuoc.add(new BaiThuocModel("Bài thuốc 4", "Trị mất ngủ", R.mipmap.thuoc4));

        adapter = new BaiThuocAdapter(this, listBaiThuoc);
        recyclerViewBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBaiThuoc.setAdapter(adapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}