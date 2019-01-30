package com.microsoft.cfd.limelight.DroneInterface.flight.updater.receivers;

import com.microsoft.cfd.limelight.DroneInterface.flight.updater.FirmwareUpdateService.ECommand;
import com.microsoft.cfd.limelight.DroneInterface.flight.updater.FirmwareUpdateService.ECommandResult;

public interface FirmwareUpdateServiceReceiverDelegate
{
    public void onCommandStateChanged(ECommand command, ECommandResult result, int progress, String message);
}
