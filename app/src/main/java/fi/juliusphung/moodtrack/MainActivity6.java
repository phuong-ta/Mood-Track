package fi.juliusphung.moodtrack;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import fi.juliusphung.moodtrack.dataBase.MoodDB;

/**
 * This MainActivity describes summary of what user input and gives suggestion.
 *
 * @author Julius
 */

public class MainActivity6 extends AppCompatActivity {
    int sadDays;
    int happyDays;
    int excitedDays;
    TextView textView;
    TextView suggestion;

    /**
     * This shows user's total input
     * and based on the input data would give corresponding suggestion
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        sadDays = MoodDB.get(this).moodDao().getCountLevel(0);
        happyDays = MoodDB.get(this).moodDao().getCountLevel(3);
        excitedDays = MoodDB.get(this).moodDao().getCountLevel(4);
        textView = findViewById(R.id.summaryInfo);
        suggestion = findViewById(R.id.suggestion);

        textView.setText("Total days: " + MoodDB.get(this).moodDao().getAll().size() + " days"
                + "\n" + "Total sad days: " + sadDays + " days"
                + "\n" + "Total happy days: " + (happyDays + excitedDays) + " days");

        if (sadDays <= (happyDays + excitedDays)) {
            happyWish();
        } else if (sadDays > (happyDays + excitedDays)) {
            sadCheerUp();
        }
    }

    /**
     * This method gives corresponding suggestion based on user's information
     *
     * @return suggestion
     */
    private void happyWish() {
        switch ((1 <= happyDays && happyDays <= 5) ? 0 :
                (6 <= happyDays && happyDays <= 14) ? 1 : 2) {
            case 0:
                suggestion.setText("It is delighted to hear that you have such many happy days!");
            case 1:
                suggestion.setText("It is wonderful to see that you have such many happy days. Keep it up!");
            case 2:
                suggestion.setText("It is time to share your happiness with people around you!");
        }
    }

    /**
     * This method gives corresponding suggestion based on user's information
     *
     * @return suggestion
     */
    private void sadCheerUp() {
        switch ((1 <= sadDays && sadDays <= 5) ? 0 :
                (6 <= sadDays && sadDays <= 14) ? 1 : 2) {
            case 0:
                suggestion.setText("Sorry to hear that you have had sad time! Take some time to relax and refresh your mind!");
                break;
            case 1:
                suggestion.setText("Sorry to hear that you have had sad time! Maybe it's time to relax by participating some outdoor activities with friends and get back to solve the matter later! ");
                break;
            case 2:
                suggestion.setText("Sorry to hear that you have had sad time!. Maybe it's time to share your matters, worries with your local Mental Health clinic! ");
                break;
        }
    }
}
