package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class secondActivity extends AppCompatActivity implements SensorEventListener {

    // VARIABLE PARA PODER UTILIZAR LOS SENSORES DEL TELEFONO
private SensorManager manager;
private TextView x;
private TextView y;
private  TextView z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );
        x= findViewById( R.id.x );
        y= findViewById( R.id.y );
        z = findViewById( R.id.z );


        SharedPreferences preferences = getSharedPreferences("mypref", MODE_PRIVATE );
       String  usuario =  preferences.getString( "user",
               "not user" );
        if (!usuario.equalsIgnoreCase( "not user" )){
            Gson gson = new Gson();
            Usuario eluser = gson.fromJson( usuario,Usuario.class );
            this.setTitle( "Hola" +eluser.getNombre());
        }
        manager = (SensorManager)
                getSystemService(SENSOR_SERVICE);


    }
//USO DE SENSORES
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float zeta = sensorEvent.values[0];
            float ex = sensorEvent.values[1];
            float ye = sensorEvent.values[2];

            x.setText( String.valueOf(  ex));
            y.setText(String.valueOf( ye ));
            z.setText( String.valueOf(   zeta));
        }

        if(sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION){

        }



    }
    @Override protected void onResume() {
        super.onResume();
        manager.registerListener( this,
                manager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER ),
                SensorManager.SENSOR_DELAY_NORMAL);

        manager.registerListener( this,
                manager.getDefaultSensor( Sensor.TYPE_ORIENTATION ),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
