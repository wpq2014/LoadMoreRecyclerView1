package com.androidog.loadmorerecyclerviewlibrary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author wpq
 * @version 1.0
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>{

    private List<T> mList;
    private OnItemClickListener<T> mOnItemClickListener;
    private OnItemLongClickListener<T> mOnItemLongClickListener;

    public BaseAdapter(List<T> list) {
        mList = list;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (0 == getLayoutId()) {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        View convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        BaseViewHolder holder = new BaseViewHolder(convertView);
        onCreate(holder);
//        setListeners(holder);
        return holder;
    }

    private void setListeners(@NonNull final BaseViewHolder holder, final int position, @NonNull final T t) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder, position, t);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mOnItemLongClickListener.onItemLongClick(holder, position, t);
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        T t = mList.get(position);
        if (t == null) {
            return;
        }
        onBind(holder, position, t);
        setListeners(holder, position, t);
    }

    @Override
    public int getItemCount() {
        return mList == null? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /**
     * convertView layoutId
     *
     * @return resource ID for an XML layout resource to load (e.g.,
     *         <code>R.layout.main_page</code>)
     */
    protected abstract int getLayoutId();

    /**
     * 初始化操作，比如设置 LayoutParams
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     */
    protected abstract void onCreate(@NonNull BaseViewHolder viewHolder);

    /**
     * 设置事件监听和数据
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the
     *               item at the given position in the data set.
     * @param position Position of the item whose data we want within the adapter's data set.
     * @param itemData 数据源
     */
    protected abstract void onBind(@NonNull BaseViewHolder viewHolder, int position, @NonNull T itemData);

    public interface OnItemClickListener<T> {
        void onItemClick(@NonNull BaseViewHolder viewHolder, int position, @NonNull T t);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(@NonNull BaseViewHolder viewHolder, int position, @NonNull T t);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }
}
