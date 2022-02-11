package fi.juliusphung.moodtrack.dataBase;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDate;

/**
 * @author Phuong Ta
 */
public class Converters {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public static LocalDate fromString(String string) {
        return null == string ? null : LocalDate.parse(string);
    }

    @TypeConverter
    public static String localDateToString(LocalDate localDate) {
        return null == localDate ? null : localDate.toString();
    }
}