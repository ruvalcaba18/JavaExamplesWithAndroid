package com.example.homeworkadapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.homeworkadapters.MainActivity.cartoons;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class cartoonsAdapter extends ArrayAdapter<cartoons> {

     public cartoonsAdapter(Activity context,cartoons[] datos ){
        super(context,R.layout.usuario,datos);


    }



}
