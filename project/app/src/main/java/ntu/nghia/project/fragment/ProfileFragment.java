package ntu.nghia.project.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ntu.nghia.project.LeaderboardAdapter;
import ntu.nghia.project.R;
import ntu.nghia.project.database.AppDatabase;
import ntu.nghia.project.database.db_user.User;

public class ProfileFragment extends Fragment {
    private RecyclerView rcvLeaderboard;
    private LeaderboardAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        rcvLeaderboard = view.findViewById(R.id.rvLeaderboard);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvLeaderboard.setLayoutManager(linearLayoutManager);

        adapter = new LeaderboardAdapter();
        rcvLeaderboard.setAdapter(adapter);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData() {
        try {
            AppDatabase db = AppDatabase.getDatabase(getContext());
            List<User> listReal = db.userDao().getAllUsers();
            if (listReal != null) {
                adapter.setData(listReal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}