package com.terry.eplatform;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.terry.eplatform.bean.Menu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabs;
    private ViewPager mVpContents;
    private ProgressBar mPbLoading;
    private String[] mLabels;
    private Fragment[] mFragments;

    private LoadMenuTask mTask = new LoadMenuTask();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    // 整体URL
    private static final String URL = "http://122.200.77.206:8899";
    // 查询参数
    private static final String QUERY = "/ishop/ePlatform/mobile/queryAllMerchants.do";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // TODO 获取所有菜单项
        mTask.execute();
    }

    private void initView() {
        mTabs = (TabLayout) findViewById(R.id.id_tl_tabs);
        mVpContents = (ViewPager) findViewById(R.id.id_vp_content);
        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);
    }

    /**
     * 执行获取JSON数据并进行处理
     */
    class LoadMenuTask extends AsyncTask<Void, Void, Boolean> {

        // 使用OKHttp封装请求
        // 获取JSON
        @Override
        protected Boolean doInBackground(Void... strings) {
            HttpUrl httpUrl = HttpUrl.parse(URL + QUERY).newBuilder().build();
            Request request = new Request.Builder().url(httpUrl).build();
            // 建立连接,获取响应
            try {
                Response response = client.newCall(request).execute();
                JSONObject jsonObject = new JSONObject(response.body().string());
//                Log.v("LOG", response.body().toString());
                JSONArray jsonArray = jsonObject.optJSONArray("data");
                // 获取所有的菜单栏
                List<Menu> menuList = gson.fromJson(jsonArray.toString()
                        , new TypeToken<List<Menu>>() {
                        }.getType());
                Log.v("LOG", menuList.size() + "");

                return true;
            } catch (Exception e) {
                e.printStackTrace();
//                Log.v("LOG", "false !!!");

                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                mPbLoading.setVisibility(View.GONE);
                return;
            }
            super.onPostExecute(aBoolean);
        }
    }
}
