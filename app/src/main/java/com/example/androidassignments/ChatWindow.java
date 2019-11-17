package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.androidassignments.ChatDatabaseHelper.KEY_MESSAGE;
import static com.example.androidassignments.ChatDatabaseHelper.TABLE_NAME;

public class ChatWindow extends AppCompatActivity {

    ListView lst;
    EditText msg;
    Button send;

    static ChatDatabaseHelper db;

    public static ArrayList<String> msgs = new ArrayList<>();

    final static String ACTIVITY_NAME = "ChatWindow";

    FrameLayout x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        lst = findViewById(R.id.lst);
        msg = findViewById(R.id.msg);
        send = findViewById(R.id.send);

        db = new ChatDatabaseHelper(this);
        final SQLiteDatabase database = db.getWritableDatabase();

        final ChatAdapter messageAdapter =new ChatAdapter( this );
        lst.setAdapter (messageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msgs.add(msg.getText().toString());
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                ContentValues contentValues = new ContentValues();
                contentValues.put(KEY_MESSAGE, msg.getText().toString());
                database.insert(TABLE_NAME,null, contentValues);
                msg.setText("");
            }
        });
        String GET_MESSAGES = "SELECT message, id FROM Messages_Table";
        final Cursor cursor = database.rawQuery(GET_MESSAGES,null);

        cursor.moveToFirst();
        Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + cursor.getColumnCount() );


        if (msgs.isEmpty()) {
            String a = cursor.getString(0);
            msgs.add(a);

            while (cursor.moveToNext()) {
                a = cursor.getString(0);
                msgs.add(a);
                Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + x);
            }
        }

    }
    private class ChatAdapter extends ArrayAdapter<String> {

        ChatDatabaseHelper db = new ChatDatabaseHelper(ChatWindow.this);
        final SQLiteDatabase database = db.getWritableDatabase();
        String GET_MESSAGES = "SELECT message, id FROM Messages_Table";
        final Cursor cursor = database.rawQuery(GET_MESSAGES,null);

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return msgs.size();
        }

        public String getItem(int position) {
            return msgs.get(position);
        }

        public long getItemId(int position) {
            cursor.moveToPosition(position);
            Long x = Long.parseLong(cursor.getString(1));
            return x;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);

            }else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            }
            TextView message = result.findViewById(R.id.msg);
            message.setText(getItem(position)); // get the string at position
            return result;
        }
        /*
        private boolean deleteMsg(long id) {
            return database.delete("Messages_Table", "id = ?", id);
        }

         */
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();

    }
}
