package com.example.kriti.uiassign;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener {

    public static final String TAG = "wifidirect";
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;
    static public final int REQUEST_LOCATION = 1;

    private final IntentFilter intentFilter = new IntentFilter();
    private BroadcastReceiver receiver = null;


    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add necessary intent values to be matched.

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        WiFiRelayProvider.manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        WiFiRelayProvider.channel = WiFiRelayProvider.manager.initialize(this, getMainLooper(), null);

        Utils.deviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Intent locationIntent = new Intent(this, com.example.kriti.uiassign.Services.LocationService.class);
            startService(locationIntent);
        }

        Intent relayIntent = new Intent(this, com.example.kriti.uiassign.Services.RelayService.class);
        startService(relayIntent);

        Intent apiIntent = new Intent(this, com.example.kriti.uiassign.Services.ServerSyncService.class);
        startService(apiIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent locationIntent = new Intent(this, com.example.kriti.uiassign.Services.LocationService.class);
                startService(locationIntent);
             } else {
                Toast.makeText(MainActivity.this, "Please grant us location permissions to ensure your safety",Toast.LENGTH_LONG).show();
            }
        }
    }


    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastVictim(View v) {

        toastMsg("You are a victim now");
        Utils.setRole("V");
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

    }

    public void tapRescuer(View view) {
        toastMsg("You are a rescuer now");
        Intent intent = new Intent(this, SpecifyRole.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        receiver = new WiFiDirectBroadcastReceiver(
                WiFiRelayProvider.manager,
                WiFiRelayProvider.channel,
                this);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }


    public void onChannelDisconnected() {
        // we will try once more
        if (WiFiRelayProvider.manager != null && !retryChannel) {
            Toast.makeText(this, "Channel lost. Trying again", Toast.LENGTH_LONG).show();
            retryChannel = true;
            WiFiRelayProvider.manager.initialize(this, getMainLooper(), this);
        } else {
            Toast.makeText(this,
                    "Severe! Channel is probably lost permanently. Try Disable/Re-Enable P2P.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
