package com.example.buscaminas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private double x,y;
    private boolean activo = true;
    private Tablero fondo;
    private Casilla[][] casillas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_main );
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        motionEvent.getX();
        motionEvent.getY();
        return false;
    }

    class Tablero extends View{

        public Tablero(Context context){
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawRGB( 0,0,0 );
            canvas.getWidth();
            canvas.getHeight();
            Paint paint = new Paint(  );
            paint.setTextSize( 50 );
            Paint paint2 = new Paint();
            paint2.setTextSize( 50 );
            paint2.setTypeface( Typeface.DEFAULT_BOLD );
            paint2.setARGB( 255,0,0,255 );

            Paint paintlinea = new Paint();
            paintlinea.setARGB( 255,255,255,255 );



        }


    }



}
