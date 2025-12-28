package ntu.nghia.project.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// Đảm bảo entity là User.class
@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "user.db";
    private static UserDatabase instance;
    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}