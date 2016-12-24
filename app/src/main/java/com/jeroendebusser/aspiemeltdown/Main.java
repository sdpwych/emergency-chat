package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreen;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

import java.util.List;

/**
 * Created by Jeroen De Busser on 22/12/2016.
 */

public class Main extends Activity implements AdapterView.OnItemClickListener {

    SplashScreenDao dao;

    SplashScreenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        dao = Dao.getSession(this).getSplashScreenDao();

        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.screens);

        adapter = new SplashScreenAdapter(this,getScreens());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private List<SplashScreen> getScreens() {
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
            dao.insert(screen);
        }
        return dao.loadAll();
    }

    @Override
    public void onResume() {
        super.onResume();
        List<SplashScreen> screens = getScreens();
        adapter.clear();
        adapter.addAll(screens);
        adapter.notifyDataSetChanged();

        if(adapter.getCount() == 1) {
            openSplash(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openSplash(position);
    }

    private void openSplash(int id) {
        Intent next = new Intent().setClass(Main.this,Splash.class);
        boolean single_splash = adapter.getCount() == 1;
        if(single_splash) {
            next.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        SplashScreen screen = adapter.getItem(id);
        next.putExtra(Splash.SPLASH_ID,screen.getId());
        next.putExtra(Splash.SETTINGS_SHOW,single_splash);
        startActivity(next);
    }

    public void openSettings(View view) {
        Intent next = new Intent().setClass(Main.this, SettingsChooserActivity.class);
        startActivity(next);
    }
}
