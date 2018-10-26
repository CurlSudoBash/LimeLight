package com.example.kriti.uiassign.beans;

/**
 * Created by kriti on 24/10/18.
 */

public class Events {
    public String eventName;
    public String location;

    public Events(String eventName, String location) {
        this.eventName = eventName;
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }
}
