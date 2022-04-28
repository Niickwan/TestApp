package com.example.testapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private int id;
    private String name;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
