package com.microsoft.cfd.limelight;

import android.location.Location;
import android.util.Log;

import com.microsoft.cfd.limelight.beans.Events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static String deviceId;

    public static String location;

    public static String role = "X";

    public static List<Events> events = new ArrayList<>();

    public static Map<String, String> locationMap = new HashMap<String, String>();

    public static Map<String, String> peerTracker = new HashMap<String, String>();

    public static String streamToString(InputStream inputStream, Charset charset) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        }

        return stringBuilder.toString();
    }

    public static boolean copyLocation(InputStream inputStream, OutputStream out) {
        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                out.write(buf, 0, len);

            }
            inputStream.close();
            out.close();
        } catch (IOException e) {
            Log.d(MainActivity.TAG, e.toString());
            return false;
        }
        return true;
    }

    public static void updateLocation(Location location) {
        Utils.location = Utils.locationToString(location);
        Utils.locationMap.put(Utils.deviceId, Utils.location+"_"+Utils.role);
    }

    public static String mapToPayload(Map<String, String> locationMap) {
        String payload = "";
        for (Map.Entry<String, String> entry : locationMap.entrySet()) {
            String address = entry.getKey();
            String location = entry.getValue();
            payload = payload + address + "|" + location + ",";
        }
        return payload;
    }

    public static String listToPayload() {
        String payload = "";
        for (Events event : events) {
            payload = payload + event.eventName + "|" + event.location + ",";
        }
        return payload;
    }

    public static void updateMap(String response) {
        if(response.length() == 0) return;
        String[] entries = response.split(",");
        for(String entry: entries) {
            String[] temp = entry.split("[|]");
            Utils.locationMap.put(temp[0],temp[1]);
        }
        MapsActivity.setLocations();
    }

    public static void updateEvents(String response) {
        if(response.length() == 0) return;
        events.clear();
        String[] entries = response.split(",");
        for(String entry: entries) {
            String[] temp = entry.split("[|]");
            String[] temp2 = temp[1].split("_");
            events.add(new Events(temp[0], temp2[0]+"_"+temp2[1], R.drawable.marker, temp2[2], temp2[3]));
        }
    }

    public static String locationToString(Location location) {
        return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + "_" + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
    }

    public static void setRole(String role) {
        Utils.role = role;
        String newEntry = Utils.location + "_" + role;
        Utils.locationMap.put(Utils.deviceId, newEntry);
    }

}
