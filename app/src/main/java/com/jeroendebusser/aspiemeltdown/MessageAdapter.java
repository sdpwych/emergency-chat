package com.jeroendebusser.aspiemeltdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jeroendebusser.aspiemeltdown.dao.Message;

/**
 * Created by Jeroen De Busser on 28/11/2014.
 */
public class MessageAdapter extends ArrayAdapter<Message> {
    private Context c;
    public MessageAdapter(Context context) {
        super(context,R.layout.user0_message);
        c = context;
    }

    @Override
    public int getItemViewType(int position) {
        return (getItem(position).getUser()) ? R.layout.user1_message : R.layout.user0_message;
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
            v = vi.inflate(getItemViewType(position), null);
        }
        String s = getItem(position).getMessage();
        if (s != null) {
            TextView t = (TextView) v.findViewById(R.id.message);
            if (t != null) t.setText(s);
        }
        return v;
    }
}
