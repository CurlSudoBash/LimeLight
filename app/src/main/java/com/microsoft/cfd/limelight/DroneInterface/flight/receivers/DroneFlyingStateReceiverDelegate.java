package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

public interface DroneFlyingStateReceiverDelegate 
{
    /**
     * Called when drone starts flying or lands
     * @param flying - true if it started to fly, or false otherwise.
     */
	public void onDroneFlyingStateChanged(boolean flying);
}
