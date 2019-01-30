package com.microsoft.cfd.limelight;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.R;
import com.microsoft.cfd.limelight.Retrofit.RetrofitModule;

public class AssignActivity extends AppCompatActivity {

    TextView scouts_count;
    TextView lifters_count;
    TextView medics_count;
    TextView victims_count;
    TextView disaster_name;
    Button assign;
    String location;

    public void makeToast(String message) {
        Toast.makeText(AssignActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_info);
        Intent intent = getIntent();
        location = intent.getStringExtra("Location");

        scouts_count = (TextView) findViewById(R.id.scouts_count);
        lifters_count = (TextView) findViewById(R.id.lifters_count);
        medics_count = (TextView) findViewById(R.id.medics_count);
        victims_count = (TextView) findViewById(R.id.victims_count);
        disaster_name = (TextView) findViewById(R.id.disaster_name);

        disaster_name.setText(Utils.currentDisaster);

        assign = (Button) findViewById(R.id.assign_yourself_button);

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.assigned) {
                    makeToast("You are already assigned");
                    return;
                }
                TextView text = victims_count;

                switch (Utils.role) {
                    case "M":
                        text = medics_count;
                        break;
                    case "L":
                        text = lifters_count;
                        break;
                    case "S":
                        text = scouts_count;
                        break;
                }

                int count = Integer.parseInt(text.getText().toString());
                text.setText(Integer.toString(count+1));
                new assignment().execute();
                Utils.assigned = true;
                makeToast("You are assigned now");
            }
        });

        new fetchInfo().execute();
    }

    @SuppressLint("StaticFieldLeak")
    public class fetchInfo extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            return RetrofitModule.fetchClusterInfo(location);
        }

        @Override
        protected void onPostExecute( final String response ) {
            if(response.equals("")) return;
            String[] data = response.split("_");
            victims_count.setText(data[0]);
            scouts_count.setText(data[1]);
            medics_count.setText(data[2]);
            lifters_count.setText(data[3]);
            makeToast("Updated Cluster Information");
        }
    }

    @SuppressLint("StaticFieldLeak")
    public class assignment extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            RetrofitModule.updateCluster(location, Utils.role);
            return null;
        }

    }

   public void requestReinforcements(View view) {
        Intent intent = new Intent(this, RequestReinforcements.class);
        startActivity(intent);
    } 


}
