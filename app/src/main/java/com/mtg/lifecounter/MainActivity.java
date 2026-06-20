package com.mtg.lifecounter;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int DEFAULT_LIFE = 20;

    private int player1Life = DEFAULT_LIFE;
    private int player2Life = DEFAULT_LIFE;

    private TextView player1LifeText;
    private TextView player2LifeText;
    private View player1Panel;
    private View player2Panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);

        player1LifeText = findViewById(R.id.player1_life);
        player2LifeText = findViewById(R.id.player2_life);
        player1Panel = findViewById(R.id.player1_panel);
        player2Panel = findViewById(R.id.player2_panel);

        Button p1Plus = findViewById(R.id.player1_plus);
        Button p1Minus = findViewById(R.id.player1_minus);
        Button p1Plus5 = findViewById(R.id.player1_plus5);
        Button p1Minus5 = findViewById(R.id.player1_minus5);

        Button p2Plus = findViewById(R.id.player2_plus);
        Button p2Minus = findViewById(R.id.player2_minus);
        Button p2Plus5 = findViewById(R.id.player2_plus5);
        Button p2Minus5 = findViewById(R.id.player2_minus5);

        Button resetButton = findViewById(R.id.reset_button);
        Button startingLifeButton = findViewById(R.id.starting_life_button);

        p1Plus.setOnClickListener(v -> updateLife(1, 1));
        p1Minus.setOnClickListener(v -> updateLife(1, -1));
        p1Plus5.setOnClickListener(v -> updateLife(1, 5));
        p1Minus5.setOnClickListener(v -> updateLife(1, -5));

        p2Plus.setOnClickListener(v -> updateLife(2, 1));
        p2Minus.setOnClickListener(v -> updateLife(2, -1));
        p2Plus5.setOnClickListener(v -> updateLife(2, 5));
        p2Minus5.setOnClickListener(v -> updateLife(2, -5));

        resetButton.setOnClickListener(v -> resetLife());
        startingLifeButton.setOnClickListener(v -> showStartingLifeDialog());

        updateDisplay();
    }

    private void updateLife(int player, int amount) {
        if (player == 1) {
            player1Life += amount;
        } else {
            player2Life += amount;
        }
        updateDisplay();
    }

    private void resetLife() {
        player1Life = DEFAULT_LIFE;
        player2Life = DEFAULT_LIFE;
        updateDisplay();
    }

    private void showStartingLifeDialog() {
        String[] options = {"20 (Standard)", "40 (Commander)", "25 (Vanguard)", "30 (Two-Headed Giant)"};
        int[] values = {20, 40, 25, 30};

        new AlertDialog.Builder(this)
                .setTitle("Starting Life Total")
                .setItems(options, (dialog, which) -> {
                    player1Life = values[which];
                    player2Life = values[which];
                    updateDisplay();
                })
                .show();
    }

    private void updateDisplay() {
        player1LifeText.setText(String.valueOf(player1Life));
        player2LifeText.setText(String.valueOf(player2Life));

        if (player1Life <= 0) {
            player1Panel.setBackgroundResource(R.drawable.panel_dead);
        } else if (player1Life <= 5) {
            player1Panel.setBackgroundResource(R.drawable.panel_danger);
        } else {
            player1Panel.setBackgroundResource(R.drawable.panel_player1);
        }

        if (player2Life <= 0) {
            player2Panel.setBackgroundResource(R.drawable.panel_dead);
        } else if (player2Life <= 5) {
            player2Panel.setBackgroundResource(R.drawable.panel_danger);
        } else {
            player2Panel.setBackgroundResource(R.drawable.panel_player2);
        }
    }
}
