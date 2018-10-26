package com.microsoft.cfd.limelight.RelaySocketHandlers;

import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.cfd.limelight.Utils;
import com.microsoft.cfd.limelight.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RelayClient extends AsyncTask<Void, Void, String> {

    private static final int SOCKET_TIMEOUT = 5000;
    public String owner_address;
    public int owner_port;

    public RelayClient(String group_owner_address, int group_owner_port) {
        this.owner_address = group_owner_address;
        this.owner_port = group_owner_port;
    }

    @Override
    protected String doInBackground(Void ...params) {

        String host = owner_address;
        int port = owner_port;
        Socket socket = new Socket();

        String payload = Utils.mapToPayload(Utils.locationMap);

        try {
            Log.d(MainActivity.TAG, "Opening client socket - ");
            socket.bind(null);
            socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);

            Log.d(MainActivity.TAG, "Client socket - " + socket.isConnected());
            OutputStream stream = socket.getOutputStream();

            InputStream is = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8));
            Utils.copyLocation(is, stream);

            socket = new Socket();
            socket.bind(null);
            socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);

            Log.d(MainActivity.TAG, "New Client socket - " + socket.isConnected());

            InputStream loc2 = socket.getInputStream();
            String response = Utils.streamToString(loc2, Charset.defaultCharset());
            Log.d("Finally Client", response);

            Utils.updateMap(response);

            Log.d(MainActivity.TAG, "Client: Data written");
        } catch (IOException e) {
            Log.e(MainActivity.TAG, e.getMessage());
        } finally {
            if (socket != null) {
                if (socket.isConnected()) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // Give up
                        e.printStackTrace();
                    }
                }
            }
        }
        return "";
    }

}
