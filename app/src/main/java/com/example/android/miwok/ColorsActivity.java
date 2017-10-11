package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class ColorsActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorsFragment())
                .commit();
    }
}



