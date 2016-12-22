package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreen;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

import java.util.List;

/**
 * Created by Jeroen De Busser on 22/12/2016.
 */

public class Main extends Activity {

    SplashScreenDao dao;

    Long splash_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        dao = Dao.getSession(this).getSplashScreenDao();
        List<SplashScreen> screens = dao.loadAll();
        if(screens.size() == 0) {
            //Insert default splash screen
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
            String splash = pref.getString(SettingsActivity.KEY_PREF_SPLASH,null);
            String title = pref.getString(SettingsActivity.KEY_PREF_HEADER,null);
            if(splash == null) {
                title = getString(R.string.meltdown_warning);
                splash = getString(R.string.splash);
            } else {
                //Remove preferences
                pref.edit().remove(SettingsActivity.KEY_PREF_HEADER).remove(SettingsActivity.KEY_PREF_SPLASH).apply();
            }
            SplashScreen screen = new SplashScreen(null,title,splash);
            splash_id = dao.insert(screen);
        } else {
            splash_id = dao.getKey(screens.get(0));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent next = new Intent().setClass(Main.this,Splash.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        next.putExtra(Splash.SPLASH_ID,splash_id);
        startActivity(next);
    }
}
