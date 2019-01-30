package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

public interface DroneConnectionChangeReceiverDelegate 
{
	public void onDroneConnected();
	public void onDroneDisconnected();
}
