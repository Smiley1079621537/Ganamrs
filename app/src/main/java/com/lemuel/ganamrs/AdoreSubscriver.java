package com.lemuel.ganamrs;

import com.jess.arms.utils.UiUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class AdoreSubscriver<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T o) {

    }

    @Override
    public void onError(Throwable t) {
        UiUtils.snackbarText(t.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
