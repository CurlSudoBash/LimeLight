/*
 * DroneServiceCommand
 * 
 * Created on: May 5, 2011
 * Author: Dmytro Baryskyy
 */

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
