package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    //VARIABLES
    private int contador = 8;
    // varibles Calculadora
    String Display = "";
public EditText valores;
private String currentOperation = "";
private String resultado = "";



    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        // ir a las segunda vista
        Button elboton = findViewById( R.id.Igual );

        valores = (EditText) findViewById( R.id.Resultado );
        valores.setText( Display );
        Toast.makeText( MainActivity.this,"contador2"+contador++,Toast.LENGTH_LONG).show();

        int contador = getIntent().getIntExtra( "elcontador", 0 );
        valores.setText( String.valueOf( contador) );
    }
// Mark: implementacion de metodos para numeros
        public void onClickNumber (View v ){
        if(resultado != ""){
            Clear();
            update();
        }
            Button Botonpre = (Button) v;
            Display += Botonpre.getText();
            update();
        }

    public void update() {
        valores.setText(Display);
    }

    private boolean queOperacion(char opcion){
        switch(opcion){
            case '+': return true;

            case '-':return true;

            case '*':return true;


            case '/': return true;

            default:
                return false;

        }

    }
    // identificacion de Operadores
public void onClickOperator(View v){
        if(Display.isEmpty()) return;
    Button BotonPresionado = (Button) v;

    if(resultado !=  ""){
       String _Display = resultado;
        Clear();
        Display = _Display;


    }
    if (currentOperation != ""){
        Log.d( "Calc","" + Display.charAt( Display.length()-1 ));
        if( queOperacion(Display.charAt( Display.length()-1 ))){
            Display.replace(Display.charAt( Display.length()-1 ),BotonPresionado.getText().charAt( 0 ));
            update();
            return;
        }else{
            ObtenerResultado();
            Display = resultado;
            resultado = "";
        }
        currentOperation = BotonPresionado.getText().toString();
    }

Display += BotonPresionado.getText();
currentOperation += BotonPresionado.getText();
update();
}
// implementacion de borrar
public void Clear(){
        Display = "";
        currentOperation = "" ;
        update();
        resultado = "";

}
public void onClickClear(View v){
        Clear();
        update();
}
//implementacion de operaciones
public double Operacion(String uno,String dos, String Operaciones) {


    switch (Operaciones) {

        case "+":

            return (Double.valueOf( uno ) + Double.valueOf( dos ));


        case "-":

            return (Double.valueOf( uno ) - Double.valueOf( dos ));

        case "*":

            return (Double.valueOf( uno ) * Double.valueOf( dos ));

        case "/":
            try {

                return Double.valueOf( uno ) / Double.valueOf( dos );
            } catch (Exception e) {
            }
            Throwable e = null;
            Log.d( "Calc", e.getMessage() );

        default:
            return -1;

    }
}
 private boolean ObtenerResultado(){
        if(currentOperation == "") return false;
     String[] Operation = Display.split(Pattern.quote( currentOperation ));
     if(Operation.length < 2)return false;
        resultado = String.valueOf( Operacion(Operation[0],Operation[1],currentOperation));
        return true;
    }

    public void onClickResultado(View v){
        if(Display == "") return;
        if (!ObtenerResultado()) return;
            valores.setText( Display + "\n" + String.valueOf( resultado));

    }


}


