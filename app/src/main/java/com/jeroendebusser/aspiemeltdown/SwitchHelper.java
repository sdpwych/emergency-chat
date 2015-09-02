package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.os.Build;

/**
 * Created by Jeroen De Busser on 18/07/2015.
 */
public abstract class SwitchHelper {
    public static SwitchHelper createInstance(Activity activity, boolean start) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return new SwitchHelperICS(activity);
        } else {
            return new SwitchHelperCompat(activity, start);
        }
    }

    public abstract boolean checked();
    public abstract void toggle();
}
