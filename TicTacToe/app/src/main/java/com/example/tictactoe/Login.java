package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;


public class Login extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";
    private EditText Email;
    private EditText Password;
    private Button Login;
    private Button crearUsuario;
    private TextView detail;
    private TextView status;
    private String correo,contrasenia;
    private ProgressDialog progreso;
    private Button verifyEmail;

private FirebaseAuth.AuthStateListener escuchador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );


        mAuth = FirebaseAuth.getInstance();
        crearUsuario = findViewById( R.id.emailCreateAccountButton );
        Login = findViewById( R.id.emailSignInButton );
        Email = findViewById( R.id.fieldEmail );
        Password = findViewById( R.id.fieldPassword );
        status = findViewById( R.id.status );
        detail = findViewById( R.id.detail );
         progreso  = new ProgressDialog( this );
         verifyEmail = findViewById( R.id.verifyEmailButton );


        escuchador = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!= null) {
                    Intent intento = new Intent( Login.this, MainActivity.class );

                }else{
                    Toast.makeText( Login.this, "El usuario no existe", Toast.LENGTH_LONG ).show();
                }
            }
        };


// Ingresar
        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                correo = Email.getText().toString().trim();
                contrasenia = Password.getText().toString().trim();

                if ( Email.getText().toString().isEmpty()){
                    Toast.makeText( Login.this,"El Email esta vacio, porfavor ingrese uno..",Toast.LENGTH_SHORT ).show();
                    return;
                }

                if ( Password.getText().toString().isEmpty()){
                    Toast.makeText( Login.this,"El Password esta vacio, porfavor ingrese uno..",Toast.LENGTH_SHORT ).show();
                    return;
                }
                progreso.setMessage( "Realizando consulta en linea.." );
                progreso.show();



                //Login de Usuario

                mAuth.signInWithEmailAndPassword( correo,contrasenia ).addOnCompleteListener( Login.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Intent intento = new Intent( Login.this,MainActivity.class );
                            intento.putExtra(  MainActivity.user,correo);
                            Toast.makeText( Login.this,"Bienvenido:" + Email.getText().toString(),Toast.LENGTH_LONG ).show();
                            startActivity(intento);

                        }else{

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {


                                Toast.makeText( Login.this,"Fue Negado el registro por alguna razon",Toast.LENGTH_SHORT ).show();

                            }else{
                                Toast.makeText( Login.this,"Ese usuario ya existe",Toast.LENGTH_SHORT ).show();
                            }

                            Toast.makeText( Login.this,"Fue Negado el registro",Toast.LENGTH_SHORT ).show();
                        }

                        progreso.dismiss();

                    }

                } ); // fina Login


            }
        } );




         //Registrar Usuario
                 crearUsuario.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       correo = Email.getText().toString().trim();
       contrasenia = Password.getText().toString().trim();

       if ( Email.getText().toString().isEmpty()){
         Toast.makeText( Login.this,"El Email esta vacio, porfavor ingrese uno..",Toast.LENGTH_SHORT ).show();
            return;
       }

        if ( Password.getText().toString().isEmpty()){
            Toast.makeText( Login.this,"El Password esta vacio, porfavor ingrese uno..",Toast.LENGTH_SHORT ).show();
            return;
        }
        progreso.setMessage( "Realizando registro en linea.." );
        progreso.show();

        mAuth.createUserWithEmailAndPassword( correo,contrasenia )
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText( Login.this,"Fue Exitoso el registro del usuario:" + Email.getText().toString(),Toast.LENGTH_LONG ).show();
                            Intent intento = new Intent( Login.this,MainActivity.class );
                            startActivity(intento);
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText( Login.this,"Fue raro el registro",Toast.LENGTH_SHORT ).show();

                            }else{
                                Toast.makeText( Login.this,"Ese usuario ya existe",Toast.LENGTH_SHORT ).show();
                            }


                            Toast.makeText( Login.this,"Fue Negado el registro ingrese una contrasenai de 6 digitos" ,Toast.LENGTH_SHORT ).show();
                        }
                        progreso.dismiss();
                    }
                } );
    }


} );


    }

    // verifica si un usuario esta Login
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener( escuchador );
    }


    //metodo de verificacion para el Login


public void verificacionDeLogin(View view){
FirebaseUser usuario = mAuth.getCurrentUser();
if(usuario.equals( mAuth.getCurrentUser() )){
    Toast.makeText( Login.this,"El Usuario ya existe",Toast.LENGTH_LONG ).show();
}else{
    Toast.makeText( Login.this,"El Usuario no existe",Toast.LENGTH_LONG ).show();
}

}


 }



