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

    List<SplashScreen> screens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
        dao = Dao.getSession(this).getSplashScreenDao();
        screens = dao.loadAll();
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
            dao.insert(new SplashScreen(null,"foo","testing"));
            screens = dao.loadAll();
            openSplash(0);
        }

        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.screens);
        ArrayAdapter adapter = new ArrayAdapter<SplashScreen>(this, android.R.layout.simple_list_item_2, android.R.id.text1, screens) {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(this.getItem(position).getTitle());
                String splash = this.getItem(position).getText();
                int max_length = 40;
                if(splash.length() > max_length) {
                    splash = splash.substring(0,max_length) + "...";
                }
                text2.setText(splash);

                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openSplash(position);
    }

    private void openSplash(int id) {
        Intent next = new Intent().setClass(Main.this,Splash.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        next.putExtra(Splash.SPLASH_ID,screens.get(id).getId());
        next.putExtra(Splash.SETTINGS_SHOW,screens.size() == 1);
        startActivity(next);
    }

    public void openSettings(View view) {
        Intent next = new Intent().setClass(Main.this, SettingsChooserActivity.class);
        startActivity(next);
    }
}
