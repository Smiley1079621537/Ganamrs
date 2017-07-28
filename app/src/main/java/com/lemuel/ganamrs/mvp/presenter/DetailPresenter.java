package com.lemuel.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankGril;
import com.lemuel.ganamrs.mvp.contract.DetailContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class DetailPresenter extends BasePresenter<DetailContract.Model, DetailContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public DetailPresenter(DetailContract.Model model, DetailContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void getRandomGril(){
        mModel.getRandomGril(new AdoreCallback<GankGril>() {
            @Override
            public void onSuccess(GankGril gril) {
                mRootView.loadGril(gril);
            }

            @Override
            public void onError(Throwable t) {

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

}