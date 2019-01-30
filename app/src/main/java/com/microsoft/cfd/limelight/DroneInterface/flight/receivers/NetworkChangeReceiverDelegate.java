package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

import android.net.NetworkInfo;


public interface NetworkChangeReceiverDelegate 
{
	public void onNetworkChanged(NetworkInfo info);
}
