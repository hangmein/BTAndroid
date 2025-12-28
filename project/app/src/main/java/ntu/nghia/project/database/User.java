package ntu.nghia.project.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public int score;

    public User() {
    }

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }
}