package com.shira.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        Bundle extras = getIntent().getExtras();
        String playerName = extras.getString("my_name");
        String playerAge = extras.getString("my_age");

        ((TextView)findViewById(R.id.text_view_hello_player)).setText("Hello " + playerName.toString());
        ((TextView)findViewById(R.id.text_view_hello_age)).setText("Your age " + playerAge);

    }

}
