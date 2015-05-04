package com.yangtech.marsstory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.yangtech.marsstory.R;


public class StoryActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.main_key_name));

        if (name == null) {
            name = "Friend";
        }

        Toast.makeText(this, name, Toast.LENGTH_LONG).show();
    }


}