package ntu.nghia.project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);
    @Query("SELECT * FROM user_table ORDER BY score DESC")
    List<User> getAllUsers();
    @Query("SELECT * FROM user_table WHERE name = :searchName LIMIT 1")
    User getUserByName(String searchName);
}