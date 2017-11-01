package com.strahmore.bbitproject.sqlitelab;

/**
 * Created by liane on 24/10/2017.
 */

public class Events {
    //private variables
    int _date;
    String _eventname;
    String _location;

    //empty constructor
    public Events(){

    }
    //constructor
    public Events(int date, String eventname, String location){
        this._date = date;
        this._eventname = eventname;
        this._location = location;
    }

    //constructor
    public Events(String eventname, String location){
        this._eventname = eventname;
        this._location = location;
    }
    //getting date
    public int getDate(){
        return this._date;
    }

    //setting date
    public void setDate (int date){
        this._date = date;
    }

    //getting event name
    public String getEventName(){
        return this._eventname;
    }

    //setting event name
    public void setEventName (String eventname){
        this._eventname = eventname;
    }

    //getting location
    public String getLocation (){
        return this._location;
    }

    //setting location
    public void setLocation (String location){
        this._location = location;
    }
}
