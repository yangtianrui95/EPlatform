package com.terry.eplatform;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
        return mMerchantses.size();
    }

    @Override
    public Object getItem(int i) {
        return mMerchantses.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_merchant, null, false);
            holder = new ViewHolder();
            holder.mIvImg = (ImageView) convertView.findViewById(R.id.id_iv_item_img);
            holder.mTvMarketPrice = (TextView) convertView.findViewById(R.id.id_tv_market_price);
            holder.mTvMerName = (TextView) convertView.findViewById(R.id.id_tv_mer_name);
            holder.mTvPrice = (TextView) convertView.findViewById(R.id.id_tv_price);

            // 添加删除线
            holder.mTvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTvMerName.setText(mMerchantses.get(position).getMer_name());
        holder.mTvPrice.setText("现价: " + mMerchantses.get(position).getPrice() + "元");
        holder.mTvMarketPrice.setText("市场价: " + mMerchantses.get(position).getMarket_price() + "元");
        // 使用Glide框架异步加载图片
        Glide.with(mContext).load(MainActivity.URL + "/" + mMerchantses.get(position).getImgpath())
                .fitCenter().into(holder.mIvImg);
        return convertView;
    }

    static class ViewHolder {
        private TextView mTvMerName;
        private TextView mTvPrice;
        private TextView mTvMarketPrice;
        private ImageView mIvImg;
    }
}
