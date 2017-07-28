package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.di.module.HomeModule;
import com.lemuel.ganamrs.mvp.ui.fragment.HomeFragment;

import dagger.Component;

@ActivityScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}