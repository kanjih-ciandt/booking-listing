package com.kanjih.booklisting.bookService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kanjih.booklisting.R;
import com.kanjih.booklisting.to.Book;

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
        mAuthor.setText(book.getAuthors().toString());




        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return convertView;

    }


}
