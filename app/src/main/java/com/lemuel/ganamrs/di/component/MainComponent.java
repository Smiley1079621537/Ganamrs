package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.di.module.MainModule;
import com.lemuel.ganamrs.mvp.ui.activity.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = MainModule.class, dependencies = AppComponent.class)
public interface MainComponent {
    void inject(MainActivity activity);
}