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

import java.util.ArrayList; // Cần thêm cái này
import java.util.Collections;
import java.util.List;

import ntu.nghia.project.R;
import ntu.nghia.project.database.AppDatabase;
import ntu.nghia.project.database.db_question.Question;
import ntu.nghia.project.database.db_question.QuestionDao;
import ntu.nghia.project.database.db_user.User;
import ntu.nghia.project.database.db_user.UserDao;

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
    private String currentCorrectContent = "";

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
        tvQuestionCount = view.findViewById(R.id.tvQuestionCount);
        tvScore = view.findViewById(R.id.tvScore);
        tvTimer = view.findViewById(R.id.tvTimer);
        tvQuestionContent = view.findViewById(R.id.tvQuestionContent);
        btnOptionA = view.findViewById(R.id.btnOptionA);
        btnOptionB = view.findViewById(R.id.btnOptionB);
        btnOptionC = view.findViewById(R.id.btnOptionC);
        btnOptionD = view.findViewById(R.id.btnOptionD);

        setupLifelines();
        AppDatabase db = AppDatabase.getDatabase(requireContext());
        QuestionDao dao = db.questionDao();

        int modeIdCanLay = 1;
        if (getArguments() != null) {
            modeIdCanLay = getArguments().getInt("selected_mode_id", 1);
        }
        listQuestions = dao.getQuestionsByMode(modeIdCanLay);

        if (listQuestions != null && listQuestions.size() > 0) {
            Collections.shuffle(listQuestions);
            showQuestion();
        } else {
            Toast.makeText(getContext(), "Đang tải dữ liệu...", Toast.LENGTH_SHORT).show();
            Navigation.findNavController(view).popBackStack();
        }
        btnOptionA.setOnClickListener(v -> checkAnswer(btnOptionA));
        btnOptionB.setOnClickListener(v -> checkAnswer(btnOptionB));
        btnOptionC.setOnClickListener(v -> checkAnswer(btnOptionC));
        btnOptionD.setOnClickListener(v -> checkAnswer(btnOptionD));
    }
    private void setupLifelines() {
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
    }
    private void showQuestion() {
        resetButtonColors();
        Question q = listQuestions.get(currentQuestionIndex);

        tvQuestionContent.setText(q.content);

        if (q.correctAnswer.equals("A")) currentCorrectContent = q.optionA;
        else if (q.correctAnswer.equals("B")) currentCorrectContent = q.optionB;
        else if (q.correctAnswer.equals("C")) currentCorrectContent = q.optionC;
        else currentCorrectContent = q.optionD;

        List<String> options = new ArrayList<>();
        options.add(q.optionA);
        options.add(q.optionB);
        options.add(q.optionC);
        options.add(q.optionD);
        Collections.shuffle(options);
        btnOptionA.setText("A. " + options.get(0));
        btnOptionB.setText("B. " + options.get(1));
        btnOptionC.setText("C. " + options.get(2));
        btnOptionD.setText("D. " + options.get(3));

        tvQuestionCount.setText("Question: " + (currentQuestionIndex + 1) + "/" + listQuestions.size());
        tvScore.setText("Score: " + score);

        startTimer();
    }
    private void checkAnswer(Button clickedButton) {
        if (countDownTimer != null) countDownTimer.cancel();
        setButtonsEnabled(false);
        String buttonText = clickedButton.getText().toString();
        if (buttonText.contains(currentCorrectContent)) {
            clickedButton.setBackgroundColor(Color.parseColor("#4CAF50"));
            score += 100;
            tvScore.setText("Score: " + score);
        } else {
            clickedButton.setBackgroundColor(Color.parseColor("#F44336"));
            highlightCorrectAnswer();
        }
        new Handler().postDelayed(this::nextQuestion, 1000);
    }
    private void highlightCorrectAnswer() {
        if (btnOptionA.getText().toString().contains(currentCorrectContent)) {
            btnOptionA.setBackgroundColor(Color.parseColor("#4CAF50"));
        }
        else if (btnOptionB.getText().toString().contains(currentCorrectContent)) {
            btnOptionB.setBackgroundColor(Color.parseColor("#4CAF50"));
        }
        else if (btnOptionC.getText().toString().contains(currentCorrectContent)) {
            btnOptionC.setBackgroundColor(Color.parseColor("#4CAF50"));
        }
        else if (btnOptionD.getText().toString().contains(currentCorrectContent)) {
            btnOptionD.setBackgroundColor(Color.parseColor("#4CAF50"));
        }
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
    private void endGame() {
        Toast.makeText(getContext(), "Kết thúc! Tổng điểm: " + score, Toast.LENGTH_LONG).show();
        showCustomGameOverDialog();
    }
    private void showCustomGameOverDialog() {
        if (getContext() == null) return;
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View dialogView = inflater.inflate(R.layout.dialog_game_over, null);

        TextView tvFinalScoreDialog = dialogView.findViewById(R.id.tvFinalScoreDialog);
        android.widget.EditText etPlayerName = dialogView.findViewById(R.id.etPlayerName);
        Button btnSave = dialogView.findViewById(R.id.btnSave);
        Button btnDiscard = dialogView.findViewById(R.id.btnDiscard);
        tvFinalScoreDialog.setText(String.valueOf(score));

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(requireContext());
        builder.setView(dialogView);
        builder.setCancelable(false);

        final android.app.AlertDialog dialog = builder.create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        btnSave.setOnClickListener(v -> {
            String playerName = etPlayerName.getText().toString().trim();
            if (playerName.isEmpty()) playerName = "Unknown Student";
            saveScoreToDatabase(playerName, score);
            dialog.dismiss();
        });

        btnDiscard.setOnClickListener(v -> {
            if (getView() != null) Navigation.findNavController(requireView()).popBackStack();
            dialog.dismiss();
        });

        dialog.show();
    }
    private void saveScoreToDatabase(String name, int newScore) {
        Context context = getContext();
        if (context == null) return;
        try {
            AppDatabase db = AppDatabase.getDatabase(context);
            UserDao userDao = db.userDao();
            User existingUser = userDao.getUserByName(name);
            if (existingUser == null) {
                User newUser = new User();
                newUser.name = name;
                newUser.score = newScore;
                userDao.insertUser(newUser);
            } else {
                if (newScore > existingUser.score) {
                    existingUser.score = newScore;
                    userDao.updateUser(existingUser);
                }
            }
            Toast.makeText(context, "Đã lưu kỷ lục!", Toast.LENGTH_SHORT).show();
            if (getView() != null) Navigation.findNavController(requireView()).popBackStack();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi lưu điểm: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void handle5050() {
        List<Button> wrongButtons = new ArrayList<>();

        if (!btnOptionA.getText().toString().contains(currentCorrectContent)) wrongButtons.add(btnOptionA);
        if (!btnOptionB.getText().toString().contains(currentCorrectContent)) wrongButtons.add(btnOptionB);
        if (!btnOptionC.getText().toString().contains(currentCorrectContent)) wrongButtons.add(btnOptionC);
        if (!btnOptionD.getText().toString().contains(currentCorrectContent)) wrongButtons.add(btnOptionD);

        Collections.shuffle(wrongButtons);
        if (wrongButtons.size() >= 2) {
            wrongButtons.get(0).setVisibility(View.INVISIBLE);
            wrongButtons.get(1).setVisibility(View.INVISIBLE);
        }
        Toast.makeText(getContext(), "Đã loại bỏ 2 phương án sai!", Toast.LENGTH_SHORT).show();
    }
    private void handleDocs() {
        String message = "Đang phân tích dữ liệu...\n";
        message += "Đáp án là: '" + currentCorrectContent + "'";

        new android.app.AlertDialog.Builder(requireContext())
                .setTitle("Trợ giúp Tài liệu")
                .setMessage(message)
                .setPositiveButton("ĐÓNG", null)
                .show();
    }
    private void handleSwitch() {
        if (countDownTimer != null) countDownTimer.cancel();
        Toast.makeText(getContext(), "Đổi câu hỏi...", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (countDownTimer != null) countDownTimer.cancel();
    }
}