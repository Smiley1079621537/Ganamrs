package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.di.module.CollectModule;
import com.lemuel.ganamrs.mvp.ui.fragment.CollectFragment;

import dagger.Component;

@ActivityScope
@Component(modules = CollectModule.class, dependencies = AppComponent.class)
public interface CollectComponent {
    void inject(CollectFragment fragment);
}