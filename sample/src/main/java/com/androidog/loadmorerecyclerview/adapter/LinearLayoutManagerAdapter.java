package com.androidog.loadmorerecyclerview.adapter;

import android.widget.TextView;

import com.androidog.loadmorerecyclerview.R;
import com.androidog.loadmorerecyclerview.bean.GanHuo;
import com.androidog.loadmorerecyclerviewlibrary.BaseSingleViewTypeAdapter;
import com.androidog.loadmorerecyclerviewlibrary.RecyclerViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LinearLayoutManagerAdapter extends BaseSingleViewTypeAdapter<GanHuo.Result> {

    public LinearLayoutManagerAdapter(List<GanHuo.Result> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_linearlayoutmanager;
    }

    @Override
    protected void onCreate(RecyclerViewHolder viewHolder) {

    }

    @Override
    protected void onBind(RecyclerViewHolder viewHolder, int position, GanHuo.Result itemData) {
        SimpleDraweeView imageView = viewHolder.getView(R.id.imageView);
        imageView.setImageURI(itemData.getUrl());

        TextView tvWho = viewHolder.getView(R.id.tv_who);
        tvWho.setText(itemData.getWho());

        TextView tvDesc = viewHolder.getView(R.id.tv_desc);
        tvDesc.setText(itemData.getDesc());
    }
}