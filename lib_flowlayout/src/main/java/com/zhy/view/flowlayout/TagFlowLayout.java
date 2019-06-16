package com.zhy.view.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhy on 15/9/10.
 */
public class TagFlowLayout extends FlowLayout
        implements TagAdapter.OnDataChangedListener {

    private static final String TAG = "TagFlowLayout";
    private TagAdapter mTagAdapter;
    private int mSelectedMax = -1;//-1为不限制数量
    private int mSelectMode = -1; //单选多选模式（默认多选）

    private Set<Integer> mSelectedView = new HashSet<>();

    private OnSelectListener mOnSelectListener;
    private OnTagClickListener mOnTagClickListener;

    public interface OnSelectListener {
        void onSelected(Set<Integer> selectPosSet);
    }

    public interface OnTagClickListener {
        boolean onTagClick(View view, int position, FlowLayout parent);
    }

    public TagFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TagFlowLayout);
        mSelectedMax = ta.getInt(R.styleable.TagFlowLayout_max_select, -1);
        mSelectMode = ta.getInt(R.styleable.TagFlowLayout_select_mode, -1);
        ta.recycle();
    }

    public TagFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagFlowLayout(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int cCount = getChildCount();
        for (int i = 0; i < cCount; i++) {
            TagView tagView = (TagView) getChildAt(i);
            if (tagView.getVisibility() == View.GONE) {
                continue;
            }
            if (tagView.getTagView().getVisibility() == View.GONE) {
                tagView.setVisibility(View.GONE);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    public void setOnSelectListener(OnSelectListener onSelectListener) {
        mOnSelectListener = onSelectListener;
    }


    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        mOnTagClickListener = onTagClickListener;
    }

    public void setAdapter(TagAdapter adapter) {
        mTagAdapter = adapter;
        mTagAdapter.setOnDataChangedListener(this);
        mSelectedView.clear();
        changeAdapter();
    }

    @SuppressWarnings("ResourceType")
    private void changeAdapter() {
        removeAllViews();
        TagAdapter adapter = mTagAdapter;
        TagView tagViewContainer = null;
        HashSet preCheckedList = mTagAdapter.getPreCheckedList();
        HashSet unEnablePosList = mTagAdapter.getUnEnablePosList();
        for (int i = 0; i < adapter.getCount(); i++) {
            View tagView = adapter.getView(this, i, adapter.getItem(i));
            tagViewContainer = new TagView(getContext());
            tagView.setDuplicateParentStateEnabled(true);
            if (tagView.getLayoutParams() != null) {
                tagViewContainer.setLayoutParams(tagView.getLayoutParams());
            } else {
                MarginLayoutParams lp = new MarginLayoutParams(
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                lp.setMargins(dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5),
                        dip2px(getContext(), 5));
                tagViewContainer.setLayoutParams(lp);
            }
            LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            tagView.setLayoutParams(lp);
            tagViewContainer.addView(tagView);
            addView(tagViewContainer);

            //有提前选中tab
            if (preCheckedList.contains(i)) {
                setChildChecked(i, tagViewContainer);
            }

            //有不可点击tab
            if (unEnablePosList.contains(i)) {
                setChildUnEnable(i, tagViewContainer);
            }
            //无用代码
            if (mTagAdapter.setSelected(i, adapter.getItem(i))) {
                setChildChecked(i, tagViewContainer);
            }
            tagView.setClickable(false);
            final TagView finalTagViewContainer = tagViewContainer;
            final int position = i;
            tagViewContainer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    doSelect(finalTagViewContainer, finalTagViewContainer, position);
                }
            });
        }
        mSelectedView.addAll(preCheckedList);
    }

    /**
     * 设置最大可选中的view数量
     *
     * @param count
     */
    public void setMaxSelectCount(int count) {
        if (mSelectedView.size() > count) {
            Log.w(TAG, "you has already select more than " + count + " views , so it will be clear .");
            mSelectedView.clear();
        }
        mSelectedMax = count;
    }

    /**
     * 获取当前选中view-position 集合
     *
     * @return
     */
    public Set<Integer> getSelectedList() {
        return new HashSet<Integer>(mSelectedView);
    }

    /**
     * 可点击
     *
     * @param position
     * @param view
     */
    private void setChildEnable(int position, TagView view) {
        view.setEnabled(true);
        mTagAdapter.onEnable(position, view.getTagView());
    }

    /**
     * 不可点击
     *
     * @param position
     * @param view
     */
    private void setChildUnEnable(int position, TagView view) {
        view.setEnabled(false);
        mTagAdapter.onEnable(position, view.getTagView());
    }

    /**
     * 选中
     *
     * @param position
     * @param view
     */
    private void setChildChecked(int position, TagView view) {
        view.setChecked(true);
        mTagAdapter.onSelected(position, view.getTagView());
    }

    /**
     * 取消选中
     *
     * @param position
     * @param view
     */
    private void setChildUnChecked(int position, TagView view) {
        view.setChecked(false);
        mTagAdapter.unSelected(position, view.getTagView());
    }

    /**
     * 点击子view
     *
     * @param child
     * @param finalTagViewContainer
     * @param position
     */
    private void doSelect(TagView child, TagView finalTagViewContainer, int position) {
        switch (mSelectMode) {
            case -1://多选
                multipleChoice(child, position);
                if (mOnTagClickListener != null) {
                    mOnTagClickListener.onTagClick(finalTagViewContainer, position,
                            TagFlowLayout.this);
                }
                if (mOnSelectListener != null) {
                    mOnSelectListener.onSelected(new HashSet<Integer>(mSelectedView));
                }
                break;
            case 0://单选
                singleChoice(child, finalTagViewContainer, position);
                break;
        }

    }

    /**
     * 多选操作
     *
     * @param child
     * @param position
     */
    private void multipleChoice(TagView child, int position) {
        if (!child.isChecked()) {
            //处理max_select=1的情况
            if (mSelectedMax == 1 && mSelectedView.size() == 1) {
                Iterator<Integer> iterator = mSelectedView.iterator();
                Integer preIndex = iterator.next();
                TagView pre = (TagView) getChildAt(preIndex);
                setChildUnChecked(preIndex, pre);
                setChildChecked(position, child);
                mSelectedView.remove(preIndex);
                mSelectedView.add(position);
            } else {
                if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax) {
                    return;
                }
                setChildChecked(position, child);
                mSelectedView.add(position);
            }
        } else {
            setChildUnChecked(position, child);
            mSelectedView.remove(position);
        }
        return;
    }

    /**
     * 单选
     *
     * @param child
     * @param finalTagViewContainer
     * @param position
     */
    private void singleChoice(TagView child, TagView finalTagViewContainer, int position) {
        if (!child.isChecked()) {
            //处理max_select=1的情况
            if (mSelectedMax == 1 && mSelectedView.size() == 1) {
                Iterator<Integer> iterator = mSelectedView.iterator();
                Integer preIndex = iterator.next();
                TagView pre = (TagView) getChildAt(preIndex);
                //取消选中&&置为可点击————>>上一次点击
                setChildUnChecked(preIndex, pre);
                setChildEnable(preIndex, pre);
                //选中&&置为不可点击————>>这次点击
                setChildChecked(position, child);
                setChildUnEnable(position, child);
                //选中集合中 移除上次点击 添加这次点击
                mSelectedView.remove(preIndex);
                mSelectedView.add(position);
                //点击同一个tab则不回调
                if (mOnTagClickListener != null && preIndex != position) {
                    mOnTagClickListener.onTagClick(finalTagViewContainer, position,
                            TagFlowLayout.this);
                }
                if (mOnSelectListener != null && preIndex != position) {
                    mOnSelectListener.onSelected(new HashSet<>(mSelectedView));
                }
            } else {
                if (mSelectedMax > 0 && mSelectedView.size() >= mSelectedMax) {
                    return;
                }
                /*setChildChecked(position, child);
                mSelectedView.add(position);*/
            }
        } else {
            //只能选中不能取消选中
            /*setChildUnChecked(position, child);
            mSelectedView.remove(position);*/
        }
        return;
    }

    public TagAdapter getAdapter() {
        return mTagAdapter;
    }


    private static final String KEY_CHOOSE_POS = "key_choose_pos";
    private static final String KEY_DEFAULT = "key_default";


    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEFAULT, super.onSaveInstanceState());

        String selectPos = "";
        if (mSelectedView.size() > 0) {
            for (int key : mSelectedView) {
                selectPos += key + "|";
            }
            selectPos = selectPos.substring(0, selectPos.length() - 1);
        }
        bundle.putString(KEY_CHOOSE_POS, selectPos);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            String mSelectPos = bundle.getString(KEY_CHOOSE_POS);
            if (!TextUtils.isEmpty(mSelectPos)) {
                String[] split = mSelectPos.split("\\|");
                for (String pos : split) {
                    int index = Integer.parseInt(pos);
                    mSelectedView.add(index);

                    TagView tagView = (TagView) getChildAt(index);
                    if (tagView != null) {
                        setChildChecked(index, tagView);
                    }
                }

            }
            super.onRestoreInstanceState(bundle.getParcelable(KEY_DEFAULT));
            return;
        }
        super.onRestoreInstanceState(state);
    }


    @Override
    public void onChanged() {
        mSelectedView.clear();
        changeAdapter();
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
