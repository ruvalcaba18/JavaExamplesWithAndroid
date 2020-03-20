package com.example.menuexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnSinHilos;
    private Button btnHilo;
    private Button btnAsyncTask;
    private Button btnCancelar;
    private Button btnAsyncDialog;
    private ProgressBar pbarProgreso;
    private ProgressDialog pDialog;

    private MiTareaAsincrona tarea1;
    private MiTareaAsincronaDialog tarea2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );
        btnSinHilos = (Button)findViewById(R.id.btnSinHilos);
        btnHilo = (Button)findViewById(R.id.btnHilo);
        btnAsyncTask = (Button)findViewById(R.id.btnAsyncTask);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnAsyncDialog = (Button)findViewById(R.id.btnAsyncDialog);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);

        btnSinHilos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pbarProgreso.setMax(100);
                pbarProgreso.setProgress(0);
                for(int i=1; i<=10; i++) {
                  tareaLarga();
                    pbarProgreso.incrementProgressBy(10);
                }

                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        });

        btnHilo.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                new Thread( new Runnable() {
                    @Override
                    public void run() {

                        pbarProgreso.post( new Runnable() {
                            @Override
                            public void run() {
                                pbarProgreso.setProgress( 0 );
                            }
                        } );


                        for(int i=1 ; i<=10;i++){

                            tareaLarga();

                            pbarProgreso.post( new Runnable() {
                                @Override
                                public void run() {
                                    pbarProgreso.incrementProgressBy( 10 );
                                }
                            } );
                        }

                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                } ).start();
            }
        } );

        btnAsyncTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tarea1 = new MiTareaAsincrona();
                tarea1.execute();
            }

        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tarea1.cancel(true);
            }
        });


        btnAsyncDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setMessage("Procesando...");
                pDialog.setCancelable(true);
                pDialog.setMax(100);

                tarea2 = new MiTareaAsincronaDialog();
                tarea2.execute();
            }
        });

    }

    private void tareaLarga()
    {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {}
    }


    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                tareaLarga();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pbarProgreso.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            pbarProgreso.setMax(100);
            pbarProgreso.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }




    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                tareaLarga();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {

            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });

            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result)
            {
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(MainActivity.this, "Tarea cancelada!", Toast.LENGTH_SHORT).show();
        }
    }





        // Menu
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//
//       MenuInflater inflador =  getMenuInflater();
//
//               inflador.inflate(  R.menu.menua,menu);
//
//               MenuItem switchItem = menu.findItem( R.id.botonswitch );
//
//      switchItem.setActionView(R.layout.switch_item);
//Switch switchMenu = switchItem.getActionView().findViewById( R.id.nuevoswitch );
//        switchMenu.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                Mensaje( "Estado:["+isChecked+"]" );
//            }
//        } );
//
//        return true;
//    }
//
//    private void elintent(String texto) {
//        Intent intento = new Intent(Intent.ACTION_SEND);
//        intento.setType( "text/plain" );
//        intento.putExtra( "texto","El Menu"+texto );
//
//        startActivity( Intent.createChooser( intento,"Compartir en:" ) );
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        switch(id){
//
//            case R.id.item1:
//                Mensaje("item1");
//                elintent( "opciones 1" );
//                break;
//            case R.id.item2:
//                Mensaje("item2");
//                elintent( "opciones 2" );
//                break;
//            case R.id.grupo:
//                Mensaje("Grupo");
//                elintent( "grupo enviar" );
//                break;
//            case R.id.botonsearch:
//                Mensaje("Boton de Buscar");
//                break;
//            case R.id.botonswitch:
//                Mensaje("Switch Button");
//                break;
//
//        }
//        return super.onOptionsItemSelected( item );
//    }
//
//    public void Mensaje(String texto){
//        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
//
//    }
}
