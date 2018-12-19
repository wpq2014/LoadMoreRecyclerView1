package com.androidog.loadmorerecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.androidog.loadmorerecyclerview.adapter.MainAdapter;
import com.androidog.loadmorerecyclerviewlibrary.BaseAdapter;
import com.androidog.loadmorerecyclerviewlibrary.BaseViewHolder;
import com.androidog.loadmorerecyclerviewlibrary.LoadMoreRecyclerView;

import java.util.Arrays;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;

    @BindArray(R.array.main_item)
    String[] mArray;

    private Class<?>[] mClasses = {HeaderAndFooterActivity.class, LinearLayoutManagerActivity.class, GridLayoutManagerActivity.class,
            StaggeredGridlayoutManagerActivity.class, MultiViewTypeWithLoadMoreActivity.class, MultiViewTypeWithSwipeRefreshLayoutActivity.class, MultiViewTypeWithUltraPullToRefreshActivity.class, MultiViewTypeWithLoadMoreOnTopActivity.class};

    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP)); // 进入
//        getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP)); // 返回
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new MainAdapter(Arrays.asList(mArray));
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(@NonNull BaseViewHolder viewHolder, int position, @NonNull String s) {
                startActivity(new Intent(MainActivity.this, mClasses[position]));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

}
