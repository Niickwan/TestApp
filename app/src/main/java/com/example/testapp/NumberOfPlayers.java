package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NumberOfPlayers extends Fragment implements View.OnTouchListener, AdapterView.OnItemSelectedListener {

    String numOfPlayers = "";
    Spinner spinner;
    boolean isSpinnerTouched = false;
    String[] playersToChooseFrom = {"Antal Spillere", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    private FragmentActivity myContext;

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    public NumberOfPlayers() {
        super(R.layout.fragment_number_of_players);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_number_of_players, container, false);
        //numberOfPlayersSpinner(view);
        numberOfPlayersSpinner(view);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button next = (Button) view.findViewById(R.id.button_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numOfPlayers.equalsIgnoreCase("") || numOfPlayers.equalsIgnoreCase("Antal Spillere")) {
                    Toast.makeText(getContext(), "Select number of players!", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("numOfPlayers", Integer.parseInt(numOfPlayers));
                    FragmentManager fragmentManager = myContext.getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .setReorderingAllowed(true)
                            .replace(R.id.fragment_container_view, NameOfPlayers.class, bundle)
                            .commit();
                }
            }
        });


    }

    private void numberOfPlayersSpinner(View view) {
        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.spinner_border, playersToChooseFrom);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnTouchListener(this);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(isSpinnerTouched) {
            isSpinnerTouched = false;
            numOfPlayers = playersToChooseFrom[position];
            Toast.makeText(getContext(), "Selected: " + playersToChooseFrom[position], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        isSpinnerTouched = true;
        return false;
    }
}