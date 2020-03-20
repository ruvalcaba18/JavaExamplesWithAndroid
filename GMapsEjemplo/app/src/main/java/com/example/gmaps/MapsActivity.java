package com.example.gmaps;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.admin.SystemUpdatePolicy;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MediaPlayer mp ;
    private LocationManager locManager;
    private LocationListener locListener;
    private LatLng Lastlong;

    private void comenzarLocalizacion() {
        locManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION )
                    != PackageManager.PERMISSION_GRANTED && checkSelfPermission
                    ( Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

                Location loc = locManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER);
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }

        locListener = new LocationListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onLocationChanged(Location location) {
                // metodo par pintar lineas y circulos dentro de maps
                LatLng latlong = new LatLng( location.getLatitude(),location.getLongitude() );

                if(Lastlong == null){
                Polyline linea = mMap.addPolyline( new PolylineOptions()
                .add(Lastlong,latlong )
                .width( 10 )
                .color( Color.BLUE )
                );

                Circle circulo = mMap.addCircle( new CircleOptions()
                .center( Lastlong )
                .radius( 2 )
                .strokeColor( Color.GREEN)
                        .fillColor( Color.RED )
                );

                }
// Metodo para Vibrar
                Vibrator vibrar = (Vibrator) getSystemService( Context.VIBRATOR_SERVICE );
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    vibrar.vibrate( VibrationEffect.createOneShot( 600,
                            VibrationEffect.DEFAULT_AMPLITUDE ) );

                }else{
                    vibrar.vibrate( 500 );
                }

/// se agrega un punto o marcador de donde se encuentra uno
mMap.addMarker( new MarkerOptions().position( latlong ).title( "La Posicion" ).snippet( "Soy el Punto" ) );
mMap.moveCamera( CameraUpdateFactory.newLatLng( latlong ) );
            }

            @Override
            public void onStatusChanged(String s, int status, Bundle bundle) {
                System.err.println( "Porvider Stauts "+ status );
            }

            @Override
            public void onProviderEnabled(String s) {
                System.err.println( "Provider On" );
            }

            @Override
            public void onProviderDisabled(String s) {
                System.err.println( "Provider Off" );
            }



        };
        locManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER,3000,1,locListener );


}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_maps );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        comenzarLocalizacion();


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng( 32.62781, -115.45446 );
        mMap.addMarker( new MarkerOptions().position( sydney ).title( "Chicali Vibes" ) );

        mMap.setMyLocationEnabled( true );
        mMap.getUiSettings().setMyLocationButtonEnabled( true );

        mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );


        if(ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION )
                == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled( true );

            Toast.makeText( this,"Tiene permisos",Toast.LENGTH_SHORT ).show();
        }else{
            String [] permi={Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions( this,permi,120 );
            Toast.makeText( this,"No Tiene permisos",Toast.LENGTH_SHORT ).show();
        }

        mMap.setOnMyLocationClickListener( new GoogleMap.OnMyLocationClickListener() {
            @Override
            public void onMyLocationClick(@NonNull Location location) {
                Toast.makeText( MapsActivity.this,("Lat[:"+location.getLatitude()+
                        "] Longitud["+location.getLongitude()+"]"),Toast.LENGTH_SHORT ).show();
            }
        } );

        mMap.setOnMyLocationButtonClickListener( new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                return false;
            }
        } );

        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if(requestCode == 120){
            if(permissions[0]== Manifest.permission.ACCESS_FINE_LOCATION
            &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled( true );
            }
        }
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
    }


    protected void reproducirsonido() {
        if(mp!= null){
            mp.reset();
            mp.release();
        }
        // crear en res la carpeta raw
        mp = MediaPlayer.create( this,R.raw.cow );
        mp.start();

    }
}
