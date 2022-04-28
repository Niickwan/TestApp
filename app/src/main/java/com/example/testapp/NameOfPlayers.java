package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class NameOfPlayers extends Fragment {

    private FragmentActivity myContext;

    // to recieve input from textField
    EditText inputText;

    // Control when to switch button text | AddPlayer -> StartGame
    int numOfPlayers = 0;
    int numOfPlayersAdded = 0;
    boolean startGame = false;

    // Create ThisGame object
    ThisGame thisGame = new ThisGame();

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_of_players, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get input name from Text field
        inputText = (EditText) getActivity().findViewById(R.id.inputName);

        // Get Int number of players from previous Fragment
        numOfPlayers = requireArguments().getInt("numOfPlayers");

        Button next = (Button) view.findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, NumberOfPlayers.class, null)
                        .commit();*/
            }
        });

        Button addPlayer = (Button) view.findViewById(R.id.button_add_player);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!startGame) {
                    // Check length of name, must be between 1-20
                    if (inputText.getText().toString().length() < 1 || inputText.getText().toString().length() > 20) {
                        Toast.makeText(getContext(), "The length of the name\n must be between 1-20", Toast.LENGTH_SHORT).show();
                    } else {
                        // Pass on view and addPlayer button to method
                        addPlayer(view, addPlayer);
                    }
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("thisGame", thisGame);
                    FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_view, GameQuestionToFrom.class, bundle)
                            .commit();
                }
            }
        });
    }

    // Add player and +1 to numberOfPlayers to know when to start game.
    public void addPlayer(View view, Button addPlayer) {
        thisGame.addPlayer(inputText.getText().toString());
        Toast.makeText(getContext(), inputText.getText() + " has been added", Toast.LENGTH_SHORT).show();
        ++numOfPlayersAdded;
        // If added players is less than the number they chose, keep add player, else start game
        if (numOfPlayersAdded >= numOfPlayers) {
            addPlayer.setText("Start Game!");
            startGame = true;
        }

    }
}