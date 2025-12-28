package ntu.nghia.project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM questions WHERE mode_id = :modeId")
    List<Question> getQuestionsByMode(int modeId);
    @Insert
    void insertAll(Question... questions);
    @Query("SELECT COUNT(*) FROM questions")
    int countQuestions();
}