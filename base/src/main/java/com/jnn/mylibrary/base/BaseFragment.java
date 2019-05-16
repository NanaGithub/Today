package com.jnn.mylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * 通用基类Fragment，可以继承该类定制AppFragment基类
 */
public class BaseFragment extends Fragment {
    protected static final String TAG = BaseFragment.class.getSimpleName();
    protected Activity mActivity;
    protected Bundle mFragmentArgs;
    protected LayoutInflater mLayoutInflater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //优化：Fragment调用getActivity()的地方不可控因素可能返回null
        mActivity = (Activity) context;
        mFragmentArgs = getArguments();
        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    protected void enterActivity(Class<?> targetActivity) {
        if (mActivity == null)
            return;
        mActivity.startActivity(new Intent(mActivity, targetActivity));
    }

    protected void enterActivity(Bundle bundle, Class<?> targetActivity) {
        if (mActivity == null || bundle == null)
            return;
        Intent intent = new Intent(mActivity, targetActivity);
        intent.putExtras(bundle);
        mActivity.startActivity(intent);
    }

    /**
     * 查找控件
     */
    protected <T extends View> T find(View rootView, int viewId) {
        if (rootView == null)
            throw new NullPointerException("fragment's root view can't null");
        return (T) rootView.findViewById(viewId);
    }


    /**
     * 场景Activity A 传值 Activity B中的fragment
     * 直接在Activity B 获取到值之后再创建Fragment提交
     */
    public static final String ARGUMENT = "argument";

    public static BaseFragment newInstance(String argument) {
        Bundle bundle = new Bundle();
        bundle.putString(ARGUMENT, argument);
        BaseFragment fragment = new BaseFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
