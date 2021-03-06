package com.denhihuynh.connectfour;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import constants.SharedPreferenceConstants;

public class GameSetupActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String PLAYERNAMES = "playerNames";
    private EditText playerOneName, playerTwoName;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);
        prefs = getSharedPreferences(
                "com.denhihuynh.connectfour", Context.MODE_PRIVATE);
        Button startButton = (Button) findViewById(R.id.startButton);
        playerOneName = (EditText) findViewById(R.id.edittext_firstplayer);
        playerTwoName = (EditText) findViewById(R.id.edittext_secondplayer);
        startButton.setOnClickListener(this);
    }

    /**
     * Checks if input given is correct. If it is then a new game is started.
     * @param v the view which was pressed.
     */
    @Override
    public void onClick(View v) {

        String firstPlayer = String.valueOf(playerOneName.getText());
        String secondPlayer = String.valueOf(playerTwoName.getText());
        if (firstPlayer.equals("") || secondPlayer.equals("")) {
            Toast.makeText(getApplicationContext(), "Players must have names between 1-10 characters",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (firstPlayer.equals(secondPlayer)) {
            Toast.makeText(getApplicationContext(), "Players can not have the same name",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        ArrayList<String> playerNames = new ArrayList<>();
        playerNames.add(firstPlayer);
        playerNames.add(secondPlayer);
        prefs.edit().putBoolean(SharedPreferenceConstants.ONGOINGGAMEEXISTS, false).apply();
        Bundle extras = new Bundle();
        extras.putStringArrayList(PLAYERNAMES, playerNames);
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    /**
     * Backpress takes the user back to Mainactivity.
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
