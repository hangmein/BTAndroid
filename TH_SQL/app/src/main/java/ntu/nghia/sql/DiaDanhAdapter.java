package ntu.nghia.sql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiaDanhAdapter extends RecyclerView.Adapter<DiaDanhAdapter.ViewHolder> {

    private ArrayList<DiaDanh> listDiaDanh;
    private Context context;

    public DiaDanhAdapter(ArrayList<DiaDanh> listDiaDanh, Context context) {
        this.listDiaDanh = listDiaDanh;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Gắn layout item_dia_danh.xml vào mỗi dòng
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dia_danh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy dữ liệu tại vị trí position
        DiaDanh diaDanh = listDiaDanh.get(position);

        // Gán dữ liệu lên giao diện
        holder.txtTen.setText(diaDanh.getTen());
        holder.imgHinh.setImageResource(diaDanh.getHinhAnhResId());

        // Xử lý sự kiện khi ấn vào một dòng
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietActivity.class);
                // Truyền cả đối tượng DiaDanh sang màn hình chi tiết
                // (Yêu cầu class DiaDanh phải implements Serializable)
                intent.putExtra("object_dia_danh", diaDanh);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDiaDanh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView txtTen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgHinhNho);
            txtTen = itemView.findViewById(R.id.txtTenNho);
        }
    }
}