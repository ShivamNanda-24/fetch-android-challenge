package com.example.android_challenge_fetch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Adapter for populating a RecyclerView with a list of Item objects.
 * It associates the data with the ItemViewHolder, which represents the individual items in the list.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> itemList;
    public ItemAdapter(List<Item> items) {
        this.itemList = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates the item layout and returns an instance of ItemViewHolder.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        // Returns the total number of items in the itemList.
        return itemList.size();
    }

    /**
     * Individual item's view in the RecyclerView.
     */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView itemNameTextView;
        private TextView itemIdTextView;
        private TextView itemLisIdTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initializes the TextViews from the item layout.
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemIdTextView = itemView.findViewById(R.id.itemIdTextView);
            itemLisIdTextView = itemView.findViewById(R.id.itemListIdTextView);
        }

        public void bind(Item item) {
            // Sets the ID, List ID, and Name TextViews with data from the Item object.
            itemIdTextView.setText(String.valueOf(item.getId()));
            itemLisIdTextView.setText(String.valueOf(item.getListId()));
            itemNameTextView.setText(item.getName());
        }
    }
}
