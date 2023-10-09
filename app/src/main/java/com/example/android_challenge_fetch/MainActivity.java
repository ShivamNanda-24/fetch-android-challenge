package com.example.android_challenge_fetch;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Item>> {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    private static final int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//         Initialize the Loader
        LoaderManager.getInstance(this).initLoader(LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<List<Item>> onCreateLoader(int id, @Nullable Bundle args) {
        return new ItemLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Item>> loader, List<Item> items) {
        adapter = new ItemAdapter(items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Item>> loader) {
        adapter = new ItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }
}
