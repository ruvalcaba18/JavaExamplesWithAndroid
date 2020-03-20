package com.example.sqliteexample;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
         ListView lista;
         SimpleCursorAdapter adapter;
         final String[] from = new String[]{DatabaseHelper._ID,DatabaseHelper.SUBJECT,
                 DatabaseHelper.DESC};
final int[] to = new int[]{R.id.id,R.id.desc,R.id.country};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

           Cursor cursor = dbManager.fetch();
           lista= findViewById( R.id.list_view );
           lista.setEmptyView( findViewById( R.id.empty ) );
           adapter = new SimpleCursorAdapter( this,R.layout.renglon,cursor,from,to,0 );
           adapter.notifyDataSetChanged();
           lista.setAdapter( adapter );

           lista.setOnItemClickListener( new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?>
                                               adapterView,
                                       View view, int position,
                                       long id) {

                   TextView idTV = view.findViewById( R.id.id );
                   TextView titleTV = view.findViewById( R.id.country );
                   TextView descTV = view.findViewById( R.id.desc );
              String _id= idTV.getText().toString();
              String title = titleTV.getText().toString();
              String desc = descTV.getText().toString();

                   Intent modify_intent = new Intent( getApplicationContext(),updateActivity.class );
                   modify_intent.putExtra( "country",title );
                   modify_intent.putExtra( "desc",desc );
                   modify_intent.putExtra( "id",_id );
                   startActivity( modify_intent );


               }
           } );


//        Toolbar toolbar = findViewById( R.id.toolbar );
//        setSupportActionBar( toolbar );
//
//        FloatingActionButton fab = findViewById( R.id.fab );
//        fab.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
//                        .setAction( "Action", null ).show();
//            }
//        } );

        dbManager = new DBManager( this );
        dbManager.open();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent add= new Intent(this,
                    agregarActivity.class);
            startActivity( add );
        }

        return super.onOptionsItemSelected( item );
    }
}
