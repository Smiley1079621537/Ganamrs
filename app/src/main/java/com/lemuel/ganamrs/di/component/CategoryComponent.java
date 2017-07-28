package com.lemuel.ganamrs.di.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.lemuel.ganamrs.di.module.CategoryModule;
import com.lemuel.ganamrs.mvp.ui.fragment.CategoryFragment;

import dagger.Component;

@ActivityScope
@Component(modules = CategoryModule.class, dependencies = AppComponent.class)
public interface CategoryComponent {
    void inject(CategoryFragment fragment);
}