package com.example.cloudbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login_button;

    EditText username_field, password_field;
    TextView debug, error_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();

        username_field = findViewById(R.id.username_field);
        password_field = findViewById(R.id.password_field);
        debug = findViewById(R.id.debug);
        error_field = findViewById(R.id.error_field);

        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = username_field.getText().toString().trim();
                String password = password_field.getText().toString().trim();

                if(username.equals(getString(R.string.auth_username)) && password.equals(getString(R.string.auth_password))){
                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "Wrong Password", Toast.LENGTH_SHORT).show();
                    error_field.setText("Wrong Password");
                }
            }
        });
    }
}