package com.lemuel.ganamrs;

public interface AdoreCallback<T>  {
    void onSuccess(T t);
    void onError(Throwable t);
}
