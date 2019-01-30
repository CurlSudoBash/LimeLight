/*
 * PauseCommand
 * 
 * Created on: May 5, 2011
 * Author: Dmytro Baryskyy
 */

package com.microsoft.cfd.limelight.DroneInterface.flight.service.commands;

import com.microsoft.cfd.limelight.DroneInterface.flight.drone.DroneProxy;
import com.microsoft.cfd.limelight.DroneInterface.flight.service.DroneControlService;

public class PauseCommand extends DroneServiceCommand
{

    private DroneProxy droneProxy;


    public PauseCommand(DroneControlService context)
    {
        super(context);
        droneProxy = DroneProxy.getInstance(context.getApplicationContext());
    }


    @Override
    public void execute()
    {
        droneProxy.doPause();

        context.onCommandFinished(this);
    }

}
