package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    RecyclerView transactions_recycler;
    DatabaseHelper db;
    ArrayList<String> transaction_id, transaction_account_id, transaction_description, transaction_created_at, transaction_amount;

    TransactionAdapter transactionAdapter;

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
        agencies_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MapsActivity.class);
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

        transactions_recycler = findViewById(R.id.transactions_recycler);

        db = new DatabaseHelper(Menu.this);
        transaction_id = new ArrayList<>();
        transaction_account_id = new ArrayList<>();
        transaction_description = new ArrayList<>();
        transaction_amount = new ArrayList<>();
        transaction_created_at = new ArrayList<>();

        getDataArrays();

        transactionAdapter = new TransactionAdapter(Menu.this, transaction_id, transaction_account_id, transaction_description, transaction_amount, transaction_created_at);
        transactions_recycler.setAdapter(transactionAdapter);
        transactions_recycler.setLayoutManager( new LinearLayoutManager(Menu.this));
    }

    void getDataArrays(){
        Cursor cursor = db.readAllTransactions();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                transaction_id.add(cursor.getString((0)));
                transaction_account_id.add(cursor.getString(9));
                transaction_description.add(cursor.getString(2));
                transaction_created_at.add(cursor.getString(3));
                transaction_amount.add(cursor.getString(5));
            }
        }
    }
}