package com.lemuel.ganamrs.di.module;

import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.mvp.contract.AndroidContract;
import com.lemuel.ganamrs.mvp.model.CategoryModel;

import dagger.Module;
import dagger.Provides;


@Module
public class CategoryModule {
    private AndroidContract.View view;

    /**
     * 构建AndroidModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public CategoryModule(AndroidContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AndroidContract.View provideAndroidView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AndroidContract.Model provideAndroidModel(CategoryModel model) {
        return model;
    }
}