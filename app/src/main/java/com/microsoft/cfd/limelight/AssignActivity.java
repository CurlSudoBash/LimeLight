package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AssignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_info);
        Intent intent = getIntent();
        String location = intent.getStringExtra("Location");
        Button assign = (Button) findViewById(R.id.assign_yourself_button);

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) findViewById(R.id.medics_count);
                text.setText("1");
            }
        });
    }


}
