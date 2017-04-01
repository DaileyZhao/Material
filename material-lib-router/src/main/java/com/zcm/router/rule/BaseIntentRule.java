package com.zcm.router.rule;

import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

/**
 * 返回Intent的路由规则的基类
 * Created by zcm on 17-4-1.
 */

public abstract class BaseIntentRule<T> implements Rule<T,Intent> {
    private HashMap<String, Class<T>> mIntentRules;

    public BaseIntentRule() {
        mIntentRules = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void router(String pattern, Class<T> klass) {
        mIntentRules.put(pattern, klass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Intent invoke(Context ctx, String pattern) {
        Class<T> klass = mIntentRules.get(pattern);
        if (klass == null) { throwException(pattern);}
        return new Intent(ctx, klass);
    }

    @Override
    public boolean isExistRule(String pattern) {
        return mIntentRules.get(pattern) != null;
    }

    /**
     * 当找不到路由规则时抛出异常
     * @param pattern 路由pattern
     */
    public abstract void throwException(String pattern);
}
