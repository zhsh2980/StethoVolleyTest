package com.zhangshan.guibai.stethovolleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.stetho.common.LogUtil;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btn_line_to_internet;
    RequestQueue queue;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        btn_line_to_internet = (Button)findViewById(R.id.btn_line_to_internet);

        queue = MyApplication.getInstance().getRequestQueue();
        url = "https://publicobject.com/helloworld.txt";
        initButton();
    }

    private void initData() {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LogUtil.d(s);
                tv.setText(s);
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.e(volleyError.toString());
            }
        });

        queue.add(request);

        //SharedPrfUtil.setInt("uid",669);
        //SharedPrfUtil.setString("username","dongye");

    }

    private void initButton() {
        btn_line_to_internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("去加载网络了");
                tv.setText(" ");
                initData();
            }
        });
    }
}
