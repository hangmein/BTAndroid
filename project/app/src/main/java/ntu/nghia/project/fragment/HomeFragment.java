package ntu.nghia.project.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import ntu.nghia.project.R;
import ntu.nghia.project.model.GameMode;

public class HomeFragment extends Fragment {

    private List<GameMode> modeList;
    private int currentModeIndex = 0;
    private ImageView imgMode;
    private TextView tvModeName;
    private TextView tvModeDesc;
    private ImageButton btnArrowLeft;
    private ImageButton btnArrowRight;
    private Button btnStartGame;


    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setupModes();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void setupModes() {
        modeList = new ArrayList<>();
        modeList.add(new GameMode(1, "IT Cơ bản", "Phần cứng, Windows, Mạng", android.R.drawable.ic_dialog_info));
        modeList.add(new GameMode(2, "SENIOR DEV", "Java, Android, SQL", android.R.drawable.ic_dialog_dialer));
        modeList.add(new GameMode(3, "PROJECT LEAD", "System Design, Security, Pattern", android.R.drawable.ic_dialog_map));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgMode = view.findViewById(R.id.imgMode);
        tvModeName = view.findViewById(R.id.tvModeName);
        tvModeDesc = view.findViewById(R.id.tvModeDesc);
        btnArrowLeft = view.findViewById(R.id.btnArrowLeft);
        btnArrowRight = view.findViewById(R.id.btnArrowRight);
        btnStartGame = view.findViewById(R.id.btnStartGame);
        updateModeDisplay();
        btnArrowRight.setOnClickListener(v -> {
            currentModeIndex = (currentModeIndex + 1) % modeList.size();
            updateModeDisplay();
        });
        btnArrowLeft.setOnClickListener(v -> {
            currentModeIndex = (currentModeIndex - 1 + modeList.size()) % modeList.size();
            updateModeDisplay();
        });
        btnStartGame.setOnClickListener(v -> {
            GameMode selectedMode = modeList.get(currentModeIndex);
            Bundle bundle = new Bundle();
            bundle.putInt("selected_mode_id", selectedMode.getId());
            Navigation.findNavController(v).navigate(R.id.action_nav_home_to_nav_play, bundle);
        });
    }
    private void updateModeDisplay() {
        GameMode currentMode = modeList.get(currentModeIndex);

        tvModeName.setText(currentMode.getTenCheDo());
        tvModeDesc.setText(currentMode.getMoTa());
        imgMode.setImageResource(currentMode.getAnhDaiDien());
        TextView tvNavBarTitle = requireActivity().findViewById(R.id.tvTitle);
        if (tvNavBarTitle != null) {
        }
    }
}