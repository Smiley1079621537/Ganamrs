package com.lemuel.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.App;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.CollectContract;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;


@ActivityScope
public class CollectModel extends BaseModel implements CollectContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public CollectModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void removeThisGril(GankGril gril) {
        App.getDaoSession().getGankGrilDao().delete(gril);
    }

    @Override
    public void getAllGrils(AdoreCallback<List<GankGril>> callback) {
        List<GankGril> gankGrils = App.getDaoSession().getGankGrilDao().loadAll();
        if (gankGrils != null && gankGrils.size() > 0) {
            Collections.reverse(gankGrils);
            callback.onSuccess(gankGrils);
        }else {
            callback.onError(null);
        }
    }
}