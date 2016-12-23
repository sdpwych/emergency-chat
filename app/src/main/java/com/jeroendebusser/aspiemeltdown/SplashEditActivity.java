package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

/**
 * Created by Jeroen De Busser on 23/12/2016.
 */

public class SplashEditActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    SplashScreenDao dao;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_edit);

        dao = Dao.getSession(this).getSplashScreenDao();

        listView = (ListView) findViewById(R.id.screenList);

        listView.setAdapter(new SplashScreenAdapter(this,dao.loadAll()));
        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //TODO: add dialog
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: edit dialog
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: confirmation dialog for deletion
        return true;
    }
}
