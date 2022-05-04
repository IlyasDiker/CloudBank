package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    FloatingActionButton add_button;
    Button beneficiaries_btn, agencies_btn;

    DatabaseHelper db;
    ArrayList<String> beneficiary_id, beneficiary_account_id, beneficiary_name, beneficiary_create_at;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        beneficiaries_btn = findViewById(R.id.beneficiaries_btn);
        beneficiaries_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, BeneficiaryList.class);
                startActivity(intent);
            }
        });

        agencies_btn = findViewById(R.id.agencies_btn);
        beneficiaries_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, BeneficiaryList.class);
                startActivity(intent);
            }
        });

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, CreateTransaction.class);
                startActivity(intent);
            }
        });

        db = new DatabaseHelper(Menu.this);
        beneficiary_id = new ArrayList<>();
        beneficiary_account_id = new ArrayList<>();
        beneficiary_name = new ArrayList<>();
        beneficiary_create_at = new ArrayList<>();

        getDataArrays();

    }

    void getDataArrays(){
        Cursor cursor = db.readAllBeneficiaries();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                beneficiary_id.add(cursor.getString((0)));
                beneficiary_account_id.add(cursor.getString(1));
                beneficiary_name.add(cursor.getString(2));
                beneficiary_create_at.add(cursor.getString(3));
            }
        }
    }
}