package ntu.nghia.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ntu.nghia.project.database.User;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.UserViewHolder> {

    private List<User> userList;

    public void setData(List<User> list) {
        this.userList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        if (user == null) return;

        // 1. Set số thứ tự (Rank) = vị trí + 1
        holder.tvRank.setText(String.valueOf(position + 1));

        // 2. Set Tên
        holder.tvName.setText(user.name);

        // --- SỬA LẠI DÒNG NÀY ---
        // Thay .bestScore thành .score
        holder.tvScore.setText(String.valueOf(user.score));
        // ------------------------

        // (Tùy chọn) Đổi màu Top 1
        if (position == 0) {
            holder.tvRank.setTextColor(android.graphics.Color.parseColor("#FFD700")); // Vàng
        } else {
            holder.tvRank.setTextColor(android.graphics.Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        if (userList != null) return userList.size();
        return 0;
    }
    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView tvRank, tvName, tvScore;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRank = itemView.findViewById(R.id.tvItemRank);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvScore = itemView.findViewById(R.id.tvItemScore);
        }
    }
}