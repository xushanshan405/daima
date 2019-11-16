package com.bawei.touxiang.contract;

import com.bawei.touxiang.bean.TouBean;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * FileName: UserContract
 * Author: 徐姗姗
 * Date: 2019/11/16 12:17
 */
public interface UserContract {
    interface IView{
        void onChuanSuccess(Object object);

        void onChuanRailure(String e);
    }
    interface IModel{
        void getUserChuan(String userId, String sessionId, IUserCallBack iUserCallBack);
        interface IUserCallBack{
            void onChuanSuccess(TouBean data);

            void onChuanRailure(String railure);
        }

    }
    interface IPersenter{
        void attachView(UserContract.IView iView);

        void detachView();

        void getPersenter(String userId, String sessionId);
    }
}
