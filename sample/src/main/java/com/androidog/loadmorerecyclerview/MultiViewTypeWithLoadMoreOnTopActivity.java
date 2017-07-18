package com.androidog.loadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.androidog.loadmorerecyclerview.adapter.MultiTypeAdapter;
import com.androidog.loadmorerecyclerview.bean.MultiTypeBean;
import com.androidog.loadmorerecyclerviewlibrary.LoadMoreOnTopRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wpq
 * @version 1.0
 */
public class MultiViewTypeWithLoadMoreOnTopActivity extends AppCompatActivity {

    public static final int PAGE_COUNT = 15;

    @BindView(R.id.recyclerView)
    LoadMoreOnTopRecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private MultiTypeAdapter mAdapter;
    private List<MultiTypeBean> mList = new ArrayList<>();

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view_type_with_load_more_on_top);
        ButterKnife.bind(this);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setOnLoadListener(new LoadMoreOnTopRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        mAdapter = new MultiTypeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadMoreData();
            }
        }, 100);
    }

    private void loadMoreData() {
        if (index >= 3) {
            return;
        }

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(0, new MultiTypeBean(MultiTypeBean.TYPE_DATE, "2017年" + (++index) +"月"));
                for (int i = 0; i < PAGE_COUNT - 1; i++) {
                    int type = MultiTypeBean.TYPE_LEFT + new Random().nextInt(2);
                    String content = type == MultiTypeBean.TYPE_LEFT ? "我是左边" : "我是右边";
                    mList.add(1, new MultiTypeBean(type, content));
                }

//                mAdapter.notifyItemInserted(0);
                mAdapter.notifyDataSetChanged();
                if (mList.size() >= PAGE_COUNT) {
                    mLinearLayoutManager.scrollToPositionWithOffset(PAGE_COUNT, 0);
                }

                if (index >= 3) {
                    mRecyclerView.noMore();
                } else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        }, 1000);

    }
}
