package com.example.jnn.today.data;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jnn.today.R;
import com.google.android.flexbox.FlexboxLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 新特性UI和ButterKnife练习
 */
public class DataActivity extends AppCompatActivity {

    @BindView(R.id.flex_layout)
    FlexboxLayout flexLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        //ButterKnife.setDebug(true);
        ButterKnife.bind(this);
    }
}
