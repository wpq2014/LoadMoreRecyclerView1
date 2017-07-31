package com.androidog.loadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.androidog.loadmorerecyclerview.adapter.GridLayoutManagerAdapter;
import com.androidog.loadmorerecyclerview.api.Api;
import com.androidog.loadmorerecyclerview.bean.GanHuo;
import com.androidog.loadmorerecyclerview.util.ScreenUtils;
import com.androidog.loadmorerecyclerview.widget.HeaderAndFooterView;
import com.androidog.loadmorerecyclerviewlibrary.BaseSingleViewTypeAdapter;
import com.androidog.loadmorerecyclerviewlibrary.GridSpaceItemDecoration;
import com.androidog.loadmorerecyclerviewlibrary.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wpq
 * @version 1.0
 */
public class GridLayoutManagerActivity extends AppCompatActivity {

    public static final String TAG = GridLayoutManagerActivity.class.getSimpleName();
    /** 分页设置每页7条 */
    public static final int PAGE_COUNT = 7;

    @BindView(R.id.ptrFrameLayout)
    PtrClassicFrameLayout mPtrFrameLayout;
    @BindView(R.id.recyclerView)
    LoadMoreRecyclerView mRecyclerView;

    private GridLayoutManagerAdapter mAdapter;
    private List<GanHuo.Result> mList = new ArrayList<>();

    private int page = 71; // 当前用的接口最多512条数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridlayoutmanager);
        ButterKnife.bind(this);

        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                page = 1; // gank.io 0和1一样，所以从1开始
                showTime(true);
            }
        });

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(mRecyclerView, ScreenUtils.dp2px(this, 3f), false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnLoadListener(new LoadMoreRecyclerView.OnLoadListener() {
            @Override
            public void onLoadMore() {
                showTime(false);
            }
        });

        View header0 = new HeaderAndFooterView(this, 0xff235840, "header0");
        mRecyclerView.addHeaderView(header0);
        View header1 = new HeaderAndFooterView(this, 0xff840395, "header1");
        mRecyclerView.addHeaderView(header1);

        mAdapter = new GridLayoutManagerAdapter(mList);
        mAdapter.setOnItemClickListener(new BaseSingleViewTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                try {
                    GanHuo.Result result = mList.get(position - mRecyclerView.getHeadersCount());
                    Toast.makeText(GridLayoutManagerActivity.this, "单击第 " + position + " 项：" + result.getDesc(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {}
            }
        });
        mAdapter.setOnItemLongClickListener(new BaseSingleViewTypeAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                try {
                    GanHuo.Result result = mList.get(position - mRecyclerView.getHeadersCount());
                    Toast.makeText(GridLayoutManagerActivity.this, "长按第 " + position + " 项：" + result.getDesc(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {}
                return true;
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        showTime(false);
    }

    /**
     * 一大波美女即将登场
     * @param isRefresh 刷新 or 加载更多
     */
    private void showTime(final boolean isRefresh) {
        Api.getInstance().getGankService()
                .getGanHuo("福利", PAGE_COUNT, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<GanHuo>() {
                    @Override
                    public void onNext(@NonNull GanHuo ganHuo) {
                        Log.e(TAG, "onNext");
                        if (ganHuo != null) {
                            if (isRefresh) {
                                mPtrFrameLayout.refreshComplete();
                                mRecyclerView.scrollToPosition(0);
                                mList.clear();
                                mAdapter.notifyDataSetChanged();
                            }
                            mList.addAll(ganHuo.getResults());
//                            mAdapter.notifyDataSetChanged();
                            mAdapter.notifyItemInserted(mRecyclerView.getHeadersCount() + mList.size());
//                            mAdapter.notifyItemRangeInserted(mRecyclerView.getHeadersCount() + mList.size(), ganhuo.getResults().size());
                            if (mList.size() < PAGE_COUNT) {
                                mRecyclerView.noNeedToLoadMore();
                            } else if (ganHuo.getResults().size() < PAGE_COUNT) {
                                mRecyclerView.noMore();
                            } else {
                                mRecyclerView.loadMoreComplete();
                                page++;
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e(TAG, "onError");
                        mRecyclerView.loadMoreError();
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete");
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_linearlayoutmanager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_more_than_one_page: // 一页以上
                page = 71;
                showTime(true);
                break;
            case R.id.action_not_enough_one_page: // 不足一页
                page = 76;
                showTime(true);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
