package com.microsoft.cfd.limelight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class RequestReinforcements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_reinforcements);
        setupUI();
    }

    public void setupUI()
    {

        EditText np1 = (EditText) findViewById(R.id.numberpick_scouts);
        EditText np2 = (EditText) findViewById(R.id.numberpick_medics);
        EditText np3 = (EditText) findViewById(R.id.numberpick_lifters);
    }
}