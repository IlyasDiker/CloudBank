package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CreateTransaction extends AppCompatActivity {

    Spinner beneficiaries_spinner;
    TextView beneficiary_number, beneficiary_selname;
    DatabaseHelper db;
    EditText amount_field;
    ArrayList<String> beneficiary_id, beneficiary_account_id, beneficiary_name, beneficiary_create_at;
    String beneficiary_selected;
    Button create_transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(CreateTransaction.this);
        beneficiary_id = new ArrayList<>();
        beneficiary_account_id = new ArrayList<>();
        beneficiary_name = new ArrayList<>();
        beneficiary_create_at = new ArrayList<>();
        getDataArrays();

        amount_field = findViewById(R.id.amount_field);
        beneficiaries_spinner = findViewById(R.id.beneficiaries_spinner);
        beneficiary_number = findViewById(R.id.beneficiary_number);
        beneficiary_selname = findViewById(R.id.beneficiary_selname);

        ArrayAdapter array_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, beneficiary_name);
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        beneficiaries_spinner.setAdapter(array_adapter);
        beneficiaries_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String BeneficiaryName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + BeneficiaryName, Toast.LENGTH_LONG).show();
                beneficiary_number.setText(beneficiary_account_id.get(position).toString());
                beneficiary_selname.setText(beneficiary_name.get(position).toString());
                beneficiary_selected = beneficiary_id.get(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        create_transaction = findViewById(R.id.create_transaction);
        create_transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(CreateTransaction.this);
                myDB.addTransaction("admin", "testmotif", beneficiary_selected.toString().trim(), Integer.parseInt(amount_field.getText().toString()));

                // Intent intent = new Intent(CreateTransaction.this, TransactionSuccess.class);
                // startActivity(intent);
            }
        });
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