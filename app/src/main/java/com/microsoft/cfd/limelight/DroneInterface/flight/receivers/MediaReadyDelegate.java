package com.microsoft.cfd.limelight.DroneInterface.flight.receivers;

import java.io.File;

public interface MediaReadyDelegate
{
    public void onMediaReady(File mediaFile);
}
