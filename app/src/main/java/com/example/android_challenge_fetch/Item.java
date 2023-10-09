package com.example.android_challenge_fetch;

/**
 * Represents an item in a list with a unique identifier (ID), a list identifier (listID),
 * and a name.
 *
 * This class provides methods to access the item's attributes such as ID, list ID, and name.
 */
public class Item {
    private int id;
    private int listId;
    private String name;

    public Item(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }

}
