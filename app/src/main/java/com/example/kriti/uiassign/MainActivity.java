package com.example.kriti.uiassign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button victimBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        victimBtn = (Button) findViewById(R.id.victim_button);
        victimBtn.setOnClickListener(this);
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
}
