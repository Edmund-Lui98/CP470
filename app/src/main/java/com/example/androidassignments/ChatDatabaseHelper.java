package com.example.androidassignments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Messages.db";
    public static final String TABLE_NAME = "Messages_Table";
    public static String KEY_ID = "id";
    public static String KEY_MESSAGE = "message";
    public static int VERSION_NUM = 1;
    public static String ACTIVITY_NAME = "ChatDatabaseHelper";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(ACTIVITY_NAME, "Calling onCreate");
        db.execSQL("create table " + TABLE_NAME + "("
                + KEY_ID + " integer primary key autoincrement, "
                + KEY_MESSAGE + " text not null);");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(ChatDatabaseHelper.class.getName(),"Upgrading database from version" + oldVersion + " to " + newVersion);
        db.execSQL("drop table if exists " + TABLE_NAME);
        VERSION_NUM = newVersion;
        onCreate(db);
    }

}
