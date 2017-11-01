package com.strahmore.bbitproject.sqlitelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler db = new DatabaseHandler(this);

        /**
         * *CRUD operations
         */
        //inserting contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contacts("Ravi", "9000363727"));
        db.addContact(new Contacts("Srinivas", "9182635547"));
        db.addContact(new Contacts("Tommy", "9522222222"));
        db.addContact(new Contacts("Karthik", "953333333333"));

        //Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<Contacts> contacts = db.getAllContacts();

        for (Contacts cn : contacts) {
            String log = "Id: " + cn.getID() + " , Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();

            //writing contacts log
            Log.d("Name: ", log);
        }

        EventsHandler ga = new EventsHandler(this);

        /**
         * *CRUD operations
         */
        //inserting contacts
        Log.d("Insert: ", "Inserting ..");
        ga.addEvent(new Events("Fireworks", "Nairobi"));
        ga.addEvent(new Events("Blue Kangaroos", "Mombasa"));
        ga.addEvent(new Events("Flying Kites", "Naivasha"));
        ga.addEvent(new Events("Oceans", "Nakuru"));

        //Reading all events
        Log.d("Reading: ", "Reading all contacts..");
        List<Events> events = ga.getAllEvents();

        for (Events cn : events) {
            String log = "Date: " + cn.getDate() + " , EventName: " + cn.getEventName() + " ,Location: " + cn.getLocation();

            //writing events log
            Log.d("Date: ", log);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
