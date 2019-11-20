package com.example.jnn.today.recycler_view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jnn.today.R;

import java.util.List;

/**
 * @author jnn
 * @date 2019/11/12
 * @description 最原始adapter
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private Context context;
    private List<PersonBean> data;
    private final LayoutInflater layoutInflater;

    public MyAdapter(Context context, List<PersonBean> mDatas) {
        this.context = context;
        this.data = mDatas;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setDatas(List<PersonBean> data) {
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(layoutInflater.inflate(R.layout.item_recycle_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.textView.setText(data.get(position).name + data.get(position).age);
    }
    @Override
    public void onBindViewHolder(MyHolder holder, int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_data);
        }
    }
}
