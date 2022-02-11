package fi.juliusphung.moodtrack.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Laurentiu Sebastian Hategan, Julius Phung, Phuong Ta
 */
@Dao
public interface MoodDAO {
    @Query("SELECT * FROM mood ORDER BY day DESC")
    List<Mood> getAll();

    @Query("SELECT * FROM mood WHERE id IN (:moodId)")
    List<Mood> getById(long moodId);

    @Query("SELECT * FROM mood ORDER BY day")
    List<Mood> getAllByDay();

    @Query("SELECT * FROM mood WHERE level IN (:moodLevel)")
    List<Mood> getByLevel(long moodLevel);

    @Query("SELECT COUNT(*) FROM mood Where level = :level")
    int getCountLevel(int level);

    @Query("SELECT * FROM mood WHERE day BETWEEN :start AND :end")
    List<Mood> getByMonth(LocalDate start, LocalDate end);

    @Query("DELETE FROM mood WHERE id = :id")
    void deleteById(long id);

    @Insert
    long create(Mood mood);

    @Delete
    void delete(Mood mood);
}
