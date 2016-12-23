package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Jeroen De Busser on 23/12/2016.
 */

public class SettingsChooserActivity extends Activity implements AdapterView.OnItemClickListener {

    private static final int SCREENS_SETTINGS = 0;
    private static final int MISC_SETTINGS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_chooser);

        ListView list = (ListView) findViewById(R.id.settingsChooserList);
        list.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == SCREENS_SETTINGS) {
            //TODO
        } else if(position == MISC_SETTINGS) {
            Intent next = new Intent().setClass(SettingsChooserActivity.this, SettingsActivity.class);
            startActivity(next);
        }
    }
}
