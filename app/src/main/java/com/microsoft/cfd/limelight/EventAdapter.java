package com.microsoft.cfd.limelight;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.content.Context;
import com.squareup.picasso.Picasso;


import com.microsoft.cfd.limelight.beans.Events;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.CustomViewHolder> {
    private List<Events> events;
    private Context context;
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;
        public TextView location;
        public ImageView image_location;
        public TextView areaCoordinator;
        public TextView disasterType;

        public CustomViewHolder(View view) {
            super(view);
            eventName = (TextView) view.findViewById(R.id.eventName);
            location = (TextView) view.findViewById(R.id.location);
            image_location = (ImageView) view.findViewById(R.id.image_location);
            areaCoordinator = (TextView) view.findViewById(R.id.area_coordinator);
            disasterType = (TextView) view.findViewById(R.id.disaster_type);
        }
    }

    public EventAdapter(Context context, List<Events> events) {
        this.events = events;
        this.context = context;
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
        holder.eventName.setText(event.eventName);
        holder.eventName.setText(event.getEventName());
        holder.location.setText(event.getLocation());
        Picasso.with(context).load(events.get(position).getImageLocation()).resize(180, 180).into(holder.image_location);
        holder.areaCoordinator.setText(event.getAreaCoordinator());
        holder.disasterType.setText(event.getDisasterType());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
