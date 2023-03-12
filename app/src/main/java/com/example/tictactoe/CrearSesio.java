package com.example.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.gamemode.GameModeLobby;

public class CrearSesio extends AppCompatActivity {

    private Button signInForm;
    private EditText username, password, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_sesio);


        signInForm = findViewById(R.id.signInForm);

        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_pass);
        email = findViewById(R.id.et_mail);

        String usernameString = username.getText()
                                        .toString();
        Intent intent = new Intent(this, GameModeLobby.class);
        intent.putExtra("userForm", usernameString);
        signInForm.setOnClickListener(v -> startActivity(intent));

    }
}