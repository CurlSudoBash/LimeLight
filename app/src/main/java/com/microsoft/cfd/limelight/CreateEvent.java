package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.cfd.limelight.Retrofit.RetrofitModule;
import com.microsoft.cfd.limelight.beans.Events;
import com.R;
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
        es = (EditText) findViewById(R.id.coordinator);
        String coordinator = es.getText().toString();
        es = (EditText) findViewById(R.id.disaster);
        String disaster = es.getText().toString();
        Utils.events.add(new Events(eventName, Utils.location, R.drawable.marker, coordinator, disaster));
        RetrofitModule.createEvent(eventName, coordinator, disaster);
        Toast toast = Toast.makeText(this, "Event created successfully", Toast.LENGTH_LONG);
        toast.show();
        Intent intent = new Intent(this, EventList.class);
        startActivity(intent);
    }
}
