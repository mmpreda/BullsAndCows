package ro.devacademy.mada.bullsandcows;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;
import net.alexandroid.shpref.ShPref;
import java.util.ArrayList;

/**
 *  The Hall of Fame (list) should be sorted descending!!
 */

public class HallOfFameActivity extends AppCompatActivity {

    private ListView mListView;
    private CustomAdapter adapter;
    private Button button_back_hof;

    private ArrayList<Player> winnersList = new ArrayList<>();
    private ArrayList<String> winnersListInShPrefs = new ArrayList<>();

    Gson gson =new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall_of_fame);

        button_back_hof = (Button)findViewById(R.id.button_back_hof);
        button_back_hof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HallOfFameActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        mListView = (ListView)findViewById(R.id.list_view);

        retrieveListOfWinnersFromShPrefs();
        adapter = new CustomAdapter(this, winnersList);
        mListView.setAdapter(adapter);


        // This can be deleted, you don't have this in a real-life game
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"You clicked on the list",Toast.LENGTH_LONG).show();
            }
        });*/
    }

    private void retrieveListOfWinnersFromShPrefs(){

        winnersListInShPrefs = ShPref.getListOfStrings("myListKey");

        for (String entry : winnersListInShPrefs){
            Player winner = gson.fromJson(entry, Player.class);
            winnersList.add(winner);
        }

    }
}
