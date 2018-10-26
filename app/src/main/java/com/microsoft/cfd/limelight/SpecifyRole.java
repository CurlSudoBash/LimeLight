package com.microsoft.cfd.limelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class SpecifyRole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specify_role);
    }

    public void showEventList(View view) {

        Button b = (Button) view;
        String buttonText = b.getText().toString();
        Utils.setRole(buttonText.substring(0,1));
        Intent intent = new Intent(this, EventList.class);
        startActivity(intent);
    }
}
