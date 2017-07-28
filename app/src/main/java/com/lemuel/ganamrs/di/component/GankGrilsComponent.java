package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.lemuel.ganamrs.di.module.GankGrilsModule;

import com.lemuel.ganamrs.mvp.ui.fragment.GankGrilsFragment;

@ActivityScope
@Component(modules = GankGrilsModule.class, dependencies = AppComponent.class)
public interface GankGrilsComponent {
    void inject(GankGrilsFragment fragment);
}