package com.yingwo.yingwo.utils;

import com.yingwo.yingwo.MyApplication;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wangyu on 8/17/16.
 */

public class HttpControl {

    private volatile static HttpControl mInstance;
    private static Retrofit retrofit;
    private static OkHttpClient client;

    private HttpControl() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .cookieJar(new CookiesManager())
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl((UserinfoService.BASE_URL))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static HttpControl getInstance() {
        if (mInstance == null) {
            synchronized (HttpControl.class) {
                if (mInstance == null)
                    mInstance = new HttpControl();
            }
        }
        return mInstance;
    }

    public OkHttpClient getClient() {
        if (client == null) {
            client = HttpControl.getInstance().initClient();
        }
        return client;
    }

    private OkHttpClient initClient() {
        return client;
    }

    public Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = HttpControl.getInstance().initRetrofit();
        }
        return retrofit;
    }

    private Retrofit initRetrofit() {
        return retrofit;
    }


    /**
     * 自动管理Cookies
     */
    private class CookiesManager implements CookieJar {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore(MyApplication.getGlobalContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (cookies != null && cookies.size() > 0) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = cookieStore.get(url);
            return cookies;
        }
    }


}
