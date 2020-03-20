package com.example.homeworkadapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     class ViewHolder{
        TextView nombres;
        TextView Description;

    }

    public class cartoons{
public String nombres;
public  String descripcion;


        public cartoons(String nombre, String descripcion) {
            this.nombres = nombre;
            this.descripcion = descripcion;

        }
    }

    cartoons[] caricaturas = {
            new cartoons( "Rick and Morty","Adult Swim Show"),
            new cartoons("The Simpsons","No Molestar Show"),
            new cartoons("Family Guy","No Molestar Show"),
            new cartoons("Sout Park","Mtv Show"),
            new cartoons( "Futurama","Fox" )
    };

     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate( savedInstanceState );
         setContentView( R.layout.activity_main );
         ListView Lista = findViewById( R.id.lista );

         ArrayAdapter<cartoons> cartoonsAdapter = new ArrayAdapter<cartoons>( this,
                 0, caricaturas )
         {

             @SuppressLint("CutPasteId")
             @Override
             public View getView(int position, View convertView,  ViewGroup parent) {

                 cartoons currentCartoons = caricaturas[position];



                 if(convertView == null) {
                     convertView = getLayoutInflater()
                             .inflate(R.layout.usuario, null, false);

                     ViewHolder Holder = new ViewHolder();

                     Holder.nombres =
                             (TextView)convertView.findViewById(R.id.Nombre);

                     Holder.Description =
                             (TextView)convertView.findViewById(R.id.Descripcion);

                     convertView.setTag(Holder);



                 }
                 TextView nombres =
                         ((ViewHolder)convertView.getTag()).nombres;
                 TextView description=
                         ((ViewHolder)convertView.getTag()).Description;


                 nombres.setText(currentCartoons.nombres);
                 description.setText(currentCartoons.descripcion);

                 nombres.setText(currentCartoons.nombres);
                 description.setText(currentCartoons.descripcion);
               return convertView;
             }
         } ;

Lista.setAdapter( cartoonsAdapter );
         }// final OnCreat
     }




