package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DroneReadyReceiver extends BroadcastReceiver {

	private DroneReadyReceiverDelegate delegate;

	public DroneReadyReceiver(DroneReadyReceiverDelegate delegate)
	{
		this.delegate = delegate;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		if (delegate != null) {
			delegate.onDroneReady();
		}
	}

}
