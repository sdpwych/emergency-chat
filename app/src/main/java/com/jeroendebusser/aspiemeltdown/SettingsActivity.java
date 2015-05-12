package com.jeroendebusser.aspiemeltdown;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.jeroendebusser.aspiemeltdown.R;

public class SettingsActivity extends PreferenceActivity {

    public static final String KEY_PREF_SPLASH = "pref_splash";
    public static final String KEY_PREF_HEADER = "pref_splash_header";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
