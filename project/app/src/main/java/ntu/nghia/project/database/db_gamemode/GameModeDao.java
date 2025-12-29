package ntu.nghia.project.database.db_gamemode;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface GameModeDao {
    @Insert
    void insertMode(GameMode mode);

    @Query("SELECT * FROM game_modes")
    List<GameMode> getAllModes();
}