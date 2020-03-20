package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.tictactoe.R.*;
import static com.example.tictactoe.R.menu.menu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private int puntos;
    private int puntosComputadora;
    private TextView desplegarPuntos;
    private TextView puntoComputadora;
    private boolean jugador1;
    private int rondas;
    public static final String user = "names";


    private Button[][] buttons = new Button[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( layout.activity_main );

        String user = getIntent().getStringExtra( "names" );
        Toast.makeText( this,"Bienvenido:"+ user,Toast.LENGTH_LONG ).show();


        desplegarPuntos = findViewById( id.puntosJugador );

        puntoComputadora = findViewById( id.puntosComputadora );

for(int coordX = 0;coordX < 3;coordX++){
    for(int coordY = 0 ; coordY < 3 ; coordY++){

        String coordenada = "boton"+coordX+coordY;
        int buscar = getResources().getIdentifier( coordenada,"id",getPackageName() );
        buttons[coordX][coordY] = findViewById( buscar );
        buttons[coordX][coordY].setOnClickListener(this );
    }
}

    }








    @Override
    public void onClick(View view) {


if(!((Button) view).getText().toString().equals( "" )){
    return;}

if(jugador1){
    ((Button) view).setText( "X" );
    ((Button) view).setTextColor( Color.BLUE );
}else{
    ((Button) view).setText( "O" );
    ((Button) view).setTextColor( Color.RED );
}
rondas++;

if(ganador()){
    if(jugador1){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            jugador1Gana();
        }
    }else{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            computadoraGana();
        }
    }
}else if (rondas == 9){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        empate();
    }
}else{
    jugador1 = !jugador1;
}

    }


    private boolean ganador(){

        String comprobar[][] = new String[3][3];

//primer for
        for(int coordX = 0; coordX < 3; coordX++){
            for(int coordY = 0; coordY < 3; coordY++) {

                comprobar[coordX][coordY] =  buttons[coordX][coordY].getText().toString();
            }
        }
// comprueba en forma vertical
        for(int x = 0 ; x < 3;x++){
            if(comprobar[x][0].equals( comprobar[x][1])
                    && comprobar[x][0].equals( comprobar[x][2])
                    && !comprobar[x][0].equals( "" ))
            {
                return true;
            }
        }

// comprueba en forma horizontal
        for(int x = 0 ; x < 3;x++){
            if(comprobar[0][x].equals( comprobar[1][x])
                    && comprobar[0][x].equals( comprobar[2][x])
                    && !comprobar[0][x].equals( "" ))
            {
                return true;
            }
        }

        // estos dos ifs comprueba de forma diagonal
        if(comprobar[0][0].equals( comprobar[1][1])
                && comprobar[0][0].equals( comprobar[2][2])
                && !comprobar[0][0].equals( "" ))
        {
            return true;
        }

        if(comprobar[0][2].equals( comprobar[1][1])
                && comprobar[0][2].equals( comprobar[2][0])
                && !comprobar[0][2].equals( "" ))
        {
            return true;
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void jugador1Gana(){
             puntos++;

        Toast.makeText(this,"Gano el Jugador",Toast.LENGTH_SHORT).show();

        desplegarPuntos.setText( "Puntos Jugador:" +puntos );
        puntoComputadora.setText(  "Puntos Computadora:" + puntosComputadora);
        repetirjuego();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void computadoraGana(){
        puntosComputadora++;
        Toast.makeText(this,"Gano el Jugador",Toast.LENGTH_SHORT).show();
        desplegarPuntos.setText( "Puntos Jugador:" +puntos );
        puntoComputadora.setText(  "Puntos Computadora:" + puntosComputadora);

        repetirjuego();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void repetirjuego(){

        for(int repetirHorizontal = 0;repetirHorizontal < 3 ;repetirHorizontal++){
            for(int repetirVertical = 0;repetirVertical < 3; repetirVertical ++){

buttons[repetirHorizontal][repetirVertical].setText( "" );
            }
        }
        rondas = 0;
        jugador1 = true;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void empate(){
        Toast.makeText(this,"Empate",Toast.LENGTH_SHORT).show();
        repetirjuego();
    }

    private void Compartir(String texto) {
        Intent intento = new Intent(Intent.ACTION_SEND);
        intento.setType( "text/plain" );
        intento.putExtra( "texto","El Menu"+texto );

        startActivity( Intent.createChooser( intento,"Compartir en:" ) );
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//
//       MenuInflater inflador =  getMenuInflater();
//
//               inflador.inflate(  R.menu.menu,menu);

        getMenuInflater().inflate( R.menu.menu, menu);
        return true;
    }


    // cuanto le pican al menu para salir o reiniciar
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        LayoutInflater inflador = LayoutInflater.from( MainActivity.this );
        final View imagen = inflador.inflate( layout.imageview,null );
        int id = item.getItemId();

        switch(id){
            case R.id.Compartir:
                share( );


            case R.id.Juego_Nuevo:
                Mensaje("Juego Nuevo");
                puntosComputadora = 0;
                puntos = 0;
                repetirjuego();


            case R.id.Salir:
                Mensaje("Salir del Juego");

                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder( MainActivity.this );
                alertDialog2.setMessage( "Quieres volver a la pantalla de inicio"  )
                        .setCancelable( false )
                        .setPositiveButton( "Si ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mAuth.getInstance().signOut();
                                Intent intento = new Intent(MainActivity.this,Login.class);
                                startActivity( intento );
                            }
                        } )// final boton

                        .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        } );

                AlertDialog alerta = alertDialog2.create();
                alerta.setTitle( "Salida" );
                alerta.setView(imagen );
                alerta.show();



        }
        return super.onOptionsItemSelected( item );
    }

    private void share() {
        Share compartir = new Share();
        compartir.shareOnFacebook( this );
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putInt( "rondas",rondas );
        outState.putInt( "puntos",puntos );
        outState.putInt( "puntosComputadora",puntosComputadora );
        outState.putBoolean( "jugador1",jugador1 );

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );
        rondas = savedInstanceState.getInt( "rondas" );
        puntos = savedInstanceState.getInt(  "puntos");
        puntosComputadora = savedInstanceState.getInt( "puntosComputadora" );
        jugador1 = savedInstanceState.getBoolean( "jugador1" );
    }

    // mensaje que sale al presionar cada boton del menu
    public void Mensaje(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();

    }



}
