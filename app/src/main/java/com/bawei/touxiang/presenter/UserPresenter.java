package com.bawei.touxiang.presenter;

import com.bawei.touxiang.bean.TouBean;
import com.bawei.touxiang.contract.UserContract;
import com.bawei.touxiang.model.UserModel;

import java.lang.ref.WeakReference;

/**
 * FileName: UserPresenter
 * Author: 徐姗姗
 * Date: 2019/11/16 12:25
 */
public class UserPresenter implements UserContract.IPersenter {

    private WeakReference<UserContract.IView> iViewWeakReference;
    private UserModel userModel;

    @Override
    public void attachView(UserContract.IView iView) {
        iViewWeakReference = new WeakReference<>(iView);
        userModel = new UserModel();
    }

    public UserContract.IView getView() {
        return iViewWeakReference.get();
    }
    @Override
    public void detachView() {
        if (iViewWeakReference != null) {
            iViewWeakReference.clear();
            iViewWeakReference = null;
        }
    }

    @Override
    public void getPersenter(String userId, String sessionId) {
        userModel.getUserChuan(userId, sessionId, new UserContract.IModel.IUserCallBack() {
            @Override
            public void onChuanSuccess(TouBean data) {
                getView().onChuanSuccess(data);
            }

            @Override
            public void onChuanRailure(String railure) {
                getView().onChuanRailure(railure);
            }
        });
    }
}
