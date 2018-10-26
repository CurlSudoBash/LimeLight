package com.example.kriti.uiassign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        mAdapter = new EventAdapter(getApplicationContext(), events);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        populateEventDetails();
        mAdapter.notifyDataSetChanged();
    }

    private void populateEventDetails() {
        events.add(new Events("EVENT1", "location 1", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT2", "location 2", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT3", "location 3", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT4", "location 4", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT5", "location 5", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT6", "location 6", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT7", "location 7", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT8", "location 8", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT9", "location 9", R.drawable.marker, "A", "Earthquake"));
        events.add(new Events("EVENT10", "location 10", R.drawable.marker, "A", "Earthquake"));
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, CreateEvent.class);
        startActivity(intent);
    }

}