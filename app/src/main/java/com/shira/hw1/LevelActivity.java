package com.shira.hw1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LevelActivity extends Activity {
    private Intent gameIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);


        Bundle extras = getIntent().getExtras();

//        String playerAge = extras.getString("my_age");


        gameIntent = new Intent(LevelActivity.this, GameActivity.class);
        //((EditText)findViewById(R.id.edit_text_name)).getText().toString()


        Button easyLevel = (Button)findViewById(R.id.button_easy);
        Button mediumLevel = (Button)findViewById(R.id.button_medium);
        Button hardLevel = (Button)findViewById(R.id.button_hard);

        ((TextView)findViewById(R.id.text_view_player_name)).setText(extras.getString("my_name"));
        ((TextView)findViewById(R.id.text_view_player_age)).setText(extras.getString("my_age"));

        easyLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gameIntent.putExtra("level",1);
                gameIntent.putExtra("my_name", ((TextView)findViewById(R.id.text_view_player_name)).getText().toString());
                startActivity(gameIntent);
            }
        });

        mediumLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameIntent.putExtra("level",2);
                gameIntent.putExtra("my_name", ((TextView)findViewById(R.id.text_view_player_name)).getText().toString());
                startActivity(gameIntent);
            }
        });

        hardLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameIntent.putExtra("level",3);
                gameIntent.putExtra("my_name", ((TextView)findViewById(R.id.text_view_player_name)).getText().toString());
                startActivity(gameIntent);
            }
        });

    }
//    @Override
//    public void onStart(){
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_levels, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    }

}
