package gk1.huynhducnghia;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class Bonus extends AppCompatActivity {

    TextView tvQuestion, tvScore, tvTimer;
    Button btnChoice1, btnChoice2, btnChoice3;

    int dapAnDung;
    int score = 0;
    CountDownTimer timer;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_bonus);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        btnChoice1 = findViewById(R.id.btnChoice1);
        btnChoice2 = findViewById(R.id.btnChoice2);
        btnChoice3 = findViewById(R.id.btnChoice3);

        startNewRound();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void startNewRound() {
        if (timer != null) timer.cancel();
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        char op;
        int result = 0;

        switch (random.nextInt(3)) {
            case 0: op = '+'; result = a + b; break;
            case 1: op = '-'; result = a - b; break;
            default: op = '×'; result = a * b; break;
        }

        dapAnDung = result;
        tvQuestion.setText(a + " " + op + " " + b + " = ?");
        int correctIndex = random.nextInt(3);
        int[] answers = new int[3];
        for (int i = 0; i < 3; i++) {
            if (i == correctIndex)
                answers[i] = result;
            else
                answers[i] = result + random.nextInt(5) - 2;
        }

        btnChoice1.setText(String.valueOf(answers[0]));
        btnChoice2.setText(String.valueOf(answers[1]));
        btnChoice3.setText(String.valueOf(answers[2]));

        View.OnClickListener answerClick = v -> {
            Button bttn = (Button) v;
            int selected = Integer.parseInt(bttn.getText().toString());

            if (selected == dapAnDung) {
                score++;
                tvScore.setText("Score: " + score);
            } else {
                score = 0;
                tvScore.setText("Score: 0");
            }

            startNewRound();
        };

        btnChoice1.setOnClickListener(answerClick);
        btnChoice2.setOnClickListener(answerClick);
        btnChoice3.setOnClickListener(answerClick);
        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                tvTimer.setText("Time: 0s");
                score = 0;
                tvScore.setText("Score: 0");
                startNewRound();
            }
        }.start();
    }
}