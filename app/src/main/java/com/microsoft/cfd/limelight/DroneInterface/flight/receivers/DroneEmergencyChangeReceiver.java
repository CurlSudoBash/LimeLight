package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.microsoft.cfd.limelight.DroneInterface.flight.service.DroneControlService;

public class DroneEmergencyChangeReceiver extends BroadcastReceiver {

	private DroneEmergencyChangeReceiverDelegate delegate;
	
	public DroneEmergencyChangeReceiver(DroneEmergencyChangeReceiverDelegate delegate)
	{
		this.delegate = delegate;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		int code = intent.getIntExtra(DroneControlService.EXTRA_EMERGENCY_CODE, 0);
		
		if (delegate != null) {
			delegate.onDroneEmergencyChanged(code);
		}
	}

}
