package com.example.kriti.uiassign;

import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.util.Log;

import com.example.kriti.uiassign.Controllers.PeerController;

public class WiFiRelayProvider {

    public static WifiP2pManager manager;
    public static Channel channel;

    public static void discover() {

        manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                Log.d("Relay Class", "Discovery Initiated");
            }

            @Override
            public void onFailure(int reasonCode) {
                Log.d("Relay Class", "Discovery Failed");
            }
        });

    }

    public static void connect() {

        for (WifiP2pDevice temp : PeerController.peers) {

            if(temp.deviceName.contains("DESKTOP") || temp.deviceName.contains("PRINTER")) continue;

            if(Utils.peerTracker.get(temp.deviceName) != null) continue;
            else Utils.peerTracker.put(temp.deviceName, "true");

            Log.d("ALL PEERS DeviceName", temp.deviceName);
            Log.d("ALL PEERS Status", Integer.toString(temp.status));

            WifiP2pConfig config = new WifiP2pConfig();
            config.deviceAddress = temp.deviceAddress;
            config.wps.setup = WpsInfo.PBC;
            config.groupOwnerIntent = 15;

            manager.connect(channel, config, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
                }

                @Override
                public void onFailure(int reason) {

                    Log.d("Relay Class", "Connect Failed, Retry");
                }
            });
        }

    }

    public static void disconnect() {
        manager.removeGroup(channel, new WifiP2pManager.ActionListener() {

            @Override
            public void onFailure(int reasonCode) {
                Log.d("Relay Class", "Disconnect failed. Reason :" + reasonCode);

            }

            @Override
            public void onSuccess() {

            }

        });
    }

}
