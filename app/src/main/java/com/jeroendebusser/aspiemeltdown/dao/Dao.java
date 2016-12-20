package com.jeroendebusser.aspiemeltdown.dao;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Jeroen De Busser on 19/12/2016.
 */
public class Dao {
    static private Dao instance = null;

    private DaoSession daoSession;

    static public Dao getInstance(Context context) {
        if(instance == null) {
            instance = new Dao(context.getApplicationContext());
        }
        return instance;
    }

    static public DaoSession getSession(Context context) {
        return getInstance(context).daoSession;
    }

    private Dao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"emergency-chat");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }
}
