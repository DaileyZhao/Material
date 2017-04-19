package com.zcm.thunder.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zcm.thunder.R;
import com.zcm.thunder.test.ThunderBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zcm on 17-4-10.
 */

public class SuperRVAdapter extends BaseQuickAdapter<ThunderBean,SuperRVAdapter.UserViewHolder> {
    private Context context;
    public SuperRVAdapter(Context context) {
        super(R.layout.item_user,ThunderBean.getSampleData(100));
        this.context=context;
    }

    @Override
    protected void convert(UserViewHolder helper, ThunderBean item) {
        helper.setText(R.id.tv_user,item.username);
        Glide.with(context).load(item.url).into(helper.img_header);

    }
    class UserViewHolder extends BaseViewHolder{
        @BindView(R.id.img_header)
        ImageView img_header;
        public UserViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
