package com.shira.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.button_enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String playerName = ((EditText)findViewById(R.id.edit_text_name)).getText().toString();
                final String playerAge = ((EditText)findViewById(R.id.edit_text_age)).getText().toString();

                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                intent.putExtra("my_name", playerName);
                intent.putExtra("my_age", playerAge);

                startActivity(intent);
            }
        });
    }
}
