package com.bawei.touxiang.model;

import com.bawei.touxiang.bean.TouBean;
import com.bawei.touxiang.contract.UserContract;
import com.bawei.touxiang.utils.RetrofitUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.SchedulerWhen;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * FileName: UserModel
 * Author: 徐姗姗
 * Date: 2019/11/16 12:25
 */
public class UserModel implements UserContract.IModel {

    private Observable<TouBean> observable;

    @Override
    public void getUserChuan(String userId, String sessionId, final IUserCallBack iUserCallBack) {
        observable = RetrofitUtil.getApiServer().tou(userId, sessionId);
        Observer<TouBean> observer = new Observer<TouBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(TouBean touBean) {
                iUserCallBack.onChuanSuccess(touBean);
            }

            @Override
            public void onError(Throwable e) {
                iUserCallBack.onChuanRailure(e.toString());
            }

            @Override
            public void onComplete() {

            }
        };observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
