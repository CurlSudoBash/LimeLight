package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

public interface DroneAvailabilityDelegate 
{
	public void onDroneAvailabilityChanged(boolean isDroneOnNetwork);
}
