package com.zcm.thunder.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zcm on 17-4-10.
 */

public class ThunderBean {
    public String url;
    public String username;
    public static List<ThunderBean> getSampleData(int lenth) {
        List<ThunderBean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            ThunderBean bean = new ThunderBean();
            bean.url="https://avatars1.githubusercontent.com/u/7698209?v=3&s=460";
            bean.username="cmc"+i;
            list.add(bean);
        }
        return list;
    }
}
