package com.example.jonathanmaldonado.bookslist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathanmaldonado.bookslist.Books.Book;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Jonathan Maldonado on 9/9/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter <BooksAdapter.ViewHolder> {

    private Book[] mDataset;



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.TV_title.setText(mDataset[position].getTitle());
        holder.TV_author.setText(mDataset[position].getAuthor());

        final int finalPosition=position;
       // holder.IV_cover.setImageBitmap(LoadImageFromWebOperations(mDataset[position].getImageURL()));

        new Thread(new Runnable() {
            public void run() {
                URL url = null;
                try {
                    url = new URL(mDataset[finalPosition].getImageURL());
                    final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

                    holder.IV_cover.post(new Runnable() {
                        public void run() {
                            holder.IV_cover.setImageBitmap(bmp);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }).start();






    }




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TV_author;
        public TextView TV_title;
        public ImageView IV_cover;
        public ViewHolder(View v) {
            super(v);
            TV_author = v.findViewById(R.id.tv_item_author);
            TV_title = v.findViewById(R.id.tv_item_title);
            IV_cover=v.findViewById(R.id.iv_bookCover);



        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BooksAdapter(Book[] book) {



        mDataset = book;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {


        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        // set the view's size, margins, paddings and layout parameters


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
