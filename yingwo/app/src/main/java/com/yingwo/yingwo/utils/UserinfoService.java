package com.yingwo.yingwo.utils;

import com.yingwo.yingwo.model.AcademyListModel;
import com.yingwo.yingwo.model.LoginEntity;
import com.yingwo.yingwo.model.PostListEntity;
import com.yingwo.yingwo.model.RegisterEntity;
import com.yingwo.yingwo.model.SchoolListModel;
import com.yingwo.yingwo.model.TopicModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by FJS0420 on 2016/8/31.
 */

public interface UserinfoService {

  /*  //注册 请求短信验证码接口
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Sms/Send")
    Call<String> requestVerify(@Field("mobile") String mobile);

    //注册 请求短信验证码接口
    @GET("getRegisterSMS")
    Call<String> requestVerify1(@Query("mobile") String mobile);

    //注册 验证短信接口
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Sms/Check")
    Call<String> verifySms(@Query("mode") String mode,@Query("mobile") String mobile,@Query("code") String code);

    //注册接口    @Headers("X-Requested-With:XMLHttpRequest")

    @POST("User/Register")
    Call<String> register(@Query("password") String password,@Query("mobile") String mobile);

    //登录接口
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("User/Login")
    Call<String> login(@Field("mobile") String mobile,@Field("password") String password);

    //获取学校列表
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("school/school_list")
    Call<String> getSchools();

    //获取token
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Qiniu/UploadToken")
    rx.Observable<String> getToken();
*/

    public static String BASE_URL = "http://yw.zhibaizhi.com/yingwophp/api/v1/";


    //注册 请求短信验证码接口
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Sms/Send")
    Observable<RegisterEntity> smsSend(@Field("mobile") String mobile);

    //注册 请求短信验证码接口
    @GET("getRegisterSMS")
    Call<String> requestVerify1(@Query("mobile") String mobile);

    //注册 验证短信接口
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Sms/Check")
    Observable<RegisterEntity> verifySms(@Field("mode") String mode,@Field("mobile") String mobile,@Field("code") String code);

    //注册接口    @Headers("X-Requested-With:XMLHttpRequest")

    @Headers("X-Requested-With:XMLHttpRequest")
    @FormUrlEncoded
    @POST("User/Register")
    Observable<RegisterEntity> register(@Field("password") String password, @Field("mobile") String mobile);

    //登录接口
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("User/Login")
    Observable<LoginEntity> login(@Field("mobile") String mobile, @Field("password") String password);

    //获取学校列表
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("school/school_list_by_group")
    Observable<SchoolListModel> getSchools();

    //获取专业列表
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("school/academy_list_by_group")
    Observable<AcademyListModel> getAcademyList(@Field("school_id") String school_id);

    //修改用户个人信息
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("User/Update")
    Call<String> updateUserInfo(@Query("name")String name,@Query("password")String passwd,@Query("face_img")String img_url,@Query("grade")String grade,@Query("signature")String signature,@Query("school_id")String school_id,@Query("academy_id")String academy_id);

    //完善个人信息
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("User/Base_info")
    Observable<RegisterEntity> updateBaseInfo(@Field("name") String name,@Field("grade")String grade,@Field("signature")String signature,@Field("sex")int sex,@Field("face_img")String img_url,@Field("school_id")String school_id,@Field("academy_id")String academy_id);

    //获取帖子回复
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Post/reply_list")
    Observable<PostListEntity> getPostList(@Field("post_id") int post_id);

    //获取帖子回复
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Post/reply")
    Observable<String> build_reply(@Field("post_id") int post_id,@Field("img")String img,@Field("content")String content);

    //获取帖子列表
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Post/get_list")
    Observable<TopicModel> getTopicList(@Field("topic_id") int topic_id);

    //发布帖子
    @FormUrlEncoded
    @Headers("X-Requested-With:XMLHttpRequest")
    @POST("Post/add_new")
    Observable<String> releasePost(@Field("topic_id") int topic_id, @Field("content") String content,@Field("img") String imgurls);

}
