package com.example.kriti.uiassign;

/**
 * Created by kriti on 24/10/18.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kriti.uiassign.beans.Events;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    private List<Events> events;
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;

        public CustomViewHolder(View view) {
            super(view);
            eventName = (TextView) view.findViewById(R.id.eventName);
        }
    }

    public EventAdapter(List<Events> events) {
        this.events = events;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Events event = events.get(position);
        holder.eventName.setText(event.getEventName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
