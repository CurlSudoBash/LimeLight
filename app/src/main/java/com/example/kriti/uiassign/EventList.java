package com.example.kriti.uiassign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kriti.uiassign.beans.*;

import java.util.ArrayList;
import java.util.List;

public class EventList extends AppCompatActivity {
    List<Events> events = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new EventAdapter(events);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        populateEventDetails();
        mAdapter.notifyDataSetChanged();
    }

    private void populateEventDetails() {
        events.add(new Events("EVENT1"));
        events.add(new Events("EVENT2"));
        events.add(new Events("EVENT3"));
        events.add(new Events("EVENT4"));
        events.add(new Events("EVENT5"));
        events.add(new Events("EVENT6"));
        events.add(new Events("EVENT7"));
        events.add(new Events("EVENT8"));
        events.add(new Events("EVENT9"));
        events.add(new Events("EVENT10"));
    }
}