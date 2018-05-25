package com.lemuel.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.mvp.contract.GospelContract;
import com.lemuel.ganamrs.mvp.model.GospelModel;

import dagger.Module;
import dagger.Provides;


@Module
public class GospelModule {
    private GospelContract.View view;

    /**
     * 构建GospelModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GospelModule(GospelContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GospelContract.View provideGospelView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GospelContract.Model provideGospelModel(GospelModel model) {
        return model;
    }
}