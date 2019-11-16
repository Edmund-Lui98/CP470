package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MessageDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);
    }

    private class MessageFragment {
        public View onCreateView(int position, View convertView) {
            LayoutInflater inflater = MessageDetails.this.getLayoutInflater();
            View result = null;
            result = inflater.inflate(R.layout.msg_detail_frag, null);

            TextView message = result.findViewById(R.id.msg);
            TextView id = result.findViewById(R.id.id);

            message.setText(getItem(position));
            id.setText(getItem(position));

            return result;
        }
        public String getItem(int position) {
            return ChatWindow.msgs.get(position);
        }
    }

}
