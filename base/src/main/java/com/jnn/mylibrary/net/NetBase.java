package com.jnn.mylibrary.net;

import android.arch.lifecycle.BuildConfig;
import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit+okHttp网络框架封装类
 * <p> 使用说明
 * 1，在Application中初始化
 * NetBase.netBase.init(getApplicationContext (), BuildConfig.HTTP_END_POINT);
 */
public class NetBase {
    private volatile static OkHttpClient okHttpClient;
    private volatile Retrofit restfulClient;
    private String url;
    private Context context;

    public static NetBase netBase = new NetBase();

    private OkHttpClient.Builder builder;

    private NetBase() {

    }

    public NetBase addInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            builder.addInterceptor(interceptor);
        }
        return this;
    }

    public NetBase build() {
        okHttpClient = builder.build();
        initRestFullClient();
        return this;
    }

    /**
     * 初始化.
     */
    public void init(Context context, String url) {
        this.context = context;
        this.url = url;
        initOkHttpClient();
        build();
    }

    /**
     * 创建对应的APi  Service.
     */
    public synchronized <T> T createApi(Class<T> tClass) {
        return restfulClient.create(tClass);
    }

    /**
     * 初始化RetrofitApi.
     */
    private void initRestFullClient() {
        if (okHttpClient == null) {
            return;
        }
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        restfulClient = builder.build();
    }

    /**
     * 初始化Okhttp.
     */
    private void initOkHttpClient() {
        File cacheFile = new File(context.getCacheDir() + "/http", "catch-file");
        builder = new OkHttpClient()
                .newBuilder()
                .cache(new Cache(cacheFile, 200000))
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        /*try {
            if (BuildConfig.ENABLE_HTTPS) {
                builder = useHttps(builder);
            }
        } catch (CertificateException | NoSuchProviderException | NoSuchAlgorithmException | IOException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }*/
    }

 /*   *//**
     * 为客户端增加Https证书验证
     *//*
    private OkHttpClient.Builder useHttps(OkHttpClient.Builder builder)
            throws CertificateException,
            NoSuchProviderException,
            KeyStoreException,
            NoSuchAlgorithmException,
            IOException, KeyManagementException {

        final TrustManager[] trustAllCerts = new TrustManager[]{
                new TrustManagerImpl()
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        builder.sslSocketFactory(sslContext.getSocketFactory());

        builder.hostnameVerifier((hostname, session) -> true);
        return builder;
    }*/
}
