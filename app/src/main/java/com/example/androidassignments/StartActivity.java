package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class StartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String ACTIVITY_NAME = "CreateActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");
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
}
