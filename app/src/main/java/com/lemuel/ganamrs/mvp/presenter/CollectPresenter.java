package com.lemuel.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.CollectContract;

import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class CollectPresenter extends BasePresenter<CollectContract.Model, CollectContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public CollectPresenter(CollectContract.Model model, CollectContract.View rootView
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

    public void removeThisGril(GankGril gril) {
        mModel.removeThisGril(gril);

    }

    public void getAllGrils() {
        mModel.getAllGrils(new AdoreCallback<List<GankGril>>() {
            @Override
            public void onSuccess(List<GankGril> gankGrils) {
                mRootView.loadGrils(gankGrils);
                mRootView.hideLoading();
            }

            @Override
            public void onError(Throwable t) {
                mRootView.hideLoading();
                mRootView.showEmpty();
            }
        });
    }
}