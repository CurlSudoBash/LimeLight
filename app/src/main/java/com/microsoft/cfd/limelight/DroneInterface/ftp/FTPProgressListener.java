/*
 * FTPProgressListener
 *
 *  Created on: Sep 1, 2011
 *      Author: Dmytro Baryskyy
 */

package com.microsoft.cfd.limelight.DroneInterface.ftp;

import com.microsoft.cfd.limelight.DroneInterface.ftp.FTPClientStatus.FTPStatus;

public interface FTPProgressListener 
{
	/**
	 * Called when FTP progress has changed.
	 * @param status FTPStatus.FTP_PROGRESS of another FTPStatus value.
	 * @param progress - value from 0 to 1 that shows operation progress (only when status equals to FTP_PROGRESS)
	 * @param operation - current operation (FTP_PUT or FTP_GET)
	 */
	public void onStatusChanged(FTPStatus status, float progress, FTPOperation operation);
}
