package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.cfd.limelight.Retrofit.RetrofitModule;

import okhttp3.internal.Util;
import com.R;
public class RequestReinforcements extends AppCompatActivity {

    EditText scouts;
    EditText medics;
    EditText lifters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_reinforcements);
        setupUI();
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitModule.requestReinforcements(
                        scouts.getText().toString(),
                        medics.getText().toString(),
                        lifters.getText().toString()
                );
                Toast.makeText(
                        RequestReinforcements.this,
                        "Request Successful",
                        Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
//                startActivity(intent);
            }
        });
    }

    public void setupUI()
    {
        scouts = (EditText) findViewById(R.id.numberpick_scouts);
        medics = (EditText) findViewById(R.id.numberpick_medics);
        lifters = (EditText) findViewById(R.id.numberpick_lifters);
    }
}