package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BeneficiaryList extends AppCompatActivity {

    DatabaseHelper db;
    FloatingActionButton add_beneficiary_button;
    ArrayList<String> beneficiary_id, beneficiary_account_id, beneficiary_name, beneficiary_create_at;
    RecyclerView beneficiaries_recycler;
    BeneficiaryAdapter beneficiaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beneficiary_list);

        add_beneficiary_button = findViewById(R.id.add_beneficiary_button);
        add_beneficiary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeneficiaryList.this, CreateBeneficiary.class);
                startActivity(intent);
            }
        });

        beneficiaries_recycler = findViewById(R.id.beneficiaries_recycler);

        db = new DatabaseHelper(BeneficiaryList.this);
        beneficiary_id = new ArrayList<>();
        beneficiary_account_id = new ArrayList<>();
        beneficiary_name = new ArrayList<>();
        beneficiary_create_at = new ArrayList<>();

        getDataArrays();

        beneficiaryAdapter = new BeneficiaryAdapter(BeneficiaryList.this, beneficiary_id, beneficiary_name, beneficiary_account_id);
        beneficiaries_recycler.setAdapter(beneficiaryAdapter);
        beneficiaries_recycler.setLayoutManager( new LinearLayoutManager(BeneficiaryList.this));
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
