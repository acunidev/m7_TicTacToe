package com.example.tictactoe.gamemode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.ProfileUser;
import com.example.tictactoe.R;
import com.example.tictactoe.SettingsApp;

public class GameModeLobby extends AppCompatActivity {
    private Button playOffline, playOnline;
    private ImageButton ajustes, perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_mode_lobby);

        playOnline = findViewById(R.id.playOnlinebtnGamemode);
        playOffline = findViewById(R.id.playOfflinebtnGamemode);
        ajustes = findViewById(R.id.ajustes);
        perfil = findViewById(R.id.goToProfile);

        Intent intent = getIntent();
        String name = intent.getStringExtra("userLogin");

        Intent intent1 = new Intent(this, GameOnline.class);
        intent1.putExtra("userLogin", name);

        perfil.setOnClickListener(v -> startActivity(new Intent(this, ProfileUser.class)));
        playOnline.setOnClickListener(v -> startActivity(intent1));
        playOffline.setOnClickListener(v -> startActivity(new Intent(this, GameModeOffline.class)));
        ajustes.setOnClickListener(v -> startActivity(new Intent(this, SettingsApp.class)));
    }
}