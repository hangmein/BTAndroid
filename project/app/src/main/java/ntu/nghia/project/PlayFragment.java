package ntu.nghia.project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PlayFragment extends Fragment {

    public PlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    // Logic của game (ví dụ: load câu hỏi, xử lý đáp án) sẽ được thêm vào onViewCreated sau.
    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Ví dụ: Load câu hỏi đầu tiên
        // TextView tvQuestion = view.findViewById(R.id.tvQuestionText);
        // tvQuestion.setText("Đây là câu hỏi đầu tiên...");
    }
    */
}