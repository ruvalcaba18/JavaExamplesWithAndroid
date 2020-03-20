package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SegundaVista extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_segunda_vista );


        int contador = getIntent().getIntExtra( "elcontador", 0 );
        Button Back = findViewById( R.id.Back );
Back.setOnClickListener( this );

    }
    public void onBack(View v){
        Intent Regresar = new Intent( SegundaVista.this,MainActivity.class ) ;
        String valor = ((TextView)findViewById( R.id.desplegar)).getText().toString();
        int desplegar = Integer.parseInt( valor ) ;
        Regresar.putExtra( "valor",(desplegar + 1 ));
        startActivity( Regresar );
    }



    @Override
    public void onClick(View view) {

    }
}
