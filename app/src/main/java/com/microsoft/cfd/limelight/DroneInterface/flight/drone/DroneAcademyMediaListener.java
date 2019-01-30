package com.microsoft.cfd.limelight.DroneInterface.flight.drone;

public interface DroneAcademyMediaListener
{
    void onNewMediaIsAvailable(String path);
    void onNewMediaToQueue(String path);
    void onQueueComplete();
}
