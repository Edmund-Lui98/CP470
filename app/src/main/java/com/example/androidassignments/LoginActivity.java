package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    Button login;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final String ACTIVITY_NAME = "CreateActivity";
        Log.i(ACTIVITY_NAME, "In onCreate()");

        user = (EditText) findViewById(R.id.login1);
        pass = (EditText) findViewById(R.id.password1);
        login = (Button) findViewById(R.id.LoginButton);
        sharedPreferences = getSharedPreferences("loginref",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        user.setText(sharedPreferences.getString("username",null));

    }


    public void login() {
        String username = user.getText().toString();

        editor.putBoolean("savelogin",true);
        editor.putString("username",username);
        editor.commit();

        Toast.makeText(this,"Enter",Toast.LENGTH_LONG);


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
        Intent myIntent = new Intent(this, StartActivity.class);
        this.startActivity(myIntent);
    }
}
