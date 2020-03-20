package com.example.textovoz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private ImageView imagenBocina;
    private EditText Editor;
    private TextToSpeech locutor;
    private SeekBar voz;
    private SeekBar velocidadVoz;
    private Spinner Idiomas;

    private double controladorVoz = 1.0;
    private double  controlVelocidad = 1.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        imagenBocina = findViewById( R.id.ImagenVocina );
        Editor = findViewById( R.id.sentencia );
        voz = findViewById( R.id.VozMH );
        velocidadVoz = findViewById( R.id.velocidadHabla );
        locutor = new TextToSpeech( this,this );
        Idiomas = findViewById( R.id.idiomasSpin );


        //primer seek
        voz.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int posicion, boolean fromUser) {
                controladorVoz = (float)posicion/(seekBar.getMax()/2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );
// segundo seek

        velocidadVoz.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                controlVelocidad = (float)progress/(seekBar.getMax()/2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        } );

        imagenBocina.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
speech();
            }
        } );

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int Status) {

     if(Status == TextToSpeech.SUCCESS){
         Locale Espaniol = new Locale( "es","Es" );
         locutor.setLanguage( Espaniol );
         Set<Voice> conjuntodeVoz = locutor.getVoices();
         for(Voice voz:conjuntodeVoz){
             System.err.println( "Nombre");
             System.err.println( voz.getName() );//agregar los nombres y los idiomas a un array
             System.err.println( "idioma" );
             System.err.println( voz.getLocale() );
             if(voz.getName().equalsIgnoreCase( "idiomas" )){ locutor.setVoice( voz );}
             Idiomas.setAdapter( new ArrayAdapter<String>(this,  ) );
         }

     }else{
         Toast.makeText(this,"No se pudo realizar tu Accion con tu celular chafa",Toast.LENGTH_SHORT).show();

     }


    }
    private void speech(){
        locutor.setPitch( (float)controladorVoz );
        locutor.setSpeechRate( (float)controlVelocidad );
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            locutor.speak( Editor.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,null );

        }else {
            locutor.speak( Editor.getText().toString(),TextToSpeech.QUEUE_FLUSH,null );
        }

    }
}
