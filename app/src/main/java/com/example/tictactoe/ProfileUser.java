package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        View vHistorial1 = findViewById(R.id.winHistorial1);
        TextView win1 = vHistorial1.findViewById(R.id.my_text_view);
        win1.setText("Alex vs Abel");

        View vHistorial2 = findViewById(R.id.lossHistorial1);
        TextView loss1 = vHistorial2.findViewById(R.id.my_text_view);
        loss1.setText("Alex vs Edu");

        View vHistorial3 = findViewById(R.id.winHistorial2);
        TextView win2 = vHistorial3.findViewById(R.id.my_text_view);
        win2.setText("Alex vs Victor");
    }
}