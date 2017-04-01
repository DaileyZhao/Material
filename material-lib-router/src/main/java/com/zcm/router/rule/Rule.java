package com.zcm.router.rule;

import android.content.Context;

/**
 * 路由规则接口
 * Created by zcm on 17-4-1.
 */

public interface Rule<T,V> {
    /**
     * 添加路由
     * @param pattern 路由url
     * @param clazz 路由class
     */
    void router(String pattern,Class<T> clazz);

    /**
     * 路由调用
     * @param ctx Context
     * @param pattern 路由url
     * @return {@code V} 返回对应的返回值
     */
    V invoke(Context ctx, String pattern);
    /**
     * 查看是否存在pattern对应的路由
     * @param pattern
     * @return
     */
    boolean isExistRule(String pattern);
}
