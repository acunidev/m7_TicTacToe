package com.example.tictactoe.gamemode;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tictactoe.MainActivity;
import com.example.tictactoe.R;
import com.example.tictactoe.model.User;
import com.google.gson.Gson;

import java.util.Random;

public class GameOnline extends AppCompatActivity implements View.OnClickListener {
    boolean activePlayer;
    //    p1 = 0
    //    p2 = 1
    //    vacio = 2
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winnintPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // filas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columnas
            {0, 4, 8}, {2, 4, 6} // diagonales
    };

    private TextView player1Name, player2Name;
    private ImageButton menuSettings, backButton;

    private Button[] buttons = new Button[9];
    private Button resetGame;
    private int rountCount;
    private SharedPreferences sharedPreferences;
    private User player1;
    private User player2;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        getPlayers();

        View includeView = findViewById(R.id.menutopGame);
        TextView textoEnMedio = includeView.findViewById(R.id.texto_menu);
        backButton = includeView.findViewById(R.id.back_arrow);
        backButton.setBackgroundResource(R.drawable.backarrow);
        backButton.setOnClickListener(this);

        menuSettings = includeView.findViewById(R.id.settingsBtn_menu);
        menuSettings.setBackgroundResource(R.drawable.settingsicon);
        menuSettings.setOnClickListener(this);
        textoEnMedio.setText("Offline Mode");


        resetGame = findViewById(R.id.resetGame);
        for (int i = 0; i < buttons.length; i++) {
            String buttonId = "btn" + i;
            int getResourcesId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons[i] = findViewById(getResourcesId);
            buttons[i].setOnClickListener(this);

        }

        rountCount = 0;
        activePlayer = true;

    }

    @Override
    public void onClick(View v) {
        String buttonId = v.getResources()
                           .getResourceEntryName(v.getId()); //btn_2
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length() - 1)); //2
        if (((Button) v).getText()
                        .toString()
                        .equals("")) {
            if (activePlayer) {
                ((Button) v).setText("X");
                ((Button) v).setTextColor(Color.parseColor("#FFC34A"));
                gameState[gameStatePointer] = 0;
            } else {
                // Es el turno de la máquina
                int randomIndex;
                do {
                    // Generar un índice aleatorio
                    randomIndex = new Random().nextInt(gameState.length);
                } while (gameState[randomIndex] != 2);

                // Marcar la casilla elegida con el símbolo de la máquina
                Button button = findViewById(getResources().getIdentifier("btn" + randomIndex, "id", getPackageName()));
                button.setText("O");
                button.setTextColor(Color.parseColor("#70FFEA"));
                gameState[randomIndex] = 1;

            }
            rountCount++;


            if (checkWinner()) {
                if (activePlayer) {
                    Toast.makeText(this, player1.getUsername() + " won!", Toast.LENGTH_SHORT)
                         .show();
                    sendWinnerData(player1);
                } else {
                    Toast.makeText(this, "Random player won!", Toast.LENGTH_SHORT)
                         .show();
                    sendWinnerData(player2);
                }
            } else if (rountCount == 9) {
                Toast.makeText(this, "No Winner", Toast.LENGTH_SHORT)
                     .show();
                playAgain();

            } else {
                activePlayer = !activePlayer;
            }
        }

        resetGame.setOnClickListener(v1 -> playAgain());
        backButton.setOnClickListener(v1 -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));

    }

    public boolean checkWinner() {
        boolean hasWinner = false;

        for (int[] winnintPosition : winnintPositions) {
            if (gameState[winnintPosition[0]] == gameState[winnintPosition[1]] &&
                    gameState[winnintPosition[1]] == gameState[winnintPosition[2]] &&
                    gameState[winnintPosition[0]] != 2) {
                hasWinner = true;
            }
        }
        return hasWinner;
    }

    public void playAgain() {
        rountCount = 0;
        activePlayer = true;

        for (int i = 0; i < buttons.length; i++) {
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }

    public void sendWinnerData(User user) {
        Intent intent = new Intent(this, Win.class);
        sharedPreferences = getSharedPreferences("winner", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String wnner = gson.toJson(user);
        prefsEditor.putString("winner1", wnner);
        prefsEditor.apply();
        startActivity(intent);
    }

    public void getPlayers() {
        player1Name = findViewById(R.id.player1);
        player2Name = findViewById(R.id.player2);

        Gson gson = new Gson();
        sharedPreferences = getSharedPreferences("userOnline", MODE_PRIVATE);
        String player1String = sharedPreferences.getString("user1Online", "");
        player1 = gson.fromJson(player1String, User.class);
        player1Name.setText(player1.getUsername());
        player2Name.setText("Random Player");
    }
}

