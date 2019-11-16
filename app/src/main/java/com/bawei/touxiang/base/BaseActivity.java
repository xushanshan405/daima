package com.bawei.touxiang.base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bawei.touxiang.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        initView();
        initData();
    }

    protected abstract int layoutId();

    protected abstract void initView();

    protected abstract void initData();
}
