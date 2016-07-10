package com.terry.eplatform;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.terry.eplatform.bean.MenuItem;
import com.terry.eplatform.bean.Merchants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    // UI
    private TabLayout mTabs;
    private ViewPager mVpContents;
    private ProgressBar mPbLoading;

    // Task
    private LoadMenuTask mTask = new LoadMenuTask();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();
    private List<MenuItem> mMenus;
    private List<ArrayList<Merchants>> mAllSubItems = new ArrayList<>();

    // Adapter
    private String[] mLabels;
    private Fragment[] mFragments;

    // 整体URL
    public static final String URL = "http://122.200.77.206:8899";
    // 查询参数
    public static final String QUERY = "/ishop/ePlatform/mobile/queryAllMerchants.do";

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
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
                // 设置编码为UTF-8
                JsonObject jsonObject = new JsonParser()
                        .parse(new String(response.body().bytes(), "utf-8")).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("data");
                mMenus = gson.fromJson(jsonArray.toString(), new TypeToken<List<MenuItem>>() {
                }.getType());
                // 获取所有子JSON对象
                for (JsonElement ele : jsonArray) {
                    JsonObject object = ele.getAsJsonObject();
                    JsonArray arr = object.getAsJsonArray("subdata");
                    Iterator it = arr.iterator();
                    ArrayList<Merchants> merchantses = new ArrayList<>();
                    while (it.hasNext()) {
                        // 获取子列表
                        Merchants m = gson.fromJson(it.next().toString(), Merchants.class);
                        merchantses.add(m);
                    }
                    mAllSubItems.add(merchantses);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();

                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean) {
                // 取消进度条，显示UI
                mPbLoading.setVisibility(View.GONE);
                mLabels = new String[mMenus.size()];
                for (int i = 0; i < mLabels.length; i++) {
                    mLabels[i] = mMenus.get(i).getSub();
                }
                // 初始化Fragment
                mFragments = new Fragment[mMenus.size()];
                for (int i = 0; i < mFragments.length; i++) {
                    mFragments[i] = ItemFragment.newInstance(mMenus.get(i).getSub(), mAllSubItems.get(i));
                }
                // 设置Adapter
                mVpContents.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return mFragments[position];
                    }

                    @Override
                    public int getCount() {
                        return mFragments.length;
                    }

                    @Override
                    public CharSequence getPageTitle(int position) {
                        return mMenus.get(position).getSub();
                    }
                });
                mTabs.setupWithViewPager(mVpContents);
            }
        }
    }
}
