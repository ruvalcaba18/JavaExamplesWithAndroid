package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class agregarActivity extends AppCompatActivity implements View.OnClickListener {

    private DBManager dbManager;
    private Button addtodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_agregar );
        dbManager = new DBManager( this);
        dbManager.open();
        subjectEditText = findViewById( R.id.subject_edittext );
        descEditText = findViewById( R.id.description_edittext );
        addtodoBtn = findViewById(R.id.add  );


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.add:
                final String country = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();
                dbManager.insert( country,desc );
                returnHome();
                break;
        }
    }

    public void returnHome(){
        Intent home = new Intent( this,MainActivity.class )
                .setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity( home );
    }
}
