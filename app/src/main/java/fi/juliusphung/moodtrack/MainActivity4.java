package fi.juliusphung.moodtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.OnNavigationButtonClickedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.juliusphung.moodtrack.adapter.MoodAdapter;
import fi.juliusphung.moodtrack.dataBase.Mood;
import fi.juliusphung.moodtrack.dataBase.MoodDB;

/**
 * Class MainActivity4 describes the main navigation activity of the Android application.
 *
 * @author Laurentiu Sebastian Hategan,
 */
public class MainActivity4 extends AppCompatActivity implements OnNavigationButtonClickedListener {
    private static final String TAG = "CalendarView";

    /**
     * This function connects the AddNew button to main activity
     *
     * @author
     */
    private final View.OnClickListener clickAddNew = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity4.this, MainActivity.class);
            startActivity(intent);
        }
    };

    /**
     * This function connects the ClickEntries button to main activity 5
     */
    private final View.OnClickListener clickEntries = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
            startActivity(intent);
        }
    };

    /**
     * This function connects the ClickSummary button to main activity 6
     */
    private final View.OnClickListener clickSummary = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity4.this, MainActivity6.class);
            startActivity(intent);
        }
    };

    private MoodAdapter moodAdapter;
    private List<Mood> mListMood;
    private int tvSad;
    private int tvMeh;
    private int tvOkay;
    private int tvHappy;
    private int tvExcited;

    /**
     * This function gets data from the database and injects it into textView.
     *
     * @author
     */
    protected void getData() {

        TextView textViewSad = findViewById(R.id.tvSad);
        TextView textViewMeh = findViewById(R.id.tvMeh);
        TextView textViewOkay = findViewById(R.id.tvOkay);
        TextView textViewHappy = findViewById(R.id.tvHappy);
        TextView textViewExcited = findViewById(R.id.tvExcited);

        moodAdapter = new MoodAdapter();
        mListMood = new ArrayList<>();
        moodAdapter.setData(mListMood);
        tvSad = MoodDB.get(this).moodDao().getCountLevel(0);
        textViewSad.setText(Integer.toString(tvSad));

        tvMeh = MoodDB.get(this).moodDao().getCountLevel(1);
        textViewMeh.setText(Integer.toString(tvMeh));

        tvOkay = MoodDB.get(this).moodDao().getCountLevel(2);
        textViewOkay.setText(Integer.toString(tvOkay));

        tvHappy = MoodDB.get(this).moodDao().getCountLevel(3);
        textViewHappy.setText(Integer.toString(tvHappy));

        tvExcited = MoodDB.get(this).moodDao().getCountLevel(4);
        textViewExcited.setText(Integer.toString(tvExcited));
    }

    /**
     * Initialise the activity
     *
     * @param savedInstanceState Saves state when reloading app
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //Assigns variable
        final CustomCalendar customCalendar = findViewById(R.id.custom_calendar);

        //Initialise description of the new HashMap
        HashMap<Object, Property> mapDescToProp = new HashMap<>();

        //Initialises default property, resource and assigns variable
        Property propDefault = new Property();
        propDefault.layoutResource = R.layout.default_view;
        propDefault.dateTextViewResource = R.id.default_datetextview;
        mapDescToProp.put("default", propDefault);

        Property propSad = new Property();
        propSad.layoutResource = R.layout.sad_view;
        propSad.dateTextViewResource = R.id.sad_datetextview;
        mapDescToProp.put("sad", propSad);

        Property propMeh = new Property();
        propMeh.layoutResource = R.layout.meh_view;
        propMeh.dateTextViewResource = R.id.meh_datetextview;
        mapDescToProp.put("meh", propMeh);

        Property propOkay = new Property();
        propOkay.layoutResource = R.layout.okay_view;
        propOkay.dateTextViewResource = R.id.okay_datetextview;
        mapDescToProp.put("okay", propOkay);

        Property propHappy = new Property();
        propHappy.layoutResource = R.layout.happy_view;
        propHappy.dateTextViewResource = R.id.happy_datetextview;
        mapDescToProp.put("happy", propHappy);

        Property propExcited = new Property();
        propExcited.layoutResource = R.layout.excited_view;
        propExcited.dateTextViewResource = R.id.excited_datetextview;
        mapDescToProp.put("excited", propExcited);


        //This function sets the properties to the calendar view
        customCalendar.setMapDescToProp(mapDescToProp);

        HashMap<Integer, Object> mapDateToDesc = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (Mood item : getForMonth(LocalDate.now())) {
            mapDateToDesc.put(item.getDate().getDayOfMonth(), getMood(item.getLevel()));
        }

        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.NEXT, this);
        customCalendar.setOnNavigationButtonClickedListener(CustomCalendar.PREVIOUS, this);
        customCalendar.setDate(calendar, mapDateToDesc);


        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                Snackbar.make(customCalendar, selectedDate.get(Calendar.DAY_OF_MONTH) + " selected", Snackbar.LENGTH_LONG).show();
            }
        });

        //Function sets OnclickListener to Plus, Summary and Entry buttons.
        ImageButton plus = findViewById(R.id.plusButton);
        plus.setOnClickListener(clickAddNew);

        ImageButton summary = findViewById(R.id.summaryButton);
        summary.setOnClickListener(clickSummary);

        ImageButton entry = findViewById(R.id.entriesButton);
        entry.setOnClickListener(clickEntries);

        getData();
    }

    /**
     * Prints the color of the mood
     *
     * @param level represents the mood level 0-4
     * @return colour based on mood to calendar
     */
    public String getMood(int level) {
        switch (level) {
            case 0:
                return "sad";
            case 1:
                return "meh";
            case 2:
                return "okay";
            case 3:
                return "happy";
            case 4:
                return "excited";
            default:
                return "";
        }
    }

    /**
     * Function works with database on getting Month days
     *
     * @param date is the local date
     * @return current Month
     */
    public List<Mood> getForMonth(LocalDate date) {

        LocalDate startMonth = date.withDayOfMonth(1);
        LocalDate endMonth = date.withDayOfMonth(date.lengthOfMonth());
        List<Mood> thisMonth = MoodDB.get(this).moodDao().getByMonth(startMonth, endMonth);
        Log.d(TAG, "calendarView is working?" + thisMonth);
        return thisMonth;
    }

    /**
     * Function puts the coresponding mood color on the current date on the calendar
     *
     * @param whichButton searches the current date button
     * @param newMonth    searches the current Month
     * @return
     */
    public Map<Integer, Object>[] onNavigationButtonClicked(int whichButton, Calendar newMonth) {

        Map<Integer, Object>[] arr = new Map[2];
        List<Mood> thisMonth = getForMonth(LocalDate.of(newMonth.get(Calendar.YEAR), newMonth.get(Calendar.MONTH) + 1, 1));
        arr[0] = new HashMap<>();
        for (Mood item : thisMonth) {
            arr[0].put(item.getDate().getDayOfMonth(), getMood(item.getLevel()));
        }
        return arr;
    }
}