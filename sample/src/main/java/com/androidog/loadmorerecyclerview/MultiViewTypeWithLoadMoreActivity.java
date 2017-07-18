package com.androidog.loadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidog.loadmorerecyclerview.adapter.MultiTypeAdapter;
import com.androidog.loadmorerecyclerview.bean.MultiTypeBean;
import com.androidog.loadmorerecyclerview.widget.HeaderAndFooterView;
import com.androidog.loadmorerecyclerviewlibrary.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wpq
 * @version 1.0
 */
public class MultiViewTypeWithLoadMoreActivity extends AppCompatActivity {

    public static final int PAGE_COUNT = 15;

    @BindView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;

    private List<MultiTypeBean> mList = new ArrayList<>();
    private MultiTypeAdapter mAdapter;

    private int index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_view_type_with_load_more);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnLoadListener(new LoadMoreRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
        mAdapter = new MultiTypeAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);

        addHeader();

        loadMoreData();
    }

    private void addHeader() {
        View header = new HeaderAndFooterView(this, 0xff235840, "header");
        mRecyclerView.addHeaderView(header);
    }

    private void loadMoreData() {
        if (index >= 3) {
            return;
        }

        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.add(new MultiTypeBean(MultiTypeBean.TYPE_DATE, "2017年" + (++index) +"月"));
                for (int i = 0; i < PAGE_COUNT - 1; i++) {
                    int type = MultiTypeBean.TYPE_LEFT + new Random().nextInt(2);
                    String content = type == MultiTypeBean.TYPE_LEFT ? "我是左边" : "我是右边";
                    mList.add(new MultiTypeBean(type, content));
                }
//                mAdapter.notifyDataSetChanged();
                mAdapter.notifyItemInserted(mRecyclerView.getHeadersCount() + mList.size());
                if (index >= 3) {
                    mRecyclerView.noMore();
                } else {
                    mRecyclerView.loadMoreComplete();
                }
            }
        }, 1000);
    }
}
