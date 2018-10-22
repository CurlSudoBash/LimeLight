package com.example.kriti.uiassign;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public void toastRescuer(View v) {

        toastMsg("You are a rescuer now");

    }

    public void toastMedic(View v) {

        toastMsg("You are a medic now");

    }

    public void toastVictim(View v) {

        toastMsg("You are a victim now");

    }

}
