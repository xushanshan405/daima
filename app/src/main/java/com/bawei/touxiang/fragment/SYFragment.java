package com.bawei.touxiang.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.touxiang.R;
import com.bawei.touxiang.base.BaseFragment;


public class SYFragment extends BaseFragment {


    private View inflate;

    @Override
    protected View layoutId(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_sy, container, false);
        return inflate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
