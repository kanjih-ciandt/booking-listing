package com.kanjih.booklisting.bookService;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.kanjih.booklisting.to.Book;

import java.util.List;

/**
 * Created by kneto on 2/19/17.
 */

public class BookLoadAsyncTask extends AsyncTaskLoader<List<Book>> {

    public static final String LOG_TAG = BookLoadAsyncTask.class.getName();
            String url;


    public BookLoadAsyncTask(Context context, String url){
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground");
        List<Book> bookList = BookJsonUtils.fetchBooksData(url);

        return bookList;
    }
}
