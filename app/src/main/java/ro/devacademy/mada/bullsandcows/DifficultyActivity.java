package ro.devacademy.mada.bullsandcows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DifficultyActivity extends AppCompatActivity {

    private Button buttonEasy;
    private Button buttonMedium;
    private Button buttonHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        buttonEasy = (Button)findViewById(R.id.button_easy);
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, SecondActivity.class);
                intent.putExtra("Difficulty", 1);
                startActivity(intent);
            }
        });

        buttonMedium = (Button)findViewById(R.id.button_medium);
        buttonMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, SecondActivity.class);
                intent.putExtra("Difficulty", 2);
                startActivity(intent);
            }
        });

        buttonHard = (Button)findViewById(R.id.button_hard);
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficultyActivity.this, SecondActivity.class);
                intent.putExtra("Difficulty", 3);
                startActivity(intent);
            }
        });
    }
}
