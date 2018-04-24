package com.androidog.loadmorerecyclerview.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.androidog.loadmorerecyclerview.R;
import com.androidog.loadmorerecyclerview.bean.GanHuo;
import com.androidog.loadmorerecyclerviewlibrary.BaseAdapter;
import com.androidog.loadmorerecyclerviewlibrary.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class LinearLayoutManagerAdapter extends BaseAdapter<GanHuo.Result> {

    public LinearLayoutManagerAdapter(List<GanHuo.Result> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_linearlayoutmanager;
    }

    @Override
    protected void onCreate(@NonNull BaseViewHolder viewHolder) {

    }

    @Override
    protected void onBind(@NonNull final BaseViewHolder viewHolder, final int position, @NonNull GanHuo.Result itemData) {
        SimpleDraweeView imageView = viewHolder.getView(R.id.imageView);
        imageView.setImageURI(itemData.getUrl());

        TextView tvWho = viewHolder.getView(R.id.tv_who);
        tvWho.setText(itemData.getWho());

        TextView tvDesc = viewHolder.getView(R.id.tv_desc);
        tvDesc.setText(itemData.getDesc());
    }
}