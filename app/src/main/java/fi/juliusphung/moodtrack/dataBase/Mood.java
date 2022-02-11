package fi.juliusphung.moodtrack.dataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

/**
 * @author Phuong Ta
 */
@Entity
public class Mood {
    //
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "day")
    public LocalDate day;
    @ColumnInfo(name = "comment")
    public String comment;
    @ColumnInfo(name = "factor")
    public String factor;
    @ColumnInfo(name = "level")
    private int level;

    public Mood(int level, LocalDate day, String factor, String comment) {
        this.level = level;
        this.day = day;
        this.factor = factor;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDate getDate() {
        return day;
    }

    public void setDate(LocalDate day) {
        this.day = day;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFactor() {
        return factor;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "id=" + id +
                ", level=" + level +
                ", day=" + day +
                ", comment='" + comment + '\'' +
                '}';
    }
}