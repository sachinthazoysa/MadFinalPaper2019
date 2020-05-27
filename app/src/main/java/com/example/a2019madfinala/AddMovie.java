package com.example.a2019madfinala;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovie extends AppCompatActivity {
    Button addBtn;
    DBHandler dbHandler;
    EditText name, year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        addBtn=findViewById(R.id.Add);
        dbHandler = new DBHandler(this);
        year=findViewById(R.id.movieYear);
        name=findViewById(R.id.movieName);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = name.getText().toString();
                String mYear = year.getText().toString();
                boolean a =dbHandler.addMovies(mName,mYear);
                if(a){
                    Toast.makeText(AddMovie.this, "Added successfully", Toast.LENGTH_SHORT).show();
                    year.setText("");
                    name.setText("");
                }

                else
                    Toast.makeText(AddMovie.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
