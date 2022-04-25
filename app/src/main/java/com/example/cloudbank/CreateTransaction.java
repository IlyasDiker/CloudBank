package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CreateTransaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}