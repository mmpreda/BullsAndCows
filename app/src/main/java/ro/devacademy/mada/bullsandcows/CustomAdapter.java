package ro.devacademy.mada.bullsandcows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mpreda on 21/11/2016.
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<Player> listOfPlayers;
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, ArrayList<Player> listOfPlayers) {
        this.listOfPlayers = listOfPlayers;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listOfPlayers.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfPlayers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.custom_list_item,parent,false);
            holder = new ViewHolder();  //fiind prima utilizare, initializam holderul
            holder.playerName = (TextView)convertView.findViewById(R.id.player_name_item);
            holder.playerScore = (TextView)convertView.findViewById(R.id.player_score_item);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Player player = (Player)getItem(position);

        holder.playerName.setText(player.getPlayerName());
        holder.playerScore.setText(""+player.getPlayerScore());     //convert the score to String

        return convertView;
    }

    class ViewHolder{
        TextView playerName;
        TextView playerScore;
    }
}
