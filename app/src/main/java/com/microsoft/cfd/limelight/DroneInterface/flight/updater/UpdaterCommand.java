/*
 * UpdaterState
 *
 *  Created on: May 5, 2011
 *      Author: Dmytro Baryskyy
 */

package com.microsoft.cfd.limelight.DroneInterface.flight.updater;

import android.content.Context;

import com.microsoft.cfd.limelight.DroneInterface.flight.service.listeners.DroneUpdaterListener.ArDroneToolError;

public interface UpdaterCommand 
{
	public enum UpdaterCommandId
	{
		CONNECT,
		CHECK_BOOT_LOADER,
		REPAIR_BOOTLOADER,
		CHECK_VERSION,
		CHECK_TOUCH_SCREEN, 
		START_APPLICATION,
		UPLOAD_FIRMWARE, 
		RESTART_DRONE, 
		INSTALL
	};
	
	public UpdaterCommandId getId();
	public void executeInternal(Context context);
	public String getCommandName();
	public UpdaterCommandId getNextCommandId();
	public ArDroneToolError getError();
}
