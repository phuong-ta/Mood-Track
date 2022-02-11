package fi.juliusphung.moodtrack;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * This activity describes user to select corresponding mood to start mood input activity.
 *
 * @author Julius
 */

public class MainActivity extends AppCompatActivity {

    public final static String MOOD = "EXTRA_MOOD";
    /**
     * This method allows user to click one option
     *
     * @param mood Individual mood has its corresponding mood number
     * @return to method which can pass the intent and start next activity
     */

    private final View.OnClickListener click = new View.OnClickListener() {
        /**
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sad:
                    clickedEmoji(0);
                    break;
                case R.id.meh:
                    clickedEmoji(1);
                    break;
                case R.id.okay:
                    clickedEmoji(2);
                    break;
                case R.id.happy:
                    clickedEmoji(3);
                    break;
                case R.id.excited:
                    clickedEmoji(4);
                    break;
            }
        }

        /**
         * @param mood
         * @return pass the mood number and start next activity
         */
        private void clickedEmoji(int mood) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra(MOOD, mood);
            startActivity(intent);
        }
    };
    ImageButton sadButton;
    ImageButton mehButton;
    ImageButton okayButton;
    ImageButton happyButton;
    ImageButton excitedButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Date and set date info on Home screen
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.calendar);
        textViewDate.setText(currentDate);

        //Set OnclickListener
        (sadButton = findViewById(R.id.sad)).setOnClickListener(click);
        (mehButton = findViewById(R.id.meh)).setOnClickListener(click);
        (okayButton = findViewById(R.id.okay)).setOnClickListener(click);
        (happyButton = findViewById(R.id.happy)).setOnClickListener(click);
        (excitedButton = findViewById(R.id.excited)).setOnClickListener(click);
    }
}