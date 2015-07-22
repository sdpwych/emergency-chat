package com.jeroendebusser.aspiemeltdown;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.jeroendebusser.aspiemeltdown.R;

public class SettingsActivity extends PreferenceActivity {

    public static final String KEY_PREF_SPLASH = "pref_splash";
    public static final String KEY_PREF_HEADER = "pref_splash_header";
    public static final String KEY_PREF_SIZE = "pref_text_size";
    public static final String KEY_SWITCH = "pref_switch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
