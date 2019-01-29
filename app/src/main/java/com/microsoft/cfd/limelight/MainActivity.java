package com.microsoft.cfd.limelight;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements WifiP2pManager.ChannelListener {

    public static final String TAG = "wifidirect";
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;
    static public final int REQUEST_LOCATION = 1;

    private final IntentFilter intentFilter = new IntentFilter();
    private BroadcastReceiver receiver = null;

    // the below data members are for the popup dialog on clicking switch
    private View popupInputDialogView = null;

    private EditText userNameEditText = null;

    private EditText passwordEditText = null;

    private Button saveUserDataButton = null;

    private Button cancelUserDataButton = null;

    private Switch isAdminSwitch = null;

    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }

    public void initPopupViewControls() {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.admin_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        userNameEditText = (EditText) popupInputDialogView.findViewById(R.id.userName);
        passwordEditText = (EditText) popupInputDialogView.findViewById(R.id.password);
        saveUserDataButton = (Button) popupInputDialogView.findViewById(R.id.button_save_user_data);
        cancelUserDataButton = (Button) popupInputDialogView.findViewById(R.id.button_cancel_user_data);
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
            Intent locationIntent = new Intent(this, com.microsoft.cfd.limelight.Services.LocationService.class);
            startService(locationIntent);
        }

        Intent relayIntent = new Intent(this, com.microsoft.cfd.limelight.Services.RelayService.class);
        startService(relayIntent);

        Intent apiIntent = new Intent(this, com.microsoft.cfd.limelight.Services.ServerSyncService.class);
        startService(apiIntent);

        // here starts the part for LimeLightV2
        isAdminSwitch = (Switch)findViewById(R.id.adminSwitch);
        isAdminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

                    alertDialogBuilder.setTitle("Sign in as Admin");
                    alertDialogBuilder.setCancelable(false);

                    initPopupViewControls();

                    alertDialogBuilder.setView(popupInputDialogView);

                    final AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    // When user click the save user data button in the popup dialog.
                    saveUserDataButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            // Get user data from popup dialog editeext.
                            String userName = userNameEditText.getText().toString();
                            String password = passwordEditText.getText().toString();

                            // Create data for the listview.
                            String[] titleArr = { "User Name", "Password"};
                            String[] dataArr = {userName, password};

                            ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;

                            int titleLen = titleArr.length;
                            for(int i =0; i < titleLen; i++) {
                                Map<String,Object> listItemMap = new HashMap<String,Object>();
                                listItemMap.put("title", titleArr[i]);
                                listItemMap.put("data", dataArr[i]);
                                itemDataList.add(listItemMap);
                            }

                            /*SimpleAdapter simpleAdapter = new SimpleAdapter(MainActivity.this,itemDataList,android.R.layout.simple_list_item_2,
                                    new String[]{"title","data"},new int[]{android.R.id.text1,android.R.id.text2});*/



                            alertDialog.cancel();
                        }
                    });

                    cancelUserDataButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.cancel();
                        }
                    });
                }
            }
        });

        Button needHelp = (Button) findViewById(R.id.needHelpButton);
        needHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });

        Button mondayEdit= (Button)findViewById(R.id.safeButton);
        mondayEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SafeMarkedActivity.class);
            startActivity(intent);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent locationIntent = new Intent(this, com.microsoft.cfd.limelight.Services.LocationService.class);
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
