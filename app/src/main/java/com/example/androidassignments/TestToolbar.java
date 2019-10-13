package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TestToolbar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "idk what to write", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    public boolean onCreateOptionsMenu (Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi) {
        switch(mi.getItemId()) {
            case R.id.refresh:
                Log.d("Toolbar", "Refresh selected");
                Snackbar.make(findViewById(R.id.coordinatorLayout), "You selected item 1", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                break;
            case R.id.trash:
                Log.d("Toolbar", "Trash selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.toolbar_dialogTitle)

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();
                break;
            case R.id.search:
                Log.d("Toolbar", "search selected");

                AlertDialog.Builder build = new AlertDialog.Builder(TestToolbar.this);
                LayoutInflater inflater = this.getLayoutInflater();
                final View v = inflater.inflate(R.layout.cust_dialog, null);

                build.setView(v)
                        // Add action buttons
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                EditText msg = v.findViewById(R.id.newMsg);
                                CharSequence enterMsg = msg.getText().toString();

                                Snackbar.make(findViewById(R.id.coordinatorLayout),enterMsg, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        }).show();
                break;
        }
        return true;
    }
}
