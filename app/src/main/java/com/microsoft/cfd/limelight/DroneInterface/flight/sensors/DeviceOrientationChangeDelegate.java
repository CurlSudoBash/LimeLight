package com.microsoft.cfd.limelight.DroneInterface.flight.sensors;

public interface DeviceOrientationChangeDelegate
{
    public void onDeviceOrientationChanged(float[] orientation, float magneticHeading, int magnetoAccuracy);
}
