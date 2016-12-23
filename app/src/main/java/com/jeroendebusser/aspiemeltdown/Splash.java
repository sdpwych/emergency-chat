package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreen;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

public class Splash extends Activity {

    public static final String SPLASH_ID = "SPLASH_ID";
    public static final String SETTINGS_SHOW = "SETTINGS_SHOW";

    SplashScreen screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        long key = this.getIntent().getLongExtra(SPLASH_ID,0);
        screen = Dao.getSession(this).getSplashScreenDao().load(key);
        boolean show_settings = this.getIntent().getBooleanExtra(SETTINGS_SHOW,true);
        if(!show_settings) {
            View settings_button = findViewById(R.id.settingsButton);
            ((ViewGroup)settings_button.getParent()).removeView(settings_button);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        TextView text_h = (TextView) findViewById(R.id.splash_header);
        text_h.setText(screen.getTitle());

        TextView text = (TextView) findViewById(R.id.splash_message);
        text.setText(screen.getText());

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int textsize = Integer.parseInt(pref.getString(SettingsActivity.KEY_PREF_SIZE,"26"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
    }

    public void Continue(View view) {
        Intent next = new Intent().setClass(Splash.this,Chat.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(next);
    }

    public void openSettings(View view) {
        Intent next = new Intent().setClass(Splash.this, SettingsChooserActivity.class);
        startActivity(next);
    }
}
