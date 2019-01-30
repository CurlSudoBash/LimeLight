

package com.microsoft.cfd.limelight.DroneInterface.flight.updater.commands;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;


import com.microsoft.cfd.limelight.DroneInterface.flight.drone.DroneConfig;
import com.microsoft.cfd.limelight.DroneInterface.flight.ui.ConnectScreenViewController.IndicatorState;
import com.microsoft.cfd.limelight.DroneInterface.flight.updater.UpdateManager;
import com.microsoft.cfd.limelight.DroneInterface.flight.utils.FTPUtils;
import com.microsoft.cfd.limelight.DroneInterface.flight.utils.TelnetUtils;
import com.microsoft.cfd.limelight.R;

public class UpdaterRestartDroneCommand 
	extends UpdaterCommandBase
{
	private String ssid;
	private boolean droneRestarted; 

	
	public UpdaterRestartDroneCommand(UpdateManager context) {
		super(context);
	}

	
	@Override
	public void execute(Context service) 
	{
        delegate.setRestartingDroneState(IndicatorState.ACTIVE, 0, "");
		
		WifiManager wifi = (WifiManager) context.getContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifi.getConnectionInfo();

		ssid = wifiInfo.getSSID();
		context.setDroneNetworkSSID(ssid);
		droneRestarted = false;
		
		Log.d(getCommandName(), "Current connection ssid: " + ssid);
		
		// We need this in order to prevent disconnection due to device inactivity
		WifiLock lock = wifi.createWifiLock("DRONE_RESTART_LOCK");
		lock.acquire();
		
		if (context.getDroneFirmwareVersion().startsWith("2.")) {
			if (!TelnetUtils.executeRemotely(DroneConfig.getHost(), DroneConfig.TELNET_PORT, "reboot\n")) {
		        delegate.setRestartingDroneState(IndicatorState.ACTIVE, 0, context.getContext().getString(R.string.update_file_sent_successfully_please_restart_drone));
			}
		} else {
	        delegate.setRestartingDroneState(IndicatorState.ACTIVE, 0, context.getContext().getString(R.string.update_file_sent_successfully_please_restart_drone));
		}
		
		while (!context.isShuttingDown()) {
		
			if (!droneRestarted) {	
				if (FTPUtils.downloadFile(service, DroneConfig.getHost(), DroneConfig.getFtpPort(), "version.txt") == null) {
					droneRestarted = true;
					Log.d(getCommandName(), "Wifi signal lost. Marking as restarted");
					onSuccess();
					break;
				} else {
					Log.d(getCommandName(), "Wifi still enabled");
				}
			} 

			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}

			Log.d(getCommandName(), "Checking connection...");
		}
		
		lock.release();
	}


	private void onSuccess() 
	{
        delegate.setRestartingDroneState(IndicatorState.PASSED, 100, "");
	}


	public UpdaterCommandId getId() 
	{
		return UpdaterCommandId.RESTART_DRONE;
	}

	
	public UpdaterCommandId getNextCommandId() 
	{
		return UpdaterCommandId.INSTALL;
	}
	
}
