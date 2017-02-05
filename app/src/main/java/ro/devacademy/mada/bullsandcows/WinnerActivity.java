package ro.devacademy.mada.bullsandcows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import net.alexandroid.shpref.ShPref;

import java.util.ArrayList;

public class WinnerActivity extends AppCompatActivity {

    private Button buttonRegisterMe;
    private EditText editTextPlayerName;
    private TextView twForScoreDisplay;

    private String playerName;
    private int playerScore;

    Gson gson =new Gson();

    // list of winners as Json
    private ArrayList<String> winnersListInShPrefs = new ArrayList<>();


    /** TO DO
     *
     *  Back to Home (button)
     *  Maybe play a sound when Activity is launched?
     *  Maybe an animation for "You won!" ?
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        // Initialize views
        buttonRegisterMe = (Button)findViewById(R.id.button_registerMe);
        twForScoreDisplay = (TextView)findViewById(R.id.textView_score_display);

        // Get score from the intent passed on from the DifficultyActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            playerScore = extras.getInt("Score");

            //Display score
            String text = String.format(getResources().getString(R.string.tv_score),playerScore);
            twForScoreDisplay.setText(text);
        }

        // Register new winner and open Hall of Fame activity
        buttonRegisterMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get player name and build the player object
                editTextPlayerName = (EditText)findViewById(R.id.editText_playerName);
                playerName = editTextPlayerName.getText().toString();

                Player player = new Player(playerName, playerScore);

                String playerJson = gson.toJson(player);
                //Log.i("Madalina","Player as Json: " + playerJson);

                updateSharedPrefs(playerJson);

                // Start next activity
                Intent intent = new Intent(WinnerActivity.this, HallOfFameActivity.class);
                startActivity(intent);

            }
        });
    }

    private void updateSharedPrefs(String playerJson){
        // Get winners' list from Shared Prefs and then delete it from there
        winnersListInShPrefs = ShPref.getListOfStrings("myListKey");  //as Json
        ShPref.clear();

        // Add the new player to the winners list and rewrite it to SharedPrefs
        winnersListInShPrefs.add(playerJson);
        ShPref.putList("myListKey", winnersListInShPrefs);
    }
}
