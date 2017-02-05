package ro.devacademy.mada.bullsandcows;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    /** IMPROVEMENT IDEAS
     *
     *  Mechanism for score (consider timing the game and creating a rule for points
     *  keeping in mind the difficulty, lives left and time left)
     *
     *  Use string resources for the texts displayed (with params when needed)
     *
     *  Show solution Button

     *
     *  Hall of fame:
     *  - Write player name and score to SharedPrefs
     *
     *
     */

    private int numberOfLives = 9;
    private String RANDOM_NUMBER;
    int gameDifficulty = 0;
    int playerScore = 0;

    private Button buttonTry;
    private Button buttonPlayAgain;
    private EditText editText;
    private TextView textViewHints;
    private ScrollView scrollView;
    private TextView twForErrorMessage;
    private TextView twForLivesCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initializeViews();

        // Get difficulty from the intent passed on from the DifficultyActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            gameDifficulty = extras.getInt("Difficulty");
            RANDOM_NUMBER = Integer.toString(generateRandom(gameDifficulty+1));

            //Adapt hint depending on game difficulty
            String text = String.format(getResources().getString(R.string.hint_number),gameDifficulty+1);
            editText.setHint(text);
        }

        //Print nb of lives left
        String text = String.format(getResources().getString(R.string.tv_lives_left),numberOfLives);
        twForLivesCount.setText(text);

        buttonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String response = editText.getText().toString();
                    //Log.i("Madalina", "Player has " + numberOfLives + " more lives");
                    if (numberOfLives>0)
                    {
                        giveHints(response, gameDifficulty+1, numberOfLives);   //using nbOfLives just to calculate score
                        numberOfLives--;

                        //Recalculate and print nb of lives left
                        String text = String.format(getResources().getString(R.string.tv_lives_left),numberOfLives);
                        twForLivesCount.setText(text);

                    }
                    else {
                        textViewHints.append("You lost :( ");
                        scrollToBottom();
                        buttonTry.setClickable(false);
                    }

                }
        });

        buttonPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initializeViews(){
        buttonTry = (Button)findViewById(R.id.button_try);
        buttonPlayAgain = (Button)findViewById(R.id.button_play_again);
        editText = (EditText)findViewById(R.id.editText_numberInserted);
        textViewHints = (TextView) findViewById(R.id.textView_hints);
             textViewHints.setMovementMethod(new ScrollingMovementMethod());
        scrollView = (ScrollView) findViewById(R.id.scroller_id);
        twForErrorMessage = (TextView)findViewById(R.id.textView_forErrMsg);
        twForLivesCount = (TextView)findViewById(R.id.textView_lives_countdown);
    }

    private String checkGuess(String secret, String guess) {
        int countBull=0;
        int countCow=0;

        HashMap<Character, Integer> map = new HashMap<>();

        //check bull
        for(int i=0; i<secret.length(); i++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);

            if(c1==c2){
                countBull++;
            }else{
                if(map.containsKey(c1)){
                    int freq = map.get(c1);
                    freq++;
                    map.put(c1, freq);
                }else{
                    map.put(c1, 1);
                }
            }
        }

        //check cow
        for(int i=0; i<secret.length(); i++){
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);

            if(c1!=c2){
                if(map.containsKey(c2)){
                    countCow++;
                    if(map.get(c2)==1){
                        map.remove(c2);
                    }else{
                        int freq = map.get(c2);
                        freq--;
                        map.put(c2, freq);
                    }
                }
            }
        }

        return "You have: "+countBull+" bulls, "+countCow+" cows. Try again!\n";
    }

    private void scrollToBottom(){
        scrollView.post(new Runnable()
        {
            public void run()
            {
                scrollView.smoothScrollTo(0, textViewHints.getBottom());
            }
        });
    }

    private int generateRandom(int numberOfDigits){
        int number = 0;
        int max = 0, min = 0;

        switch(numberOfDigits){
            case 2 :
                min = 10;
                max = 99;
                break;
            case 3 :
                min = 100;
                max = 999;
                break;
            case 4 :
                min = 1000;
                max = 9999;
                break;
        }

        Random r = new Random();
        number = r.nextInt(max - min + 1) + min;
        Log.i("Madalina:", " random number generated is - " + (Integer.toString(number)));

        return number;
    }

    private static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();

        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void giveHints(String insertedNumber, int nbOfDigits, int nbOfLives){

        //Check for correct input
        if (insertedNumber.length()!=nbOfDigits) {
            twForErrorMessage.setText("Error: You must insert a " + nbOfDigits + "-digit number!");
        }else {
            //Correct input
            twForErrorMessage.setText("");  //make this invisible again

            //Case number guessed
            if (insertedNumber.compareTo(RANDOM_NUMBER) == 0) {
                hideKeyboard(SecondActivity.this);
                /*textViewHints.append("You guessed correctly!");
                scrollToBottom();
                buttonTry.setClickable(false);*/

                //Calculate score. It will only be printed in the WinnerActivity
                playerScore = calculateScore(nbOfDigits-1, nbOfLives);  //function of difficulty and lives left
                //Log.i("Madalina","Player score is: " + playerScore);

                Intent intent = new Intent (SecondActivity.this, WinnerActivity.class);
                intent.putExtra("Score",playerScore);
                startActivity(intent);
            }

            // Case number not guessed
            else {
                hideKeyboard(SecondActivity.this);
                String outcome = checkGuess(RANDOM_NUMBER, insertedNumber);
                textViewHints.append(insertedNumber + " - " + outcome);
                scrollToBottom();
                editText.setText("");
            }
        }
    }

    private int calculateScore(int gameDifficulty, int livesLeft) {
        int score = 0;

        switch(gameDifficulty){
            case 1:
                score = 100 * livesLeft;
                break;
            case 2:
                score = 200 * livesLeft;
                break;
            case 3:
                score = 300 * livesLeft;
                break;
        }

        return score;
    }
}
