package com.microsoft.cfd.limelight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.R;

import com.bumptech.glide.Glide;

import java.util.List;

public class DroneAdapter extends RecyclerView.Adapter<DroneAdapter.MyViewHolder> {
    private Context mContext;
    private List<Drone> droneList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public DroneAdapter(Context mContext, List<Drone> droneList) {
        this.mContext = mContext;
        this.droneList = droneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.drone_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Drone drone = droneList.get(position);
        holder.title.setText(drone.getName());

        // loading album cover using Glide library
        Glide.with(mContext).load(drone.getThumbnail()).into(holder.thumbnail);

        /*holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    /*private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }*/

    @Override
    public int getItemCount() {
        return droneList.size();
    }

}
