package com.example.jonathanmaldonado.bookslist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jonathanmaldonado.bookslist.Books.Book;
import com.example.jonathanmaldonado.bookslist.Frutas.Fruta;
import com.example.jonathanmaldonado.bookslist.Frutas.Fruta_;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG =MainActivity.class.getSimpleName()+"._TAG";
    private static final String BASE_URL ="http://de-coding-test.s3.amazonaws.com/books.json";
    TextView resultsTV;
    OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsTV= (TextView) findViewById(R.id.tv_results);

        client = new OkHttpClient.Builder().build();


       // getFrutas();
        getBooks();

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



    public void getBooks() {


        Request request = new Request.Builder().url(BASE_URL).build();

        // thys is a Synchoronous request
        // this needs a separate thread
        // Response response = client.newCall(request).execute();

        client.newCall(request).enqueue(
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        if(response.isSuccessful()){



                            String resp= response.body().string();
                            try {


                                Gson gson = new Gson();
                                final Book[] book =  gson.fromJson(resp, Book[].class);

                                final StringBuilder resultsString = new StringBuilder();
                                for(int i = 0 ; i<book.length; i++){
                                    resultsString.append(book[i].getTitle().toString()+"\n");
                                }


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        resultsTV.setText(resultsString.toString());
                                    }
                                });


                            }catch (JsonParseException e){
                                e.printStackTrace();
                            }



                            Log.d(TAG, "onResponse resp:  "+ resp);
                        }else{
                            Log.d(TAG, "onResponse: Application Error");
                        }





                    }
                }
        );



    }



}
