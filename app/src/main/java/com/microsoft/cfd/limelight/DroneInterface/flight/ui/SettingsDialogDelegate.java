package com.microsoft.cfd.limelight.DroneInterface.flight.ui;

import com.microsoft.cfd.limelight.DroneInterface.flight.activities.SettingsDialog;
import com.microsoft.cfd.limelight.DroneInterface.flight.settings.ApplicationSettings.EAppSettingProperty;

public interface SettingsDialogDelegate
{
    /**
     * Called when dialog is going to be shown.
     * @param dialog - instance of the dialog that is going to be shown.
     */
    public void prepareDialog(SettingsDialog dialog);
    
    /**
     * Called when dialog is dismissed.
     * @param settingsDialog - instance of the dialog
     */
    public void onDismissed(SettingsDialog settingsDialog);
    
    /**
     * Called when application property is changed by the user from settings screen.
     * @param dialog - instance of the dialog.
     * @param property - one of the EAppSettingProperty values.
     * @param value - value of the property.
     */
    public void onOptionChangedApp(SettingsDialog dialog, EAppSettingProperty property, Object value);
}
