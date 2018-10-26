package com.microsoft.cfd.limelight.RelaySocketHandlers;

import android.os.AsyncTask;
import android.util.Log;

import com.microsoft.cfd.limelight.Utils;
import com.microsoft.cfd.limelight.MainActivity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class RelayServer extends AsyncTask<Void, Void, String> {

    @Override
    protected String doInBackground(Void... params) {
        try {
            ServerSocket serverSocket = new ServerSocket(8988);
            Log.d(MainActivity.TAG, "Server: Socket opened");
            Socket client = serverSocket.accept();
            Log.d(MainActivity.TAG, "Server: connection done");

            InputStream inputstream = client.getInputStream();

            String response = Utils.streamToString(inputstream, Charset.defaultCharset());

            Log.d("Finally Server", response);

            Utils.updateMap(response);

            client = serverSocket.accept();

            OutputStream out = client.getOutputStream();

            String payload = Utils.mapToPayload(Utils.locationMap);

            InputStream is = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8));

            Utils.copyLocation(is, out);

            serverSocket.close();
            return "/";
        } catch (IOException e) {
            Log.e(MainActivity.TAG, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (result != null) {
            Log.d("FIN", "Location Sent - " + result);
        }

    }

    @Override
    protected void onPreExecute() {
        Log.d("INIT","Opening a server socket");
    }

}