package com.zcm.thunder.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zcm.thunder.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zcm on 17-4-10.
 */

public class FuncListAdapter extends RecyclerView.Adapter<FuncListAdapter.FuncViewHolder> {
    private List<TestItem> item_list;
    private Context context;
    public FuncListAdapter(List<TestItem> item_list,Context context){
        this.item_list=item_list;
        this.context=context;
    }
    @Override
    public FuncViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_view= LayoutInflater.from(context).inflate(R.layout.item_test,parent,false);
        FuncViewHolder viewHolder=new FuncViewHolder(item_view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FuncViewHolder holder, int position) {
        holder.setBtn(position);
    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }

    class FuncViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.btn_item_test)
        Button itemButton;
        public FuncViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void setBtn(int position){
            itemButton.setText(item_list.get(position).getItem_name());
            item_list.get(position).setViewOnclick(itemButton);
        }
    }
}
