package com.mertyilmaz.lab1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

/**
 * Part C — Number Guessing Game.
 *
 * Rules implemented exactly as required:
 * - The app picks a random number in 1..10 (inclusive).
 * - Valid guesses are counted starting from 1; invalid input never counts.
 * - "Value too small" / "Value too large" feedback after each wrong guess.
 * - The game ends ONLY when the number is guessed on the 2nd attempt.
 *   A correct guess on attempt 1 or attempt >= 3 shows
 *   "Correct, but not on the 2nd attempt. Try again." and immediately starts
 *   a new round (new random number, attempt counter reset).
 */
public class GuessActivity extends AppCompatActivity {

    private static final int MIN = 1;
    private static final int MAX = 10;

    private final Random random = new Random();

    private int target;        // the secret number for the current round
    private int attempts;      // valid guesses made in the current round
    private boolean gameOver;  // true only after a correct 2nd-attempt guess

    private TextView textAttempt;
    private TextView textMessage;
    private EditText editGuess;
    private Button buttonCheck;
    private Button buttonNewGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);

        textAttempt = findViewById(R.id.textAttempt);
        textMessage = findViewById(R.id.textMessage);
        editGuess = findViewById(R.id.editGuess);
        buttonCheck = findViewById(R.id.buttonCheck);
        buttonNewGame = findViewById(R.id.buttonNewGame);

        buttonCheck.setOnClickListener(v -> checkGuess());
        buttonNewGame.setOnClickListener(v -> {
            textMessage.setText("");
            startNewRound();
        });

        if (savedInstanceState != null) {
            target = savedInstanceState.getInt("target");
            attempts = savedInstanceState.getInt("attempts");
            gameOver = savedInstanceState.getBoolean("gameOver");
            updateAttemptLabel();
            applyGameOverUi();
        } else {
            startNewRound();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("target", target);
        outState.putInt("attempts", attempts);
        outState.putBoolean("gameOver", gameOver);
    }

    private void startNewRound() {
        target = MIN + random.nextInt(MAX - MIN + 1);
        attempts = 0;
        gameOver = false;
        editGuess.setText("");
        updateAttemptLabel();
        applyGameOverUi();
    }

    private void checkGuess() {
        if (gameOver) {
            return;
        }

        String raw = editGuess.getText().toString().trim();

        int guess;
        try {
            guess = Integer.parseInt(raw);
        } catch (NumberFormatException e) {
            // Empty or not an integer — does not count as an attempt.
            textMessage.setText(R.string.msg_not_integer);
            return;
        }

        if (guess < MIN || guess > MAX) {
            // Out of range — does not count as an attempt.
            textMessage.setText(R.string.msg_out_of_range);
            return;
        }

        attempts++;
        editGuess.setText("");

        if (guess < target) {
            textMessage.setText(R.string.msg_too_small);
            updateAttemptLabel();
        } else if (guess > target) {
            textMessage.setText(R.string.msg_too_large);
            updateAttemptLabel();
        } else if (attempts == 2) {
            textMessage.setText(R.string.msg_correct_second);
            gameOver = true;
            applyGameOverUi();
        } else {
            textMessage.setText(R.string.msg_correct_not_second);
            startNewRound();
        }
    }

    /** Shows the number of the upcoming attempt (starts at 1, invalid input does not change it). */
    private void updateAttemptLabel() {
        textAttempt.setText(getString(R.string.attempt_label, attempts + 1));
    }

    private void applyGameOverUi() {
        editGuess.setEnabled(!gameOver);
        buttonCheck.setEnabled(!gameOver);
        buttonNewGame.setVisibility(gameOver ? View.VISIBLE : View.GONE);
    }
}
