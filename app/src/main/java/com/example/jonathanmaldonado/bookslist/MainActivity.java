package com.example.jonathanmaldonado.bookslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jonathanmaldonado.bookslist.Frutas.Fruta;
import com.example.jonathanmaldonado.bookslist.Frutas.Fruta_;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG =MainActivity.class.getSimpleName()+"._TAG";
    TextView resultsTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsTV= (TextView) findViewById(R.id.tv_results);


        getFrutas();

    }

    public void getFrutas(){
        String myjson = "{\n" +
                "   \"frutas\": [\n" +
                "       { \"nombre_fruta\":\"Manzana\" , \"cantidad\":10 }, \n" +
                "       { \"nombre_fruta\":\"Pera\" , \"cantidad\":20 }, \n" +
                "       { \"nombre_fruta\":\"Naranja\" , \"cantidad\":30 }\n" +
                "    ]\n" +
                "}";
        Gson gson = new Gson();
        Fruta frutas =  gson.fromJson(myjson, Fruta.class);
        Log.d(TAG, "getFrutas: "+ frutas.getFrutas().get(0).getNombreFruta().toString());
        resultsTV.setText(frutas.getFrutas().get(0).getNombreFruta().toString());
    }
}
