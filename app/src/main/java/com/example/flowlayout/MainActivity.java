package com.example.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
//        Log.e("zangdianbin", "Activity onAttachedToWindow");
    }

}
