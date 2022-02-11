package fi.juliusphung.moodtrack;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fi.juliusphung.moodtrack.adapter.MoodAdapter;
import fi.juliusphung.moodtrack.dataBase.Mood;
import fi.juliusphung.moodtrack.dataBase.MoodDB;

public class MainActivity5 extends AppCompatActivity {
    /**
     * This MainActivity describes ListView of user's input history
     *
     * @author PhuongTa
     */


    public static final String TAG = "Test DB:";
    private MoodAdapter moodAdapter;
    private List<Mood> mListMood;
    private Mood mood;

    /**
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        listAllMood();

    }

    /**
     * Call recyclerView, and pass data to Array
     * List all data, what user inputted from database.
     */

    protected void listAllMood() {
        RecyclerView recyclerView = findViewById(R.id.rev_listMood);
        moodAdapter = new MoodAdapter();
        mListMood = new ArrayList<>();
        mListMood = MoodDB.get(this).moodDao().getAll();
        moodAdapter.setData(mListMood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(moodAdapter);
    }

}