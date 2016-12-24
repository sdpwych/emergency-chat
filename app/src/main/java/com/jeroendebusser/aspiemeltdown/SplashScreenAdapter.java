package com.jeroendebusser.aspiemeltdown;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeroendebusser.aspiemeltdown.dao.SplashScreen;

import java.util.Collection;
import java.util.List;

/**
 * Created by Jeroen De Busser on 23/12/2016.
 */

public class SplashScreenAdapter extends ArrayAdapter<SplashScreen> {

    public SplashScreenAdapter(Context context, List<SplashScreen> objects) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, objects);
    }

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

    @Override
    public void addAll(Collection<? extends SplashScreen> c) {
        for(SplashScreen s : c) {
            add(s);
        }
    }
}
