package fi.juliusphung.moodtrack;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;

import fi.juliusphung.moodtrack.dataBase.Mood;
import fi.juliusphung.moodtrack.dataBase.MoodDB;


/**
 * This MainActivity describes user's input confirmation and save it into datebase
 *
 * @author Julius with some help from Patrick
 */
public class MainActivity3 extends AppCompatActivity {
    EditText userComment;
    Bundle b;
    int mood;

    /**
     * This shows again user's choices and comments
     *
     * @param savedInstanceState
     * @return list of checked options and user's input
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //Confirm user's input mood emoji and comment
        TextView print = findViewById(R.id.textView2);
        b = getIntent().getExtras();
        mood = b.getInt(MainActivity.MOOD, 0);
        ImageView emoji = findViewById(R.id.imageView);
        userComment = findViewById(R.id.editTextComment);
        switch (mood) {
            case 0:
                emoji.setImageResource(R.drawable.sad);
                print.setText("You are feeling sad !");
                print.setTextColor(getResources().getColor(R.color.sad));
                break;
            case 1:
                emoji.setImageResource(R.drawable.meh);
                print.setText("You are feeling meh !");
                print.setTextColor(getResources().getColor(R.color.meh));
                break;
            case 2:
                emoji.setImageResource(R.drawable.okay);
                print.setText("You are feeling okay !");
                print.setTextColor(getResources().getColor(R.color.okay));
                break;
            case 3:
                emoji.setImageResource(R.drawable.happy);
                print.setText("You are feeling happy !");
                print.setTextColor(getResources().getColor(R.color.happy));
                break;
            case 4:
                emoji.setImageResource(R.drawable.excited);
                print.setText("You are feeling excited !");
                print.setTextColor(getResources().getColor(R.color.excited));
                break;
        }

        /**
         * Get intent data and pass it to the next activity : comment, list of chosen options and selected mood
         * @return Intent data and pass it to next activity
         */
        // Get intented information from Activity 3
        // User's comment:
        Intent intent = getIntent();
        String comment = intent.getStringExtra(MainActivity2.COMMENT);
        TextView tx = findViewById(R.id.printComment);
        tx.setText(comment);
        tx.setMovementMethod(new ScrollingMovementMethod());


        // Checked list of factors
        ArrayList factors = intent.getStringArrayListExtra(MainActivity2.LIST_SELECTED);
        TextView txv = findViewById(R.id.selectedFactors);
        txv.setText((String.valueOf(factors)).replaceAll(",", "\n" + "  ○ ").replace("[", "  ○ ").replace("]", ""));

        // Call MoodDB to make "create" method
        // Also return to Activity 4 after saving input data
        ImageButton clickSave = findViewById(R.id.saveButton);
        clickSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SAVE BUTTON", "YAS! IT WORKS! ");
                Toast.makeText(MainActivity3.this, "Your input data is saved", Toast.LENGTH_LONG).show();
                saveToDataBase();
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }

    /**
     * This method saves user's input into database
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void saveToDataBase() {
        MoodDB moodDB = MoodDB.get(this);
        Intent intent = getIntent();
        String comment = intent.getStringExtra(MainActivity2.COMMENT);
        ArrayList factors = intent.getStringArrayListExtra(MainActivity2.LIST_SELECTED);
        // Add new mood. Get Data from user's input of Mood emoji and comment
        Mood newMood = new Mood(mood, LocalDate.now(), (String.valueOf(factors)).replaceAll(",", "\n" + "  ○ ").replace("[", "  ○ ").replace("]", ""), comment);
        // Insert data to Database (moodDb --> moodDao --> run "create" function)
        long addNew = moodDB.moodDao().create(newMood);
        Log.d("CHECK_DATABASE", "ALL data user have put and saved: " + moodDB.moodDao().getAll());

    }
}