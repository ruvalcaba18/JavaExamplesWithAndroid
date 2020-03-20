package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class VolleyExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_volley );
    }

    public void peticionVolley(){
        RequestQueue queue = Volley.newRequestQueue( this );
        String url = "https://api.imgflip.com/get_memes";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,url, new Response.Listener<String>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(String response) {
                String res = response;
                System.err.println( res );
            }

        },
                new Response.ErrorListener() {
                    /**
                     * Callback method that an error has been occurred with the provided error code and optional
                     * user-readable message.
                     *
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( VolleyExample.this,
                                error.getMessage(),Toast.LENGTH_SHORT ).show();
                    }
                }

                );
        queue.add( stringRequest );

    }
}

public class Application {
    private boolean success;
    Data DataObject;


    // Getter Methods

    public boolean getSuccess() {
        return success;
    }

    public Data getData() {
        return DataObject;
    }

    // Setter Methods

    public void setSuccess( boolean success ) {
        this.success = success;
    }

    public void setData( Data dataObject ) {
        this.DataObject = dataObject;
    }
}

public class Data {
    ArrayList<Object> memes = new ArrayList<Object>();


    // Getter Methods



    // Setter Methods


}
