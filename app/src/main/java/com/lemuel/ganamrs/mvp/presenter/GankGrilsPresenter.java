package com.lemuel.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.GankGrilsContract;

import java.util.ArrayList;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class GankGrilsPresenter extends BasePresenter<GankGrilsContract.Model, GankGrilsContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public GankGrilsPresenter(GankGrilsContract.Model model, GankGrilsContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }
    public void getGankGrils(final boolean b){
        mModel.getRandomGrils(new AdoreCallback<ArrayList<GankGril>>() {
            @Override
            public void onSuccess(ArrayList<GankGril> gankGrils) {
                if (b){
                    mRootView.setGankGrils(gankGrils);
                }else {
                    mRootView.addGankGrils(gankGrils);
                }
                mRootView.hideLoading();
            }

            @Override
            public void onError(Throwable t) {
                mRootView.hideLoading();
                mRootView.showMessage(t.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void addToFavorites(GankGril gril) {
        mModel.addToFavorites(gril);
    }
}