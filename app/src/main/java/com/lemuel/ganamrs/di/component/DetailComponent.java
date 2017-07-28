package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.lemuel.ganamrs.di.module.DetailModule;

import com.lemuel.ganamrs.mvp.ui.activity.DetailActivity;

@ActivityScope
@Component(modules = DetailModule.class, dependencies = AppComponent.class)
public interface DetailComponent {
    void inject(DetailActivity activity);
}