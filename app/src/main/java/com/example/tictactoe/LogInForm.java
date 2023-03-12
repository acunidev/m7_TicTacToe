package com.example.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.gamemode.GameModeLobby;
import com.example.tictactoe.model.User;
import com.google.gson.Gson;

public class LogInForm extends AppCompatActivity {
    private EditText username, password;
    private Button logMein;
    private String[] validUsers = {"alex", "abelprofe", "test"};
    private String[] validPassword = {"so", "root", "mockup"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_form);

        username = findViewById(R.id.username_log);
        password = findViewById(R.id.password_log);


        logMein = findViewById(R.id.signInForm);
        logMein.setOnClickListener(log -> checkUser());
    }

    private void checkUser() {
        String usernameString = username.getText()
                                        .toString();
        String passwordString = password.getText()
                                        .toString();
        for (String validUser : validUsers) {
            for (String pass : validPassword) {
                if (validUser.equals(usernameString) && pass.equals(passwordString)) {
                    Intent intent = new Intent(this, GameModeLobby.class);
                    intent.putExtra("userLogin", usernameString);

                    SharedPreferences sharedPreferences = getSharedPreferences("userOnline", MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    User playerName1 = new User(usernameString, R.drawable.narutopic);
                    Gson gson = new Gson();
                    String player1ToString = gson.toJson(playerName1);
                    prefsEditor.putString("user1Online", player1ToString);
                    prefsEditor.apply();
                    startActivity(intent);
                    return;
                } else {
                    Toast.makeText(getBaseContext(), "Invalid USERNAME or PASSWORD", Toast.LENGTH_SHORT)
                         .show();
                }

            }
        }
    }
}