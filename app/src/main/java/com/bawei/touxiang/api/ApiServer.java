package com.bawei.touxiang.api;

import com.bawei.touxiang.bean.TouBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * FileName: ApiServer
 * Author: 徐姗姗
 * Date: 2019/11/16 11:57
 */
public interface ApiServer {



    @POST("community/verify/v1/releasePost")
    @Multipart
    Observable<TouBean> tou(@Header("userId") String userId, @Header("sessionId") String sessionId);


}
