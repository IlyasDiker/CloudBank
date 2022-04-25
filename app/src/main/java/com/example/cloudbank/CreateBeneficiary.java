package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateBeneficiary extends AppCompatActivity {

    EditText acountid_field, name_field;
    Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_beneficiary);
        Context context = getApplicationContext();

        acountid_field = findViewById(R.id.acountid_field);
        name_field = findViewById(R.id.name_field);
        save_button = findViewById(R.id.save_button);

        save_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(acountid_field.getText().toString().trim() != ""){
                    DatabaseHelper myDB = new DatabaseHelper(CreateBeneficiary.this);
                    myDB.addBeneficiary(acountid_field.getText().toString().trim(), name_field.getText().toString().trim());
                } else {
                    Toast.makeText(context, "Invalid Form", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}