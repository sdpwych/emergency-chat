package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        setContentView(R.layout.activity_splash);
        View next = findViewById(R.id.splash_layout);
        next.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSettings();
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String splash = pref.getString(SettingsActivity.KEY_PREF_SPLASH,"");
        TextView text = (TextView) findViewById(R.id.splash_message);
        text.setText(splash);
        String splash_h = pref.getString(SettingsActivity.KEY_PREF_HEADER,"");
        TextView text_h = (TextView) findViewById(R.id.splash_header);
        text_h.setText(splash_h);
        Context context = getApplicationContext();
        CharSequence toastText = getString(R.string.toastText);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, toastText, duration);
        toast.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            openSettings();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Continue(View view) {
        Intent next = new Intent().setClass(Splash.this,Chat.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(next);
    }

    private void openSettings() {
        Intent next = new Intent().setClass(Splash.this, SettingsActivity.class);
        startActivity(next);
    }
}
