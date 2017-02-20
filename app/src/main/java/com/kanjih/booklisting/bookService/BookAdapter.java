package com.kanjih.booklisting.bookService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanjih.booklisting.R;
import com.kanjih.booklisting.to.Book;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by kneto on 2/19/17.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, int resource, List<Book> books){
        super(context,0,books);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book book = getItem(position);

        TextView mTitle = (TextView) convertView.findViewById(R.id.book_title);
        TextView mAuthor = (TextView) convertView.findViewById(R.id.book_author);

        mTitle.setText(book.getTitle());
        mAuthor.setText(this.converterAuthors(book.getAuthors()));

        if(book.getSmallThumbnail() != null){
            ImageView img = (ImageView) convertView.findViewById(R.id.img_book);
            img.setImageBitmap(book.getSmallThumbnail());
        }

        return convertView;

    }

    private String converterAuthors(List<String> authors) {
        StringBuilder builder = new StringBuilder().append("by ");

        for(String author : authors){
            builder.append(author+ ", ");
        }
        int pos = builder.lastIndexOf(",");

        if(pos > 0) {
            builder.replace(pos, pos + 1, "");
        }

        if(builder.length() == 3) {
            builder.append("-");
        }



        return  builder.toString();
    }

}
