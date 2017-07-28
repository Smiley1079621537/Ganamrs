package com.lemuel.ganamrs.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.AdoreSubscriver;
import com.lemuel.ganamrs.api.ApiManager;
import com.lemuel.ganamrs.api.GankService;
import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.entity.GankResponse;
import com.lemuel.ganamrs.mvp.contract.AndroidContract;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@ActivityScope
public class CategoryModel extends BaseModel implements AndroidContract.Model {
    private Gson mGson;
    private Application mApplication;
    private final GankService mGankService;

    @Inject
    public CategoryModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
        mGankService = ApiManager.getGankService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public void getGankData(String type, int pageSize, int page,
                            final AdoreCallback<ArrayList<GankBean>> callback) {
        mGankService.getGank(type, pageSize, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new AdoreSubscriver<GankResponse<GankBean>>() {
                    @Override
                    public void onNext(GankResponse<GankBean> o) {
                        if (!o.isError() && o.getResults() != null && o.getResults().size() > 0) {
                            callback.onSuccess(o.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        callback.onError(t);
                    }
                });
    }
}