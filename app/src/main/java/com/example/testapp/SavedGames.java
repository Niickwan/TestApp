package com.example.testapp;

import java.io.Serializable;
import java.util.ArrayList;

public class SavedGames implements Serializable {
    private ArrayList<ThisGame> game = new ArrayList<>();

    public SavedGames() {

    }
}
