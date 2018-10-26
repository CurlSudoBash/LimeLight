package com.microsoft.cfd.limelight.beans;


public class Events {
    public String eventName;
    public String location;
    public int image_location;
    public String areaCoordinator;
    public String disasterType;

    public Events(String eventName, String location, int image_location, String areaCoordinator, String disasterType) {
        this.eventName = eventName;
        this.location = location;
        this.image_location = image_location;
        this.areaCoordinator = areaCoordinator;
        this.disasterType = disasterType;
    }

    public String getEventName() {
        return eventName;
    }
    public String getLocation() { return location; }
    public int getImageLocation() { return image_location; }
    public String getAreaCoordinator() { return areaCoordinator; }
    public String getDisasterType() { return disasterType; }
}
