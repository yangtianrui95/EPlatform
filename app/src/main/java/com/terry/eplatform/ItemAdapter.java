package com.terry.eplatform;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.terry.eplatform.bean.Merchants;

import java.util.List;

/**
 * Created by yangtianrui on 16-7-8.
 * 列表项的适配器
 */
public class ItemAdapter extends BaseAdapter {
    private List<Merchants> mMerchantses;
    private Context mContext;

    public ItemAdapter(Context context, List<Merchants> list) {
        mMerchantses = list;
        mContext = context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
