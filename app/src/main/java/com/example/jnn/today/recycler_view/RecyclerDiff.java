package com.example.jnn.today.recycler_view;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.text.TextUtils;

import java.util.List;

/**
 * @author jnn
 * @date 2019/11/12
 * @description 高效刷新方法DiffUtil配合RecyclerView使用
 */
public class RecyclerDiff extends DiffUtil.Callback {
    List<PersonBean> personBeans;
    List<PersonBean> newPersonBeans;

    public RecyclerDiff(List<PersonBean> personBeans, List<PersonBean> newPersonBeans) {
        this.personBeans = personBeans;
        this.newPersonBeans = newPersonBeans;
    }

    @Override
    public int getOldListSize() {
        return personBeans.size();
    }

    @Override
    public int getNewListSize() {
        return newPersonBeans.size();
    }

    /**
     * 新老数据集在同一个postion的Item是否是一个对象？
     * 可能内容不同，如果这里返回true，会调用areContentsTheSame的方法
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return TextUtils.equals(personBeans.get(oldItemPosition).age,
                newPersonBeans.get(newItemPosition).age);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return TextUtils.equals(personBeans.get(oldItemPosition).name,
                newPersonBeans.get(newItemPosition).name);
    }

    /**
     * 当 areItemsTheSame返回true， areContentsTheSame返回false时，也就是同一个item内容不同时，该方法会回调。
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        /**
         * 定向刷新中的部分更新，效率最高
         * 只是没有了ItemChange的白光一闪动画，（反正我也觉得不太重要）
         * 这里返回一个包含item变化的object
         */
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
