package com.example.kriti.uiassign;

import android.location.Location;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static String deviceId;

    public static String location;

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
        Utils.locationMap.put(Utils.deviceId, Utils.location);
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

    public static void updateMap(String response) {
        if(response.length() == 0) return;
        response = response.substring(0, response.length()-1);
        String[] entries = response.split(",");
        for(String entry: entries) {
            String[] temp = entry.split("|");
            Utils.locationMap.put(temp[0],temp[1]);
        }
    }

    public static String locationToString(Location location) {
        return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + "_" + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
    }

}
