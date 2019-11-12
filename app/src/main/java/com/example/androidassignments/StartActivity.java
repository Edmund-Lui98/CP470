package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class StartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner dropDown;
    public static String selected_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ACTIVITY_NAME = "CreateActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        dropDown = findViewById(R.id.dropDown);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(StartActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(myAdapter);
        dropDown.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selected_city = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        final String ACTIVITY_NAME = "onActivityResult!";

        if (requestCode == 10) {
            Log.i(ACTIVITY_NAME, "Returned to StartActivity.onActivityResult");
        }
    }

    protected void onResume() {
        super.onResume();
        final String ACTIVITY_NAME = "ResumeActivity";
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    protected void onStart() {
        super.onStart();
        final String ACTIVITY_NAME = "StartActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onPause() {
        super.onPause();
        final String ACTIVITY_NAME = "PauseActivity";
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    protected void onStop() {
        super.onStop();
        final String ACTIVITY_NAME = "StopActivity";
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        final String ACTIVITY_NAME = "DestroyActivity";
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    public void onClickLogIn(View view) {
        Intent myIntent = new Intent(this, ListItemsActivity.class);
        this.startActivity(myIntent);
    }

    public void onClickEnterChat(View view) {
        Intent myIntent = new Intent(this, ChatWindow.class);
        this.startActivity(myIntent);
    }

    public void onClickTestToolbar(View view) {
        Intent myIntent = new Intent(this, TestToolbar.class);
        this.startActivity(myIntent);
    }
    public void onClickWeatherForecast(View view) {
        Intent myIntent = new Intent(this, WeatherForecast.class);
        this.startActivity(myIntent);
    }
}
