package com.example.android_challenge_fetch;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    private TextView itemNameTextView;
    private TextView itemIdTextView; // Add this
    private TextView itemLisIdTextView; // Add this

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
        itemIdTextView = itemView.findViewById(R.id.itemIdTextView); // Initialize the TextViews
        itemLisIdTextView = itemView.findViewById(R.id.itemListIdTextView);
    }

    public void bind(Item item) {
        // Bind item data to the views
        itemIdTextView.setText(String.valueOf(item.getId())); // Set the ID
        itemLisIdTextView.setText(String.valueOf(item.getListId())); // Set the List ID
        itemNameTextView.setText(item.getName());
    }
}
