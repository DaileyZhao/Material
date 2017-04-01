package com.zcm.router.rule;

import android.app.Activity;

import com.zcm.router.exception.ActivityNotRouteException;

/**
 * Created by zcm on 17-4-1.
 */

public class ActivityRule extends BaseIntentRule<Activity> {
    /** activity路由scheme*/
    public static final String ACTIVITY_SCHEME = "activity://";

    /**
     * {@inheritDoc}
     */
    @Override
    public void throwException(String pattern) {
        throw new ActivityNotRouteException(pattern);
    }
}
