package com.microsoft.cfd.limelight;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class SafeMarkedActivity extends AppCompatActivity {

    ImageView mImgCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_marked);

        mImgCheck = (ImageView) findViewById(R.id.imageView);
        ((Animatable) mImgCheck.getDrawable()).start();
    }
}
