package com.jeroendebusser.aspiemeltdown;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.jeroendebusser.aspiemeltdown.dao.Dao;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreen;
import com.jeroendebusser.aspiemeltdown.dao.SplashScreenDao;

/**
 * Created by Jeroen De Busser on 23/12/2016.
 */

public class SplashEditActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    SplashScreenDao dao;
    ListView listView;
    SplashScreenAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_edit);

        dao = Dao.getSession(this).getSplashScreenDao();

        listView = (ListView) findViewById(R.id.screenList);

        adapter = new SplashScreenAdapter(this,dao.loadAll());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.splash_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(super.onOptionsItemSelected(item)) {
            return true;
        } else if(item.getItemId() == R.id.action_add_splash) {
            showEditDialog(new SplashScreen());
            return true;
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO: add dialog
        showEditDialog(adapter.getItem(position));
    }

    private void showEditDialog(final SplashScreen current) {
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.splash_edit_dialog, (ViewGroup) findViewById(android.R.id.content), false);
        // Set up the input
        final EditText title = (EditText) viewInflated.findViewById(R.id.title_edit);
        title.setText(current.getTitle());
        final EditText text = (EditText) viewInflated.findViewById(R.id.splash_edit);
        text.setText(current.getText());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.edit_title)
                .setView(viewInflated)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                current.setTitle(title.getText().toString());
                current.setText(text.getText().toString());
                addOrUpdateScreen(current);
            }
        }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void addOrUpdateScreen(SplashScreen screen) {
        dao.insertOrReplace(screen);
        reloadAdapter();
    }

    private void reloadAdapter() {
        adapter.clear();
        adapter.addAll(dao.loadAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final SplashScreen screen = adapter.getItem(position);
        new AlertDialog.Builder(this).setMessage("Are you sure you want to delete this screen?")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dao.delete(screen);
                        reloadAdapter();
                    }
                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).show();
        return true;
    }
}
