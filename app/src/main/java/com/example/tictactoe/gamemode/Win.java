package com.example.tictactoe.gamemode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.R;
import com.example.tictactoe.model.User;
import com.google.gson.Gson;

public class Win extends AppCompatActivity {
    private ImageView winnerPic;

    private Button playAgainBtn, homebtn;
    private TextView winnerName;
    private User winnerPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        playAgainBtn = findViewById(R.id.playAgainButton);
        homebtn = findViewById(R.id.goHomeBtnWin);
        playAgainBtn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), GameOffline.class)));
        homebtn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), GameModeLobby.class)));


        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences("winner", MODE_PRIVATE);
        String winner = sharedPreferences.getString("winner1", "");
        winnerPlayer = gson.fromJson(winner, User.class);

        winnerPic = findViewById(R.id.winnerSkin);
        winnerPic.setImageResource(winnerPlayer.getProfilePicResource());
        winnerName = findViewById(R.id.winnerTV);
        winnerName.setText(winnerPlayer.getUsername()
                                       .toUpperCase() + " WIN!");

    }

}