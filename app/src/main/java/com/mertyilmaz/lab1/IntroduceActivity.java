package com.mertyilmaz.lab1;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Part B — Program 2: "Introduce Yourself".
 * Non-empty input (after trimming) -> "Witaj <input>".
 * Empty input -> exactly "Przedstaw się.".
 */
public class IntroduceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        EditText editName = findViewById(R.id.editName);
        TextView textGreeting = findViewById(R.id.textGreeting);

        findViewById(R.id.buttonWitaj).setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            if (name.isEmpty()) {
                textGreeting.setText(R.string.msg_introduce_empty);
            } else {
                textGreeting.setText(getString(R.string.msg_witaj, name));
            }
        });
    }
}
