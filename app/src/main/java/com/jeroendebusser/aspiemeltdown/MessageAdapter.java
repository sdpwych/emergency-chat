package com.jeroendebusser.aspiemeltdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jeroen De Busser on 28/11/2014.
 */
public class MessageAdapter extends ArrayAdapter<String> {
    private Context c;
    public MessageAdapter(Context context) {
        super(context,R.layout.user0_message);
        c = context;
    }

    @Override
    public int getItemViewType(int position) {
        return (position % 2 == 0) ? R.layout.user0_message : R.layout.user1_message;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(position % 2 == 0) {
                v = vi.inflate(R.layout.user0_message, null);
            } else {
                v = vi.inflate(R.layout.user1_message, null);
            }
        }
        String s = getItem(position);
        if (s != null) {
            TextView t = (TextView) v.findViewById(R.id.message);
            if (t != null) t.setText(s);
        }
        return v;
    }
}
