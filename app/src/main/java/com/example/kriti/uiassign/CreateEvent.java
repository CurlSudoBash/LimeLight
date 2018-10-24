package com.example.kriti.uiassign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
    }

    public void submitEvent(View v)
    {
        Toast toast = Toast.makeText(this, "Event created successfully", Toast.LENGTH_LONG);
        toast.show();
    }
}
