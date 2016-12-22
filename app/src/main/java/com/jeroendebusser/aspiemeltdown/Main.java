package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

/**
 * Created by Jeroen De Busser on 22/12/2016.
 */

public class Main extends Activity {

    SplashScreenDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        dao = Dao.getSession(this).getSplashScreenDao();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent next = new Intent().setClass(Main.this,Splash.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(next);
    }
}
