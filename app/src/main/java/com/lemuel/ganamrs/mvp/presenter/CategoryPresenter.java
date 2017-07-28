package com.lemuel.ganamrs.mvp.presenter;

import android.app.Application;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.widget.imageloader.ImageLoader;
import com.lemuel.ganamrs.AdoreCallback;
import com.lemuel.ganamrs.entity.GankBean;
import com.lemuel.ganamrs.mvp.contract.AndroidContract;

import java.util.ArrayList;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


@ActivityScope
public class CategoryPresenter extends BasePresenter<AndroidContract.Model, AndroidContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public CategoryPresenter(AndroidContract.Model model, AndroidContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void getGankData(String type, int pageSize, final int page){
        mModel.getGankData(type, pageSize, page, new AdoreCallback<ArrayList<GankBean>>() {
            @Override
            public void onSuccess(ArrayList<GankBean> gankBeen) {
                if (page > 1){
                    mRootView.addGankData(gankBeen);
                }else {
                    mRootView.setGankData(gankBeen);
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

}