package ro.devacademy.mada.bullsandcows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    private Button buttonHowToPlay ;
    private Button buttonHallOfFame ;
    private Button buttonPlay;

    /** TO DO
     *
     * A welcome message, something to fill up the space
     * Check how the activities look in landscape
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        buttonHowToPlay = (Button)findViewById(R.id.button_howtoplay);
        buttonHowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, InstructionsActivity.class);
                startActivity(intent);
            }
        });

        buttonPlay = (Button)findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, DifficultyActivity.class);
                startActivity(intent);
            }
        });


        buttonHallOfFame = (Button)findViewById(R.id.button_halloffame);
        buttonHallOfFame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, HallOfFameActivity.class);
                startActivity(intent);
            }
        });
    }

}
