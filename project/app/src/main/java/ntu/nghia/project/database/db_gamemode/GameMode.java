package ntu.nghia.project.database.db_gamemode;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "game_modes")
public class GameMode {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;

    public GameMode(String name, String description) {
        this.name = name;
        this.description = description;
    }
}