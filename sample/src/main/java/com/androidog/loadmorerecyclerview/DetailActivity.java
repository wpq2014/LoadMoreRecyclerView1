package com.androidog.loadmorerecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.DraweeTransition;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wupuquan
 * @version 1.0
 * @since 2018/12/18 17:59
 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.drawee)
    SimpleDraweeView mDrawee;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSharedElementEnterTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP)); // 进入
        getWindow().setSharedElementReturnTransition(DraweeTransition.createTransitionSet(ScalingUtils.ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP)); // 返回
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

//        setExitSharedElementCallback(new SharedElementCallback() {
//            @Override
//            public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
//                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
//                for (View view : sharedElements) {
//                    view.setVisibility(View.VISIBLE);
//                }
//            }
//        });

        mDrawee.setImageURI("http://ww1.sinaimg.cn/large/7a8aed7bjw1f03emebr4jj20ez0qoadk.jpg");
//        mImageView.setImageURI(getIntent().getStringExtra("pic"));
    }
}
