package com.zcm.thunder.model;

import android.view.View;

/**
 * Created by zcm on 17-4-10.
 */

public class TestItem implements View.OnClickListener{
    private String item_name;
    private View item_view;
    public TestItem(String item_name){
        this.item_name=item_name;
    }
    public String getItem_name(){
        return item_name;
    }
    public void setViewOnclick(View view){
        item_view=view;
        item_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
