package fi.juliusphung.moodtrack.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * @author Phuong Ta
 */
@TypeConverters(Converters.class)
@Database(entities = {Mood.class}, version = 2)
public abstract class MoodDB extends RoomDatabase {

    // Add new column to table, and update table to version 2
    static Migration migrationFrom1To2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Add "factor" Column to table mood
            database.execSQL("ALTER TABLE mood ADD COLUMN factor TEXT");
        }
    };
    private static MoodDB moodDB;

    public static MoodDB get(Context context) {
        if (null == moodDB) {
            moodDB = Room.databaseBuilder(context.getApplicationContext(),
                    MoodDB.class, "mood_db").allowMainThreadQueries()
                    .addMigrations(migrationFrom1To2)
                    .build();
        }
        return moodDB;
    }

    public abstract MoodDAO moodDao();
}
