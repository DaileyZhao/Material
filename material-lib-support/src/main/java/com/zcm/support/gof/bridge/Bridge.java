package com.zcm.support.gof.bridge;

/**
 * Created by ZCM on 2017/6/24.
 */

public abstract class Bridge {
    private Sourceable source;

    public void method() {
        source.method();
    }

    public Sourceable getSource() {
        return source;
    }

    public void setSource(Sourceable source) {
        this.source = source;
    }
}
