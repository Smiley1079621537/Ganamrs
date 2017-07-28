package com.lemuel.ganamrs.integration;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.jess.arms.base.delegate.AppLifecycles;
import com.jess.arms.di.module.GlobalConfigModule;
import com.jess.arms.integration.ConfigModule;

import java.util.List;

public class GlobalConfiguration implements ConfigModule {


    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {

    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {

    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }

}
