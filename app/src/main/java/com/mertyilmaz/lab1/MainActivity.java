package com.mertyilmaz.lab1;

/*
 * Name: Hasan Yilmaz
 * Student ID: 56505
 * Lab: 1
 * Course: Introduction to Mobile Systems
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Part A — Program 1: "Hello World".
 * Shows "Hello World" centered on the screen as soon as the app starts.
 * The two buttons at the bottom open the other two programs of this lab.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonIntroduce).setOnClickListener(v ->
                startActivity(new Intent(this, IntroduceActivity.class)));

        findViewById(R.id.buttonGuess).setOnClickListener(v ->
                startActivity(new Intent(this, GuessActivity.class)));
    }
}
