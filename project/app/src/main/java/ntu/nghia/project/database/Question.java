package ntu.nghia.project.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "questions")
public class Question {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "option_a")
    public String optionA;

    @ColumnInfo(name = "option_b")
    public String optionB;

    @ColumnInfo(name = "option_c")
    public String optionC;

    @ColumnInfo(name = "option_d")
    public String optionD;

    @ColumnInfo(name = "correct_answer")
    public String correctAnswer;

    @ColumnInfo(name = "mode_id")
    public int modeId;
    public Question(String content, String optionA, String optionB, String optionC, String optionD, String correctAnswer, int modeId) {
        this.content = content;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
        this.modeId = modeId;
    }

}