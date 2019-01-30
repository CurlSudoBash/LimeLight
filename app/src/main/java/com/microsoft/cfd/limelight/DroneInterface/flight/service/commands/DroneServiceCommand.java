

package com.microsoft.cfd.limelight.DroneInterface.flight.service.commands;

import com.microsoft.cfd.limelight.DroneInterface.flight.service.DroneControlService;

public abstract class DroneServiceCommand
{
    protected DroneControlService context;


    public DroneServiceCommand(DroneControlService context)
    {
        this.context = context;
    }


    public abstract void execute();

}
