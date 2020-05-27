package com.example.a2019madfinala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button regBtn, loginBtn;
    EditText usernameField, passwordField;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regBtn = findViewById(R.id.RegisterMain);
        loginBtn = findViewById(R.id.loginMain);
        usernameField = findViewById(R.id.nameMain);
        passwordField = findViewById(R.id.passwordMain);
        dbHandler = new DBHandler(this);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                long i= dbHandler.registerUser(name,password);

                Toast.makeText(MainActivity.this,""+i, Toast.LENGTH_SHORT).show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                int a= dbHandler.loginUser(name, password);
                if(a == 1){
                    Intent intent= new Intent(MainActivity.this, AddMovie.class);
                    startActivity(intent);
                }
                else if(a == 2){
                    Intent intent= new Intent(MainActivity.this, MovieList.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
