package fi.juliusphung.moodtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This activity describes user to input factor options and comment
 *
 * @author Julius
 */

public class MainActivity2 extends AppCompatActivity {

    public final static String COMMENT = "COMMENT";
    public final static String LIST_SELECTED = "LIST_SELECTED";
    public final static String IMAGE = "EMOJI_IMAGE";
    private final static String TAG = "REASON AND COMMENT";

    ArrayList<String> selectedItems = new ArrayList();
    ImageView emoji;

    private int mood = 0;

    /**
     * This method passes the intented String type comment, checked factor options
     * and selected mood imagebutton from current activity pass to next activity
     *
     * @return passes intented data to next activity
     */
    private final View.OnClickListener click = v -> {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);

        //Pass user's comment to the next activity

        EditText comment = findViewById(R.id.editTextComment);
        String outputComment = comment.getText().toString();
        intent.putExtra(COMMENT, outputComment);

        //Pass user's input Mood emoji

        intent.putExtra(MainActivity.MOOD, mood);
        intent.putExtra(IMAGE, R.id.emoji);

        //Pass user's input factors

        intent.putStringArrayListExtra(LIST_SELECTED, selectedItems);

        startActivity(intent);
    };

    /**
     * This receives and shows intented data passed from previous activity
     *
     * @return listview of options that user can choose from by using listview Adapter
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Next Button and onCLickListener

        ImageButton btn = findViewById(R.id.nextButton);
        btn.setOnClickListener(click);

        //Get information from Activity 1 : Mood's emoji that user chosen

        Bundle b = getIntent().getExtras();
        mood = b.getInt(MainActivity.MOOD, 0);
        emoji = findViewById(R.id.emoji);
        switch (mood) {
            case 0:
                emoji.setImageResource(R.drawable.sad);
                break;
            case 1:
                emoji.setImageResource(R.drawable.meh);
                break;
            case 2:
                emoji.setImageResource(R.drawable.okay);
                break;
            case 3:
                emoji.setImageResource(R.drawable.happy);
                break;
            case 4:
                emoji.setImageResource(R.drawable.excited);
                break;
        }

        //Set ListView of Factor and add OnCheckedListener to the checkbox listview of Factors

        ListView lv = findViewById(R.id.factorListView);

        lv.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                getResources().getStringArray(R.array.arrayFactors)));

        lv.setOnItemClickListener((parent, view, position, id) -> {

            String factors[] = getResources().getStringArray(R.array.arrayFactors);
            Log.d(TAG, "item: " + position);
            Log.d(TAG, "What cause you this: " + factors[position]);
            if (selectedItems.contains(factors[position])) {
                selectedItems.remove(factors[position]);
            } else {
                selectedItems.add(factors[position]);
                Toast.makeText(MainActivity2.this, factors[position] + " is selected", Toast.LENGTH_SHORT).show();
            }
            Log.d("List of factors:", String.valueOf(selectedItems));
        });
    }
}