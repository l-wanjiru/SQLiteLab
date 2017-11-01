package com.strahmore.bbitproject.sqlitelab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liane on 24/10/2017.
 */

public class EventsHandler extends SQLiteOpenHelper {

    //All static variables
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static  final String DATABASE_NAME = "eventsManager";

    //Contacts table name
    private  static  final  String TABLE_EVENTS = "events";

    //Contacts table columns names
    private  static  final  String KEY_DATE = "date";
    private static final String KEY_ENAME = "eventname";
    private static final String KEY_LOCATION = "location";

    public EventsHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    //creating tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE" + TABLE_EVENTS +
                "(" + KEY_DATE + " INTEGER PRIMARY KEY," + KEY_ENAME +
                " TEXT," + KEY_LOCATION + "TEXT" + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_EVENTS);

        //create tables again
        onCreate(db);
    }

    //Adding new event
    public void addEvent (Events event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ENAME, event.getEventName());//event name
        values.put(KEY_LOCATION, event.getLocation());//event phone number

        //Inserting row
        db.insert(TABLE_EVENTS, null, values);
        db.close();//closing database connection
    }

    //Getting single event
    public Events getEvent (int date) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENTS, new String[] { KEY_DATE, KEY_ENAME, KEY_LOCATION}, KEY_DATE + "=?", new String[] {String.valueOf(date) }, null, null, null,null);
        if (cursor != null)
            cursor.moveToFirst();

        Events event = new Events(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        //return event
        return event;
    }

    //Getting all events
    public List<Events> getAllEvents(){
        List<Events> eventList = new ArrayList<Events>();

        //selecting all query
        String selectQuery = " SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Events event = new Events();
                event.setDate(Integer.parseInt(cursor.getString(0)));
                event.setEventName(cursor.getString(1));
                event.setLocation(cursor.getString(2));

                //adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        //return event list
        return eventList;
    }

    //Getting events count
    public int getEventsCount() {
        String countQuery = "SELECT * FROM " + TABLE_EVENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        //return count
        return cursor.getCount();
    }

    //Updating single contact
    public int updateContact (Events event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ENAME, event.getEventName());
        values.put(KEY_LOCATION, event.getLocation());

        //updating row
        return db.update(TABLE_EVENTS, values, KEY_DATE + " = ?",
                new String[] {String.valueOf(event.getDate())  });
    }

    //Deleting event contact
    public void deleteEvent (Events event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_DATE + " = ?",
                new String[] {String.valueOf(event.getDate()) });
    }



}
