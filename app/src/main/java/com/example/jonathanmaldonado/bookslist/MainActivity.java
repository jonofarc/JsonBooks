package com.example.jonathanmaldonado.bookslist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.jonathanmaldonado.bookslist.Books.Book;
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
    public void getBooks(){

        String myjson = "[\n" +
                "  {\n" +
                "    \"title\" : \"Harry Potter: Complete 8-Film Collection (DVD, 2011, 8-Disc Set)\",\n" +
                "    \"imageURL\" : \"http://i.ebayimg.com/00/$(KGrHqV,!g0E6ZCwQ)wpBOuWbUNB,g~~_6.JPG?set_id=89040003C1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\" : \"Harry Potter and the Sorcerer's Stone (DVD, 2002, 2-Disc Set, Widescreen)\",\n" +
                "    \"imageURL\" : \"http://i.ebayimg.com/10/!!eBlRg!EGM~$(KGrHqQOKkIE0YRojg,kBNQ)(j8Q1!~~_6.JPG?set_id=89040003C1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\" : \"Harry Potter: Complete 8-Film Collection (Blu-ray Disc, 2011, 8-Disc Set)\",\n" +
                "    \"imageURL\" : \"http://i.ebayimg.com/00/$(KGrHqR,!igE6M5wILm(BOuWZc0bjg~~_6.JPG?set_id=89040003C1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"title\" : \"Harry Potter Years 1-7 by J. K. Rowling and Inc. Staff Scholastic (2007, Hardcover)\",\n" +
                "    \"author\" : \"J.K. Rowling\",\n" +
                "    \"imageURL\" : \"http://i.ebayimg.com/00/$T2eC16F,!)kE9s4Z-Ue7BRb4ZE0oog~~_6.JPG?set_id=89040003C1\"\n" +
                "  }\n" +
                "]";
        Gson gson = new Gson();
        Book[] book =  gson.fromJson(myjson, Book[].class);
        //Log.d(TAG, "getFrutas: "+ frutas.getFrutas().get(0).getNombreFruta().toString());
        resultsTV.setText(book[0].getTitle().toString());

    }
}
