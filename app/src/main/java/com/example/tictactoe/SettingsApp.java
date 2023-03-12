package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.gamemode.GameModeLobby;

public class SettingsApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_app);
        ImageButton arrowSettings = findViewById(R.id.arrowSettings);
        arrowSettings.setOnClickListener(btn -> startActivity(new Intent(SettingsApp.this, GameModeLobby.class)));
    }
}