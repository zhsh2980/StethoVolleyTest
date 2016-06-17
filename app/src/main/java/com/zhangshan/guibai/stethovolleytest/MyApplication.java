package com.zhangshan.guibai.stethovolleytest;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

/**
 * Created by zhangshan on 16/6/17 上午11:16.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    public RequestQueue mRequestQueue;

    public static synchronized MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider
                                (this))
                        .build());
    }

    /**
     * @return The Volley Request queue
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
            if (mRequestQueue == null) {
                OkHttpClient client = new OkHttpClient();
                client.networkInterceptors().add(new StethoInterceptor());
                mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new
                 OkHttpStack(client));
            }
        return mRequestQueue;
    }


}
