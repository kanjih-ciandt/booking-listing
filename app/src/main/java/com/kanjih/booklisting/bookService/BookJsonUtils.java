package com.kanjih.booklisting.bookService;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.kanjih.booklisting.to.Book;
import com.kanjih.booklisting.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kneto on 2/19/17.
 */

public final class BookJsonUtils {

    public static final String LOG_TAG = BookJsonUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link BookJsonUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private BookJsonUtils(){

    }

    public static List<Book> fetchBooksData(String requestUrl){
        Log.i(LOG_TAG, "fetchBooksData");
        List<Book> bookList;
        URL url = JsonUtils.createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = JsonUtils.makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        bookList = extractBooks(jsonResponse);

        return bookList;
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<Book> extractBooks(String bookJSON) {
        ArrayList<Book> books = new ArrayList<Book>();

        try {
            // build up a list of Earthquake objects with the corresponding data.
            JSONObject root = new JSONObject(bookJSON);

            JSONArray arrayOfItens = root.getJSONArray("items");
            for(int i = 0; i < arrayOfItens.length(); i++){
                JSONObject item = arrayOfItens.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                ArrayList<String> authors = new ArrayList<String>();
                if(volumeInfo.has("authors")) {
                    JSONArray arrayOfAuthor = volumeInfo.getJSONArray("authors");

                    if(arrayOfAuthor !=null) {
                        for(int j = 0; j < arrayOfAuthor.length(); j++){
                            authors.add(arrayOfAuthor.getString(j));
                        }
                    }
                }

                Bitmap smallThumbnail = null;

                if(volumeInfo.has("imageLinks")) {
                    JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                    smallThumbnail = getImage(imageLinks.getString("smallThumbnail"));
                }
               books.add(new Book(item.getString("id"), volumeInfo.getString("title"), authors, smallThumbnail));
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e(LOG_TAG, "Problem parsing the  JSON results", e);
        }

        return books;
    }

    private static Bitmap getImage(String url) {
        Bitmap mIcon = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            mIcon = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return mIcon;
    }
}