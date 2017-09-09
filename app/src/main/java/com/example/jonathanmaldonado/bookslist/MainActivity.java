package com.example.jonathanmaldonado.bookslist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathanmaldonado.bookslist.Books.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
    private String[] resultList;

    private ImageView profilePictureIV;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultsTV= (TextView) findViewById(R.id.tv_results);
        profilePictureIV= (ImageView) findViewById(R.id.iv_profilePicture);
        client = new OkHttpClient.Builder().build();

        getBooks();




    }


    public void setRecyclerView(){
        Log.d(TAG, "setRecyclerView: "+ resultList[0]);
        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new BooksAdapter(resultList);
        mRecyclerView.setAdapter(mAdapter);
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
                                resultList=new String[book.length];

                                for(int i = 0 ; i<book.length; i++){
                                    resultsString.append(book[i].getTitle().toString()+"\n");
                                    resultList[i]=book[i].getTitle().toString();
                                }

                                URL url = new URL(book[0].getImageURL());
                                final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        resultsTV.setText(resultsString.toString());
                                        profilePictureIV.setImageBitmap(bmp);

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


    public void getMyBooks(View view) {
        setRecyclerView();
    }
}
