package com.example.android_challenge_fetch;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ItemLoader is an AsyncTaskLoader responsible for fetching and processing JSON data from a remote
 * server.
 * Returns list of Item objects
 */
public class ItemLoader extends AsyncTaskLoader<List<Item>> {

    private Exception error = null; // variable to handle exceptions

    public ItemLoader(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (error != null) {
            // If there was a previous error, reset it before reloading data
            error = null;
        }
        forceLoad();
    }

    /**
     * Executed on a background thread and is responsible for fetching JSON data
     * and converting it into a list of Item objects.
     *
     * @return A list of Item objects containing the parsed data.
     */
    @Override
    public List<Item> loadInBackground() {
        List<Item> items = new ArrayList<>();

        try {
            // Create a URL and establish an HTTP connection
            URL url = new URL("https://fetch-hiring.s3.amazonaws.com/hiring.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // If the HTTP response code indicates success
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(inputStreamReader);
                StringBuilder response = new StringBuilder();
                String line;

                // Read the response from the server and append it to line
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                JSONArray jsonArray = new JSONArray(response.toString());

                // Parse JSON data and create Item objects
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    int listId = jsonObject.getInt("listId");
                    String name = jsonObject.optString("name");

                    if (!name.isEmpty() && !name.equals("null")) {
                        items.add(new Item(id, listId, name));
                    }
                }

                // Sort the items by listId and then by name
                Collections.sort(items, (item1, item2) -> {
                    if (item1.getListId() == item2.getListId()) {
                        return item1.getName().compareTo(item2.getName());
                    }
                    return Integer.compare(item1.getListId(), item2.getListId());
                });

                reader.close();
                inputStreamReader.close();
            } else {
                // Handle HTTP error response
                error = new IOException("HTTP Error: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            // Handle other exceptions
            error = e;
        }

        return items;
    }

    @Override
    public void deliverResult(List<Item> data) {
        if (error != null) {
            // Handle and report the error by displaying a toast message
            Toast.makeText(getContext(), "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            super.deliverResult(data); // Deliver the data to the caller
        }
    }
}
