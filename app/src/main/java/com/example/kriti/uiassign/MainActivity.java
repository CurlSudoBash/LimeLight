package com.example.kriti.uiassign;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        WifiP2pManager.ChannelListener {

    Button victimBtn;
    public static final String TAG = "wifidirect";
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;

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

        Log.d("Mah Id", Utils.deviceId);

        victimBtn = (Button) findViewById(R.id.victim_button);
        victimBtn.setOnClickListener(this);

        Intent locationIntent = new Intent(this, com.example.kriti.uiassign.Services.LocationService.class);
        //startService(locationIntent);

        Intent relayIntent = new Intent(this, com.example.kriti.uiassign.Services.RelayService.class);
        startService(relayIntent);

        Intent apiIntent = new Intent(this, com.example.kriti.uiassign.Services.ServerSyncService.class);
        startService(apiIntent);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastVictim(View v) {

        toastMsg("You are a victim now");

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
