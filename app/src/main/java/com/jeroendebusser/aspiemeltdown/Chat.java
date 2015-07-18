package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


/**
 * The main chat activity
 */
public class Chat extends Activity {

    /*
     * The arrayadapter for our messages
     */
    private MessageAdapter mMessages;

    /**
     * The switch determining which user the next message is from
     */
    private SwitchHelper userSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        // Set up the ListView
        mMessages = new MessageAdapter(this);
        mMessages.setNotifyOnChange(true);
        final ListView listView = (ListView) findViewById(R.id.message_view);
        listView.setAdapter(mMessages);

        userSwitch = SwitchHelper.createInstance(this,true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //mMessages.addAll("This is a \nmultiline\ntest", "testing");
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.message_edit);
        String message = editText.getText().toString();
        editText.setText("");
        mMessages.add(new Message(message,userSwitch.checked()));
        //TODO: check for user preference
        userSwitch.toggle();
    }
}
