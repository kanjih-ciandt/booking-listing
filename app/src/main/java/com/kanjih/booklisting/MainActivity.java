package com.kanjih.booklisting;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kanjih.booklisting.bookService.BookAdapter;
import com.kanjih.booklisting.bookService.BookLoadAsyncTask;
import com.kanjih.booklisting.to.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    public static final String LOG_TAG = MainActivity.class.getName();

    private String URL = "https://www.googleapis.com/books/v1/volumes?";

    private BookAdapter adapter;

    private ArrayList<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.isConnectOnInternet();

        ImageButton imgButton =  (ImageButton) findViewById(R.id.btn_search);
        imgButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(isConnectOnInternet()) {
                            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
                            progressBar.setVisibility(View.VISIBLE);
                            TextView mSearch = (TextView) findViewById(R.id.tx_search);
                            getLoaderManager().initLoader(mSearch.getText().hashCode(), null, MainActivity.this);
                        } else {
                            updateUI(new ArrayList<Book>());
                        }
                    }
                }
        );
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("bookList",  bookList);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        bookList = (ArrayList<Book>) savedInstanceState.getSerializable("bookList");

        if(bookList != null && !bookList.isEmpty()){
            updateUI(bookList);
        }
    }

    /**
     * Check if the app have access in the internet
     */
    private boolean isConnectOnInternet(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        Button bTryConnect = (Button) findViewById(R.id.try_connect);

        TextView mSearch = (TextView) findViewById(R.id.tx_search);
        ImageButton bSearch = (ImageButton) findViewById(R.id.btn_search);

        if(isConnected) {
            bTryConnect.setVisibility(View.GONE);
            mEmptyStateTextView.setText("");
            mSearch.setEnabled(true);
            bSearch.setEnabled(true);
        } else {
            bTryConnect.setVisibility(View.VISIBLE);
            mEmptyStateTextView.setText(R.string.no_internet);
            ProgressBar progressBar =  (ProgressBar) findViewById(R.id.progressbar);
            progressBar.setVisibility(View.GONE);
            mSearch.setText("");
            mSearch.setEnabled(false);
            bSearch.setEnabled(false);
        }

        return isConnected;
    }

    public void onClickTryConnect(View view) {
        isConnectOnInternet();
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader");
        TextView mSearch = (TextView) findViewById(R.id.tx_search);

        String parameter = mSearch.getText().toString().replace(" ","+");
        return new BookLoadAsyncTask(this, URL+"q="+parameter+"&maxResults=10");
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.i(LOG_TAG, "onLoadFinished");
        ProgressBar progressBar =  (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);


        if(adapter != null){
            adapter.clear();
        }

        if(books != null && !books.isEmpty()){
            mEmptyStateTextView.setText("");
            updateUI(books);
        } else{
            // Set empty state text to display "No book found."
            mEmptyStateTextView.setText(R.string.no_books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.i(LOG_TAG, "onLoaderReset");
        updateUI(new ArrayList<Book>());
    }

    /**
     * update the adapters
     * @param books
     */
    private void updateUI(final List<Book> books) {
        adapter = new BookAdapter(this,0,books);
        bookList = (ArrayList<Book>) books;
        // Find a reference to the {@link ListView} in the layout
        final ListView bookListView = (ListView) findViewById(R.id.list);
        bookListView.setAdapter(adapter);
    }
}
