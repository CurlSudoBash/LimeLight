package com.microsoft.cfd.limelight.DroneInterface.flight.updater;

import com.microsoft.cfd.limelight.DroneInterface.flight.ui.ConnectScreenViewController.IndicatorState;

public interface UpdaterDelegate
{
    public void setCheckingRepairingState(IndicatorState state, int progress, String message);
    public void setSendingFileState(IndicatorState state, int progress, String message);
    public void setRestartingDroneState(IndicatorState state, int progress, String message);
    public void setInstallingState(IndicatorState state, int progress, String message);
    public void onFinished();
}
