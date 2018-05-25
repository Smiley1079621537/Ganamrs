package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.di.module.GospelModule;
import com.lemuel.ganamrs.mvp.ui.fragment.GospelFragment;

import dagger.Component;

@ActivityScope
@Component(modules = GospelModule.class, dependencies = AppComponent.class)
public interface GospelComponent {
    void inject(GospelFragment fragment);
}