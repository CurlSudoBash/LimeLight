/*
 * UpdaterRepairBootloaderCommand
 *
 *  Created on: Jul 27, 2011
 *      Author: Dmytro Baryskyy
 */

package com.microsoft.cfd.limelight.DroneInterface.flight.updater.commands;

import android.content.Context;

import com.microsoft.cfd.limelight.DroneInterface.flight.R;
import com.microsoft.cfd.limelight.DroneInterface.flight.drone.DroneConfig;
import com.microsoft.cfd.limelight.DroneInterface.flight.ui.ConnectScreenViewController.IndicatorState;
import com.microsoft.cfd.limelight.DroneInterface.flight.updater.UpdateManager;
import com.microsoft.cfd.limelight.DroneInterface.flight.updater.utils.FirmwareConfig;
import com.microsoft.cfd.limelight.DroneInterface.flight.utils.FTPUtils;
import com.microsoft.cfd.limelight.DroneInterface.flight.utils.TelnetUtils;

public class UpdaterRepairBootloaderCommand 
	extends UpdaterCommandBase
{
	
	private UpdaterCommandId nextCommand = UpdaterCommandId.UPLOAD_FIRMWARE;

	public UpdaterRepairBootloaderCommand(UpdateManager context) 
	{
		super(context);
	}
	
	
	@Override
	public void execute(Context service) 
	{
        delegate.setCheckingRepairingState(IndicatorState.ACTIVE, 50, "");
		
		FirmwareConfig config = this.context.getFirmwareConfig();
		String repairFilename = config.getRepairFileName();
		String bootldrFilename = config.getBootldrFileName();

		if (!uploadBootldr(service, bootldrFilename)) {
			onFailure(service);
		} else if (!uploadRepair(service, repairFilename)) {
			onFailure(service);
		} else if (!modifyAccessRights(repairFilename)) {
			onFailure(service);
		} else if (!repair(repairFilename)) {
			onFailure(service);
		} else {
		    delegate.setCheckingRepairingState(IndicatorState.PASSED, 100, "");
		}
	}


	public UpdaterCommandId getId() 
	{
		return UpdaterCommandId.REPAIR_BOOTLOADER;
	}

	
	public UpdaterCommandId getNextCommandId() 
	{
		return nextCommand;
	}
	
	
	private boolean uploadBootldr(Context context, String bootldrFilename)
	{
		boolean result = FTPUtils.uploadFileSync(context, 
				DroneConfig.getHost(),
				DroneConfig.REPAIR_FTP_PORT, 
				"firmware/" + bootldrFilename, 
				bootldrFilename);
		
		return result;
	}
	
	
	private boolean uploadRepair(Context context, String repairFilename)
	{
		boolean result = FTPUtils.uploadFileSync(context, 
				DroneConfig.getHost(),
				DroneConfig.REPAIR_FTP_PORT, 
				"firmware/" + repairFilename, 
				repairFilename);
		
		return result;
	}
	
	
	private boolean modifyAccessRights(String repairFilename)
	{
		String command = "cd `find /data -name \""+repairFilename+"\" -exec dirname {} \\;` && chmod 755 "+repairFilename+"\n";
		
		return TelnetUtils.executeRemotely(DroneConfig.getHost(), DroneConfig.TELNET_PORT, command);
	}
	
	
	private boolean repair(String repairFilename)
	{
		String command = "cd `find /data -name \""+repairFilename+"\" -exec dirname {} \\;` && ./"+repairFilename+"\n";
		
		return TelnetUtils.executeRemotely(DroneConfig.getHost(), DroneConfig.TELNET_PORT, command);
	}
	
	
	private void onFailure(Context context)
	{
        delegate.setCheckingRepairingState(IndicatorState.FAILED, 100, context.getString(R.string.wifi_not_available_please_connect_device_to_drone));
	    
		nextCommand = null;
	}

}
