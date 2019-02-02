package com.microsoft.cfd.limelight.Controllers;

import android.app.ProgressDialog;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;

import com.microsoft.cfd.limelight.MainActivity;
import com.microsoft.cfd.limelight.WiFiRelayProvider;

import java.util.ArrayList;
import java.util.List;

public class PeerController {

    public static List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();
    ProgressDialog progressDialog = null;
    private WifiP2pDevice device;

    public WifiP2pDevice getDevice() {
        return device;
    }


    public static PeerListListener peerListListener = new PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {
                peers.clear();
                peers.addAll(peerList.getDeviceList());
                // Perform any other updates needed based on the new list of
                // peers connected to the Wi-Fi P2P network.
            if (peers.size() == 0) {
                Log.d(MainActivity.TAG, "No devices found");
                return;
            }

            WiFiRelayProvider.connect();
        }
    };

}
