package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnOnline, btnOffline, btnSignIn, btnLogIn;
    private ImageButton btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.SignInBtn);
        btnLogIn = findViewById(R.id.logInBtn);


        btnSignIn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), CrearSesio.class)));
        btnLogIn.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), LogInForm.class)));

    }
}