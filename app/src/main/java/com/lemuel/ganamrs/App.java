package com.lemuel.ganamrs;

import android.database.sqlite.SQLiteDatabase;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jess.arms.base.BaseApplication;
import com.lemuel.ganamrs.greendao.DaoMaster;
import com.lemuel.ganamrs.greendao.DaoSession;


public class App extends BaseApplication {

    private static final String DATABASE_NAME = "grils";
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDataBase(this);
        Fresco.initialize(this);
    }

    private void setupDataBase(App app) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(app, DATABASE_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return mDaoSession;
    }
}
