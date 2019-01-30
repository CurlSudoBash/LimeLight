

package com.microsoft.cfd.limelight.DroneInterface.flight.drone;

public interface DroneProxyListener 
{
	public void onToolConnected();
	public void onToolConnectionFailed(int reason);
	public void onToolDisconnected();
	public void onConfigChanged();
}
