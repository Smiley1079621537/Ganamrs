package com.lemuel.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.AdoreSubscriver;
import com.lemuel.ganamrs.App;
import com.lemuel.ganamrs.api.ApiManager;
import com.lemuel.ganamrs.api.GankService;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.entity.GankResponse;
import com.lemuel.ganamrs.mvp.contract.GankGrilsContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@ActivityScope
public class GankGrilsModel extends BaseModel implements GankGrilsContract.Model {
    private Gson mGson;
    private Application mApplication;
    private final GankService mGankService;

    @Inject
    public GankGrilsModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
        mGankService = ApiManager.getGankService();
    }

    @Override
    public void getRandomGrils(final AdoreCallback<ArrayList<GankGril>> callback) {
        mGankService.getRandomGrils().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AdoreSubscriver<GankResponse<GankGril>>() {
                    @Override
                    public void onNext(GankResponse<GankGril> o) {
                        if (!o.isError() && o.getResults() != null && o.getResults().size() > 0) {
                            callback.onSuccess(o.getResults());
                        }
                    }
                });
    }

    @Override
    public void addToFavorites(GankGril gril) {
        App.getDaoSession().getGankGrilDao().insert(gril);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

}