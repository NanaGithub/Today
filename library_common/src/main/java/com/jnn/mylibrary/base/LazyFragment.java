package com.jnn.mylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author jnn
 * @date 2019/4/9
 * @description Fragment懒加载基类, fragment可见时加载数据（vp+fragment时常用）
 */
public abstract class LazyFragment extends Fragment {
    public static final String TAG = LazyFragment.class.getSimpleName();
    /**
     * Fragment的View加载完毕的标记
     */
    private boolean isViewCreated;
    /**
     * Fragment对用户可见的标记
     */
    private boolean isUiVisible;

    private View mRootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.i(TAG, "setUserVisibleHint -- " + isVisibleToUser + "\trootView -- " + mRootView);
        //setUserVisibleHint(boolean isVisibleToUser)方法会多次回调
        //而且可能会在onCreateView()方法执行完毕之前回调。
        if (isVisibleToUser) {
            if (mRootView != null) {
                isViewCreated = true;
            }
            isUiVisible = true;
            lazyLoad();
        } else {
            isUiVisible = false;
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mRootView != null) {
            //缓存rootView，避免多次初始化
            return mRootView;
        }
        Log.i(TAG, "onCreateView -- " + mRootView);
        mRootView = setView(inflater, container);
        initView();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated -- isUiVisible:" + isUiVisible + "\tisViewCreated" + isViewCreated);
        Log.i(TAG, "onViewCreated --" + mRootView);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView" + mRootView);
        //页面销毁,恢复标记
        isViewCreated = false;
        isUiVisible = false;
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,
        // 必须确保onCreateView加载完毕且页面可见,才加载数据
        Log.e(TAG, "lazyLoad -- isUiVisible:" + isUiVisible + "\tisViewCreated:" + isViewCreated);
        if (isViewCreated && isUiVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUiVisible = false;
        }
    }

    /**
     * 子类加载布局
     *
     * @param inflater
     * @param container
     * @return view
     */
    protected abstract View setView(LayoutInflater inflater, ViewGroup container);

    /**
     * 子类初始化View
     */
    protected abstract void initView();

    /**
     * 子类实现加载数据逻辑
     */
    protected void loadData() {
    }
}
