package com.androidog.loadmorerecyclerview.adapter;

import android.widget.TextView;

import com.androidog.loadmorerecyclerview.R;
import com.androidog.loadmorerecyclerviewlibrary.BaseSingleViewTypeAdapter;
import com.androidog.loadmorerecyclerviewlibrary.RecyclerViewHolder;

import java.util.List;

public class HeaderAndFooterAdapter extends BaseSingleViewTypeAdapter<String> {

    public HeaderAndFooterAdapter(List<String> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_main;
    }

    @Override
    protected void init(RecyclerViewHolder viewHolder) {

    }

    @Override
    protected void onBind(RecyclerViewHolder viewHolder, int position, String itemData) {
        TextView tvItem = viewHolder.getView(R.id.tv_item);
        tvItem.setText(itemData);
    }
}