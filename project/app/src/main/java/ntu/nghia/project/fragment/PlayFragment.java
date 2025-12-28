package ntu.nghia.project.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import ntu.nghia.project.R;
import ntu.nghia.project.database.AppDatabase;
import ntu.nghia.project.database.Question;
import ntu.nghia.project.database.QuestionDao;
import ntu.nghia.project.database.User;
import ntu.nghia.project.database.UserDao;

public class PlayFragment extends Fragment {

    private TextView tvQuestionCount, tvScore, tvTimer, tvQuestionContent;
    private Button btnOptionA, btnOptionB, btnOptionC, btnOptionD;

    private List<Question> listQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private CountDownTimer countDownTimer;
    private static final long TIME_LIMIT = 30000;

    private ImageButton btnHelp5050, btnHelpDocs, btnHelpSwitch;

    private boolean is5050Used = false;
    private boolean isDocsUsed = false;
    private boolean isSwitchUsed = false;

    public PlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnHelp5050 = view.findViewById(R.id.btnHelp5050);
        btnHelpDocs = view.findViewById(R.id.btnHelpDocs);
        btnHelpSwitch = view.findViewById(R.id.btnHelpSwitch);

        btnHelp5050.setOnClickListener(v -> {
            if (!is5050Used) {
                handle5050();
                is5050Used = true;
                disableLifelineButton(btnHelp5050);
            }
        });

        btnHelpDocs.setOnClickListener(v -> {
            if (!isDocsUsed) {
                handleDocs();
                isDocsUsed = true;
                disableLifelineButton(btnHelpDocs);
            }
        });

        btnHelpSwitch.setOnClickListener(v -> {
            if (!isSwitchUsed) {
                handleSwitch();
                isSwitchUsed = true;
                disableLifelineButton(btnHelpSwitch);
            }
        });

        tvQuestionCount = view.findViewById(R.id.tvQuestionCount);
        tvScore = view.findViewById(R.id.tvScore);
        tvTimer = view.findViewById(R.id.tvTimer);
        tvQuestionContent = view.findViewById(R.id.tvQuestionContent);
        btnOptionA = view.findViewById(R.id.btnOptionA);
        btnOptionB = view.findViewById(R.id.btnOptionB);
        btnOptionC = view.findViewById(R.id.btnOptionC);
        btnOptionD = view.findViewById(R.id.btnOptionD);

        AppDatabase db = AppDatabase.getDatabase(requireContext());
        QuestionDao dao = db.questionDao();

        if (dao.countQuestions() == 0) {
            fakeData(dao);
        }

        listQuestions = dao.getQuestionsByMode(1);

        Collections.shuffle(listQuestions);

        if (listQuestions != null && listQuestions.size() > 0) {
            showQuestion();
        } else {
            Toast.makeText(getContext(), "Không có dữ liệu câu hỏi!", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).popBackStack();
        }

        btnOptionA.setOnClickListener(v -> checkAnswer("A", btnOptionA));
        btnOptionB.setOnClickListener(v -> checkAnswer("B", btnOptionB));
        btnOptionC.setOnClickListener(v -> checkAnswer("C", btnOptionC));
        btnOptionD.setOnClickListener(v -> checkAnswer("D", btnOptionD));
    }

    private void showQuestion() {
        resetButtonColors();

        Question q = listQuestions.get(currentQuestionIndex);

        tvQuestionContent.setText(q.content);
        btnOptionA.setText("A. " + q.optionA);
        btnOptionB.setText("B. " + q.optionB);
        btnOptionC.setText("C. " + q.optionC);
        btnOptionD.setText("D. " + q.optionD);

        tvQuestionCount.setText("Question: " + (currentQuestionIndex + 1) + "/" + listQuestions.size());
        tvScore.setText("Score: " + score);

        startTimer();
    }

    private void checkAnswer(String userChoice, Button clickedButton) {
        if (countDownTimer != null) countDownTimer.cancel();

        setButtonsEnabled(false);

        Question currentQ = listQuestions.get(currentQuestionIndex);

        if (userChoice.equals(currentQ.correctAnswer)) {
            clickedButton.setBackgroundColor(Color.parseColor("#4CAF50"));
            score += 100;
            tvScore.setText("Score: " + score);
        } else {
            clickedButton.setBackgroundColor(Color.parseColor("#F44336"));
            showCorrectAnswer(currentQ.correctAnswer);
        }

        new Handler().postDelayed(this::nextQuestion, 1000);
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < listQuestions.size()) {
            setButtonsEnabled(true);
            showQuestion();
        } else {
            endGame();
        }
    }

    private void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();

        countDownTimer = new CountDownTimer(TIME_LIMIT, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                tvTimer.setText("0");
                nextQuestion();
            }
        }.start();
    }

    private void endGame() {
        Toast.makeText(getContext(), "Kết thúc! Tổng điểm: " + score, Toast.LENGTH_LONG).show();
        Navigation.findNavController(requireView());
        showCustomGameOverDialog();
    }

    private void showCorrectAnswer(String correctAnswer) {
        if (correctAnswer.equals("A")) btnOptionA.setBackgroundColor(Color.parseColor("#4CAF50"));
        if (correctAnswer.equals("B")) btnOptionB.setBackgroundColor(Color.parseColor("#4CAF50"));
        if (correctAnswer.equals("C")) btnOptionC.setBackgroundColor(Color.parseColor("#4CAF50"));
        if (correctAnswer.equals("D")) btnOptionD.setBackgroundColor(Color.parseColor("#4CAF50"));
    }

    private void resetButtonColors() {
        int defaultColor = R.drawable.btn_tech;
        btnOptionA.setBackgroundResource(defaultColor);
        btnOptionB.setBackgroundResource(defaultColor);
        btnOptionC.setBackgroundResource(defaultColor);
        btnOptionD.setBackgroundResource(defaultColor);

        btnOptionA.setVisibility(View.VISIBLE);
        btnOptionB.setVisibility(View.VISIBLE);
        btnOptionC.setVisibility(View.VISIBLE);
        btnOptionD.setVisibility(View.VISIBLE);

        setButtonsEnabled(true);
    }

    private void setButtonsEnabled(boolean enable) {
        btnOptionA.setEnabled(enable);
        btnOptionB.setEnabled(enable);
        btnOptionC.setEnabled(enable);
        btnOptionD.setEnabled(enable);
    }

    private void fakeData(QuestionDao dao) {
        dao.insertAll(
                new Question("Java chạy trên nền tảng nào?", "JVM", "JDK", "JRE", "Windows", "A", 1),
                new Question("Biến 'final' trong Java có ý nghĩa gì?", "Không thể thay đổi", "Có thể kế thừa", "Biến cục bộ", "Biến toàn cục", "A", 1),
                new Question("Android dựa trên kernel nào?", "Linux", "Windows", "Unix", "Hybrid", "A", 1),
                new Question("Activity có vòng đời bắt đầu bằng hàm nào?", "onStart", "onCreate", "onResume", "onInit", "B", 1),
                new Question("File layout Android có đuôi là gì?", ".java", ".kt", ".xml", ".class", "C", 1)
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (countDownTimer != null) countDownTimer.cancel();
    }

    private void handle5050() {
        Question currentQ = listQuestions.get(currentQuestionIndex);
        String correct = currentQ.correctAnswer;

        java.util.List<Button> wrongButtons = new java.util.ArrayList<>();
        if (!correct.equals("A")) wrongButtons.add(btnOptionA);
        if (!correct.equals("B")) wrongButtons.add(btnOptionB);
        if (!correct.equals("C")) wrongButtons.add(btnOptionC);
        if (!correct.equals("D")) wrongButtons.add(btnOptionD);

        Collections.shuffle(wrongButtons);

        wrongButtons.get(0).setVisibility(View.INVISIBLE);
        wrongButtons.get(1).setVisibility(View.INVISIBLE);

        Toast.makeText(getContext(), "Đã loại bỏ 2 phương án sai!", Toast.LENGTH_SHORT).show();
    }

    private void handleDocs() {
        Question currentQ = listQuestions.get(currentQuestionIndex);

        String message = "Reading documentation...\nAnalyzing StackOverflow...\n\n";

        if (currentQ.correctAnswer.equals("A")) message += ">> Option A: 85% match\n   Option B: 5%\n   Option C: 4%\n   Option D: 6%";
        else if (currentQ.correctAnswer.equals("B")) message += "   Option A: 10%\n>> Option B: 80% match\n   Option C: 5%\n   Option D: 5%";
        else if (currentQ.correctAnswer.equals("C")) message += "   Option A: 5%\n   Option B: 5%\n>> Option C: 88% match\n   Option D: 2%";
        else message += "   Option A: 8%\n   Option B: 2%\n   Option C: 5%\n>> Option D: 85% match";

        new android.app.AlertDialog.Builder(requireContext())
                .setTitle("System Logs")
                .setMessage(message)
                .setPositiveButton("CLOSE", null)
                .show();
    }

    private void handleSwitch() {
        if (countDownTimer != null) countDownTimer.cancel();

        Toast.makeText(getContext(), "Skipping current task...", Toast.LENGTH_SHORT).show();

        currentQuestionIndex++;
        if (currentQuestionIndex < listQuestions.size()) {
            showQuestion();
        } else {
            endGame();
        }
    }

    private void disableLifelineButton(ImageButton btn) {
        btn.setEnabled(false);
        btn.setAlpha(0.3f);
    }
    private void showCustomGameOverDialog() {
        // 1. Chuẩn bị View từ file XML tùy chỉnh
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.dialog_game_over, null);

        // 2. Ánh xạ các thành phần trong dialogView
        TextView tvFinalScoreDialog = dialogView.findViewById(R.id.tvFinalScoreDialog);
        android.widget.EditText etPlayerName = dialogView.findViewById(R.id.etPlayerName);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnDiscard = dialogView.findViewById(R.id.btnDiscard);

        // 3. Gán dữ liệu điểm số
        tvFinalScoreDialog.setText(String.valueOf(score));

        // 4. Tạo và cấu hình Dialog
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(requireContext());
        builder.setView(dialogView); // Quan trọng: Gán view tùy chỉnh vào
        builder.setCancelable(false); // Không cho bấm ra ngoài để tắt

        final android.app.AlertDialog dialog = builder.create();

        // Mẹo: Làm nền dialog trong suốt để thấy được góc bo tròn của CardView
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        // 5. Xử lý sự kiện nút bấm
        btnSave.setOnClickListener(v -> {
            String playerName = etPlayerName.getText().toString().trim();
            if (playerName.isEmpty()) {
                playerName = "Unknown Agent"; // Tên mặc định nếu để trống
            }
            saveScoreToDatabase(playerName, score);
            dialog.dismiss(); // Đóng dialog
        });

        btnDiscard.setOnClickListener(v -> {
            // Quay về trang chủ mà không lưu
            Navigation.findNavController(requireView()).popBackStack();
            dialog.dismiss();
        });

        dialog.show();
    }

    // Hàm lưu vào Database (Giữ nguyên logic cũ)
    // Thay thế toàn bộ hàm saveScoreToDatabase cũ bằng hàm này:
    private void saveScoreToDatabase(String name, int newScore) {
        Context context = getContext();
        if (context == null) return;

        // Thêm log để soi
        android.util.Log.d("DEBUG_GAME", "Bắt đầu lưu: " + name + " - Điểm: " + newScore);

        try {
            AppDatabase db = AppDatabase.getDatabase(context);
            UserDao userDao = db.userDao();

            // Kiểm tra user cũ
            User existingUser = userDao.getUserByName(name);

            if (existingUser == null) {
                android.util.Log.d("DEBUG_GAME", "User mới -> Insert");
                User newUser = new User();
                newUser.name = name;
                newUser.score = newScore;
                userDao.insertUser(newUser);
            } else {
                android.util.Log.d("DEBUG_GAME", "User cũ -> Update");
                if (newScore > existingUser.score) {
                    existingUser.score = newScore;
                    userDao.updateUser(existingUser);
                }
            }
            android.util.Log.d("DEBUG_GAME", "Lưu thành công!");

        } catch (Exception e) {
            // Nếu có lỗi đỏ lòm ở đây thì biết ngay nguyên nhân
            android.util.Log.e("DEBUG_GAME", "Lỗi lưu database: " + e.getMessage());
            e.printStackTrace();
        }

        if (getView() != null) {
            Navigation.findNavController(requireView()).popBackStack();
        }
    }

}