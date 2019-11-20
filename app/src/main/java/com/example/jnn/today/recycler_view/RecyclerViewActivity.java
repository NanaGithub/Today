package com.example.jnn.today.recycler_view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jnn.today.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private List<PersonBean> mDatas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(this, getData());
        recyclerView.setAdapter(adapter);
    }


    public void clickDiffRefresh(View view) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new RecyclerDiff(mDatas, getNewData()), true);
        adapter.setDatas(mDatas);
        //相当于adapter的刷新，放在最后位置
        diffResult.dispatchUpdatesTo(adapter);
    }

    public void clickLowRefresh(View view) {
        getNewData();
        adapter.setDatas(mDatas);
        //以前我们大多数情况下只能这样
        adapter.notifyDataSetChanged();
    }

    public void clickClearData(View view) {
        getData();
        adapter.setDatas(mDatas);
        adapter.notifyDataSetChanged();
    }

    private List<PersonBean> getNewData() {
        List<PersonBean> newDatas = new ArrayList<>();
        for (PersonBean bean : mDatas) {
            newDatas.add(bean.clone());
            //clone一遍旧数据 ，模拟刷新操作
        }
        //模拟新增数据
        newDatas.add(new PersonBean("新增小美女", "18"));
        //模拟修改数据
        newDatas.get(0).setName("修改小美女");
        //模拟数据位移
        PersonBean testBean = newDatas.get(1);
        testBean.setName("移动小美女");
        newDatas.remove(testBean);
        newDatas.add(testBean);

        //别忘了将新数据给Adapter
        mDatas = newDatas;
        return newDatas;
    }

    private List<PersonBean> getData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            mDatas.add(new PersonBean("小美女", "" + i));
        }
        return mDatas;
    }

}
