package com.example.kriti.uiassign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kriti.uiassign.Retrofit.RetrofitModule;
import com.example.kriti.uiassign.beans.Events;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    public void submitEvent(View v)
    {
        EditText es = (EditText) findViewById(R.id.eventName);
        String eventName = es.getText().toString();
        Utils.events.add(new Events(eventName, Utils.location));
        RetrofitModule.createEvent(eventName);
        Toast toast = Toast.makeText(this, "Event created successfully", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, EventList.class);
        startActivity(intent);
    }
}