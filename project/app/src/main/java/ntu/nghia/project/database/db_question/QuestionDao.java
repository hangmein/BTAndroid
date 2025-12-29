package ntu.nghia.project.database.db_question;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface QuestionDao {
    @Query("SELECT * FROM questions WHERE mode_id = :modeId ORDER BY RANDOM() LIMIT 15")
    List<Question> getQuestionsByMode(int modeId);
    @Query("SELECT COUNT(*) FROM questions")
    int countQuestions();
    @Insert
    void insertAll(Question... questions);
    @Insert
    void insertQuestion(Question question);
}