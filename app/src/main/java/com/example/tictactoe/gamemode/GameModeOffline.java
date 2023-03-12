package com.example.tictactoe.gamemode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.R;
import com.example.tictactoe.model.User;
import com.google.gson.Gson;

public class GameModeOffline extends AppCompatActivity {

    private Button startGame;
    private ImageButton backarrow;
    private EditText player1, player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_offline);

        player1 = findViewById(R.id.player1FormUser);
        player2 = findViewById(R.id.player2FormUser);

        startGame = findViewById(R.id.startOfflineGame1v1);
        startGame.setOnClickListener(v -> checkPlayerName());

        backarrow = findViewById(R.id.back_arrow_offlineMode);
        backarrow.setOnClickListener(v -> startActivity(new Intent(this, GameModeLobby.class)));
    }

    private void checkPlayerName() {
        String user1 = player1.getText()
                              .toString();
        String user2 = player2.getText()
                              .toString();
        if (!user1.isEmpty() && !user2.isEmpty()) {
            Intent intent = new Intent(this, GameOffline.class);
            SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            User playerName1 = new User(user1, R.drawable.narutopic);
            User playerName2 = new User(user2, R.drawable.gokupic);
            Gson gson = new Gson();
            String player1ToString = gson.toJson(playerName1);
            String player2ToString = gson.toJson(playerName2);
            prefsEditor.putString("user1", player1ToString);
            prefsEditor.putString("user2", player2ToString);
            prefsEditor.apply();
            startActivity(intent);
        }

    }
}
