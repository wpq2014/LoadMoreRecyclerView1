package com.androidog.loadmorerecyclerview.adapter;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.androidog.loadmorerecyclerview.R;
import com.androidog.loadmorerecyclerviewlibrary.BaseAdapter;
import com.androidog.loadmorerecyclerviewlibrary.BaseViewHolder;

import java.util.List;

public class HeaderAndFooterAdapter extends BaseAdapter<String> {

    public HeaderAndFooterAdapter(List<String> list) {
        super(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_main;
    }

    @Override
    protected void onCreate(@NonNull BaseViewHolder viewHolder) {

    }

    @Override
    protected void onBind(@NonNull BaseViewHolder viewHolder, int position, @NonNull String itemData) {
        TextView tvItem = viewHolder.getView(R.id.tv_item);
        tvItem.setText(itemData);
    }
}