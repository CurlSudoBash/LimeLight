package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.microsoft.cfd.limelight.DroneInterface.flight.service.DroneControlService;

public class DroneConfigChangedReceiver extends BroadcastReceiver
{
    
    private DroneConfigChangedReceiverDelegate delegate;

    
    public DroneConfigChangedReceiver(DroneConfigChangedReceiverDelegate delegate)
    {
       this.delegate = delegate;
    }
    
    
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (delegate != null && intent.getAction().equals(DroneControlService.DRONE_CONFIG_STATE_CHANGED_ACTION)) {
            delegate.onDroneConfigChanged();
        }
    }

}
