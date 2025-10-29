package gk1.huynhducnghia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.BaiThuocViewHolder> {

    private List<BaiThuocModel> danhSachBaiThuoc;
    private Context context;

    public BaiThuocAdapter(Context context, List<BaiThuocModel> danhSachBaiThuoc) {
        this.context = context;
        this.danhSachBaiThuoc = danhSachBaiThuoc;
    }

    @NonNull
    @Override
    public BaiThuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_thuoc, parent, false);
        return new BaiThuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiThuocViewHolder holder, int position) {
        BaiThuocModel baiThuoc = danhSachBaiThuoc.get(position);

        holder.textTieuDe.setText(baiThuoc.getTieuDe());
        holder.textMoTa.setText(baiThuoc.getMoTa());
        holder.imageBaiThuoc.setImageResource(baiThuoc.getImageResourceId());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, baiThuoc.getTieuDe(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return danhSachBaiThuoc.size();
    }

    public static class BaiThuocViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBaiThuoc;
        TextView textTieuDe;
        TextView textMoTa;

        public BaiThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBaiThuoc = itemView.findViewById(R.id.imageBaiThuoc);
            textTieuDe = itemView.findViewById(R.id.textTieuDe);
            textMoTa = itemView.findViewById(R.id.textMoTa);
        }
    }
}