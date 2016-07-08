package com.terry.eplatform;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.terry.eplatform.bean.Merchants;

import java.util.ArrayList;

/**
 * Created by yangtianrui on 16-7-8.
 * <p/>
 * 每个菜单项对应的Fragment
 */
public class ItemFragment extends Fragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_LIST = "list";
    private String mTitle;
    private ArrayList<Merchants> mMerchants = new ArrayList<>();

    public ItemFragment() {
    }

    /**
     * 获取Fragment实例
     *
     * @param title Fragment的菜单项
     * @param list  Fragment的菜单列表
     */
    public static ItemFragment newInstance(String title, ArrayList<Merchants> list) {

        Bundle args = new Bundle();
        args.putCharSequence(KEY_TITLE, title);
        args.putParcelableArrayList(KEY_LIST, list);
        ItemFragment fragment = new ItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTitle = getArguments().getString(KEY_TITLE);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
