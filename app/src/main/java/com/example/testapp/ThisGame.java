package com.example.testapp;

import java.io.Serializable;
import java.util.ArrayList;

public class ThisGame implements Serializable{
    private ArrayList<Player> players;
    private ArrayList<Round> round;

    public ThisGame () {
        players = new ArrayList<>();
        round = new ArrayList<>();
    }

    public void addPlayer(String name) {
        int id = players.size() + 1;
        players.add(new Player(id, name));
    }

}
