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

        NumberPicker np1 = (NumberPicker) findViewById(R.id.numberpick_scouts);
        NumberPicker np2 = (NumberPicker) findViewById(R.id.numberpick_medics);
        NumberPicker np3 = (NumberPicker) findViewById(R.id.numberpick_lifters);

        np1.setMaxValue(100);
        np1.setMinValue(0);
        np2.setMaxValue(100);
        np2.setMinValue(0);
        np3.setMaxValue(100);
        np3.setMinValue(0);
    }
}