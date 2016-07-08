package com.terry.eplatform;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabs;
    private ViewPager mVpContents;
    private String[] mLabels;
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        // TODO 获取所有菜单项
        
    }

    private void initView() {
        mTabs = (TabLayout) findViewById(R.id.id_tl_tabs);
        mVpContents = (ViewPager) findViewById(R.id.id_vp_content);
    }
}
