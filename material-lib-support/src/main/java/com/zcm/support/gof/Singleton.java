package com.zcm.support.gof;

/**
 * Created by zcm on 17-6-1.
 * 线程安全懒汉式单例模式模板
 */

public abstract class Singleton<T> {
    private T mInstance;

    protected abstract T create();

    //线程安全的懒汉式
    public final T get() {
        synchronized (this) {
            if (mInstance == null) {
                mInstance = create();
            }
            return mInstance;
        }
    }
}
