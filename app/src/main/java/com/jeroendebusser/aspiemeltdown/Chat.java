package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.DaoSession;
import com.jeroendebusser.aspiemeltdown.dao.Message;
import com.jeroendebusser.aspiemeltdown.dao.MessageDao;


/**
 * The main chat activity
 */
public class Chat extends Activity {

    public static final String EDIT_STRING = "EDIT_STRING";
    public static final String SWITCH_STATE = "SWITCH_STATE";
    /*
             * The arrayadapter for our messages
             */
    private MessageAdapter mMessages;

    /**
     * The switch determining which user the next message is from
     */
    private SwitchHelper userSwitch;

    private boolean autoSwitch;

    private MessageDao session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = Dao.getSession(this).getMessageDao();

        setContentView(R.layout.activity_chat);

        // Set up the ListView
        mMessages = new MessageAdapter(this);
        mMessages.setNotifyOnChange(true);
        final ListView listView = (ListView) findViewById(R.id.message_view);
        listView.setAdapter(mMessages);

        if(savedInstanceState != null) {
            mMessages.addAll(session.loadAll());
            getEditText().setText(savedInstanceState.getString(EDIT_STRING));
            userSwitch = SwitchHelper.createInstance(this,savedInstanceState.getBoolean(SWITCH_STATE));
        } else {
            //Clear previous session
            session.deleteAll();
            userSwitch = SwitchHelper.createInstance(this,true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        this.autoSwitch = pref.getBoolean(SettingsActivity.KEY_SWITCH,true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //mMessages.addAll("This is a \nmultiline\ntest", "testing");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(EDIT_STRING,getEditText().getText().toString());
        outState.putBoolean(SWITCH_STATE,userSwitch.checked());

        super.onSaveInstanceState(outState);
    }

    private EditText getEditText() {
        return (EditText) findViewById(R.id.message_edit);
    }

    public void sendMessage(View view) {
        EditText editText = getEditText();
        String message = editText.getText().toString();
        editText.setText("");
        mMessages.add(new Message(message,userSwitch.checked()));
        if(this.autoSwitch) userSwitch.toggle();
    }
}
