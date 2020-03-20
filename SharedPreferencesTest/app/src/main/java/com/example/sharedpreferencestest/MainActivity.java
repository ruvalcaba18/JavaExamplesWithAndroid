package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private EditText Emails;
    private EditText password;
    private Button Login;
    private Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Emails = findViewById( R.id.EmailText );
        password = findViewById( R.id.password );
        Login = findViewById( R.id.loginButton );

        sp = this.getSharedPreferences( "mypref",Context.MODE_PRIVATE );

        String esta= sp.getString( "user","no user" );
        if(esta.equalsIgnoreCase( "no user" )){
            Toast.makeText( this,"No hay usuario loggeado",Toast.LENGTH_SHORT ).show();

        }else{
            Gson gson = new Gson();
            gson.fromJson( esta,Usuario.class );
            Usuario eluser = gson.fromJson( esta,Usuario.class );
            elintento();

        }

        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Emails.getText().toString().length()>0 &&
                        password.getText().toString().length()>0){

                    Usuario user = new Usuario("Juan",
                            Emails.getText().toString(),
                            password.getText().toString().getBytes());

                    Gson gson = new Gson();
                    gson.toJson( user );
                    SharedPreferences.Editor editor = sp.edit();
                    editor.commit();
                    editor.apply();

                }
            }
        } );



    }

    private void elintento() {
        Intent intento = new Intent(this, secondActivity.class);
        startActivity( intento );
    }


}
