package com.lemuel.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

import com.lemuel.ganamrs.mvp.contract.GankGrilsContract;
import com.lemuel.ganamrs.mvp.model.GankGrilsModel;


@Module
public class GankGrilsModule {
    private GankGrilsContract.View view;

    /**
     * 构建GankGrilsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public GankGrilsModule(GankGrilsContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GankGrilsContract.View provideGankGrilsView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GankGrilsContract.Model provideGankGrilsModel(GankGrilsModel model) {
        return model;
    }
}