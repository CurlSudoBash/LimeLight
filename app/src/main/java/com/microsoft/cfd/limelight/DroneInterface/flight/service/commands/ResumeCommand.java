

package com.microsoft.cfd.limelight.DroneInterface.flight.service.commands;

import com.microsoft.cfd.limelight.DroneInterface.flight.drone.DroneProxy;
import com.microsoft.cfd.limelight.DroneInterface.flight.service.DroneControlService;

public class ResumeCommand extends DroneServiceCommand
{

    private DroneProxy droneProxy;


    public ResumeCommand(DroneControlService context)
    {
        super(context);
        droneProxy = DroneProxy.getInstance(context.getApplicationContext());
    }


    @Override
    public void execute()
    {
        droneProxy.doResume();
        context.onCommandFinished(this);
    }

}
