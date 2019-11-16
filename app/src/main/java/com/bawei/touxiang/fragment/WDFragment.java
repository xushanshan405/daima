package com.bawei.touxiang.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.touxiang.R;
import com.bawei.touxiang.base.BaseFragment;
import com.bawei.touxiang.contract.UserContract;
import com.bawei.touxiang.presenter.UserPresenter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class WDFragment extends BaseFragment implements UserContract.IView {

    private Button buttonquxiao;
    private Button buttonwan;
    private Button buttonpai;
    private Button buttonxianji;
    private EditText editText;
    private ImageView img;
    private RecyclerView recyclerView;
    private RelativeLayout dialogLayout;
    private View inflate;
    private UserPresenter userPresenter;

    @Override
    protected View layoutId(LayoutInflater inflater, ViewGroup container) {
        inflate = inflater.inflate(R.layout.fragment_wd, container, false);
        return inflate;
    }

    @Override
    protected void initView() {
        dialogLayout = inflate.findViewById(R.id.dialog_layout);
        buttonquxiao = inflate.findViewById(R.id.buttoncancel);
        buttonwan = inflate.findViewById(R.id.button_post_data);
        buttonpai = inflate.findViewById(R.id.button_paise);
        buttonxianji = inflate.findViewById(R.id.button_xianji);
        editText = inflate.findViewById(R.id.edit_shuru);
        recyclerView = inflate.findViewById(R.id.tian_recyclerView);
        img =inflate.findViewById(R.id.img_add);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用相机或相册
                dialogLayout.setVisibility(View.VISIBLE);
            }
        });
        buttonpai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.setVisibility(View.GONE);
                PictureSelector.create(getActivity())
                        .openCamera(PictureMimeType.ofImage())//打开相机
                        .compress(true)

                        .forResult(PictureConfig.CHOOSE_REQUEST);
                // 是否压缩 true or false
            }
        });
        buttonxianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.setVisibility(View.GONE);
                PictureSelector.create(PostActivity.this)
                        .openGallery(PictureMimeType.ofImage())//打开相册
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
        buttonquxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogLayout.setVisibility(View.GONE);
            }
        });
        buttonwan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //多表单文件对象列表，通过集合维护

                //遍历已经选择的图片数据，并且转换成文件对象，保存到文件对象列表中
                for (LocalMedia localMedia : selectList) {
                    //把文件对象包装成请求体对象
                    // RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                    // MultipartBody.Part  和后端约定好Key，这里的partName是用file
                    //最终转换成需要的多表单对象
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);


                }
                if (editText.getText().toString().length()==0){
                    Toast.makeText(getActivity(), "输入内容不能为空", Toast.LENGTH_SHORT).show();;
                    return;
                }
                //文本
                RequestBody content = RequestBody.create(MediaType.parse("multipart/form-data"),editText.getText().toString());
                userPresenter.getPersenter("842","1573799759428842");
            }
        });
    }

    @Override
    protected void initData() {
        userPresenter = new UserPresenter();
        userPresenter.attachView(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_Ok) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    if (selectList!=null&&selectList.size()>0){
                        selectList.clear();
                    }
                    // 图片、视频、音频选择结果回调,框架的回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    Log.d(TAG, "onActivityResult: "+selectList.size());
                    recyclerView.setAdapter(new PostAdapter(PostActivity.this,selectList));
                    break;
            }
        }
    }

    @Override
    public void onChuanSuccess(Object object) {

    }

    @Override
    public void onChuanRailure(String e) {

    }
}
