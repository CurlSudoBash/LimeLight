package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

public interface DroneFirmwareCheckReceiverDelegate 
{
	public void onFirmwareChecked(boolean updateRequired);
}
