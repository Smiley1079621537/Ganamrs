package com.lemuel.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.Subject;
import com.lemuel.ganamrs.mvp.contract.GospelContract;

import java.util.ArrayList;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class GospelPresenter extends BasePresenter<GospelContract.Model, GospelContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public GospelPresenter(GospelContract.Model model, GospelContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void getGospel() {
        mModel.getGospel(new AdoreCallback<ArrayList<Subject>>() {
            @Override
            public void onSuccess(ArrayList<Subject> subjects) {
                mRootView.loadGospel(subjects);
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}