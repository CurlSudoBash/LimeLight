package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

public interface DroneVideoRecordStateReceiverDelegate 
{
	public void onDroneRecordVideoStateChanged(boolean recording, boolean usbActive, int remainingTime);
}
