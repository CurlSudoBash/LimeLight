package com.microsoft.cfd.limelight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.parrot.freeflight.drone.DroneConfig;
import com.parrot.freeflight.utils.FTPUtils;
import com.parrot.freeflight.utils.ProgressListener;
import com.parrot.ftp.FTPClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import okhttp3.internal.Util;

import static android.support.constraint.Constraints.TAG;

public class DroneReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null && info.isConnected()) {

            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            Log.d("Drone", ssid);

            if(ssid.contains("ardrone")) {
                String host = DroneConfig.getHost();
                int port = DroneConfig.getFtpPort();

                File tempFile = null;
                FTPClient ftpClient = null;
                try {

                    ftpClient = new FTPClient();

                    File saveToDir = context.getExternalCacheDir();

                    if (saveToDir == null) {
                        saveToDir = context.getCacheDir();
                    }

                    /* Location Data Synchronizing Begins */

                    String LocationData = FTPUtils.downloadFile(
                            context,
                            host,
                            port,
                            "data.txt"
                    );

                    Log.d("Drone Data", LocationData);

                    if(LocationData != null ) {
                        Utils.updateMap(LocationData);
                    }

                    File payload = new File(context.getFilesDir(), "data.txt");

                    try {
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("data.txt", Context.MODE_WORLD_READABLE));
                        outputStreamWriter.write(Utils.mapToPayload(Utils.locationMap));
                        outputStreamWriter.close();
                    } catch (Exception e) {
                        Log.e("Drone", e.toString());
                    }

                    if (!ftpClient.connect(host, port)) {
                        Toast.makeText(
                                context.getApplicationContext(),
                                "Cannot connect to Drone " + ssid,
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    boolean result = ftpClient.putSync(payload.getAbsolutePath(),"data.txt");

                    if(result) {
                        Log.d("Drone", "Successfully Uploaded");
                    }

                    Toast.makeText(
                            context.getApplicationContext(),
                            "Synchronized Location Data With Drone " + ssid,
                            Toast.LENGTH_LONG
                    ).show();

                    /* Location Data Synchronizing Ends */

                } finally {
                    if (tempFile != null && tempFile.exists()) {
                        if (!tempFile.delete()) {
                            Log.w(TAG, "Can't delete temp file " + tempFile.getAbsolutePath());
                        }
                    }

                    if (ftpClient != null && ftpClient.isConnected()) {
                        ftpClient.disconnect();
                        ftpClient = null;
                    }
                }
            }
        }
    }
}
