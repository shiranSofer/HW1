package com.shira.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("my_name");
        String playerAge = extras.getString("my_age");
        Log.d("name: ", playerName);
        Log.d("age: ", playerAge);
    }

}
