package com.example.kriti.uiassign.beans;

/**
 * Created by kriti on 24/10/18.
 */

public class Events {
    private String eventName;
    private String location;
    private int image_location;
    private String areaCoordinator;
    private String disasterType;

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
