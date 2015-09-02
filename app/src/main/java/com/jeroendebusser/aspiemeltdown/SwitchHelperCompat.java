package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.support.v7.widget.SwitchCompat;

/**
 * Created by Jeroen De Busser on 18/07/2015.
 */
public class SwitchHelperCompat extends SwitchHelper {
    SwitchCompat s = null;

    public SwitchHelperCompat(Activity activity, boolean start) {
        this.s = (SwitchCompat) activity.findViewById(R.id.userSwitch);
        s.setChecked(start);
    }

    @Override
    public boolean checked() {
        return s.isChecked();
    }

    @Override
    public void toggle() {
        s.toggle();
    }
}
