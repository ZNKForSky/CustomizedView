package customizedView.demo.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import customizedView.demo.com.R;
import customizedView.demo.com.beans.Ads;

import static customizedView.demo.com.utils.Constants.TAG;

public class AdsPagerAdapter extends PagerAdapter {
    private ArrayList<Ads> adsArrayList;
    private Context mContext;

    public AdsPagerAdapter(ArrayList<Ads> adsArrayList, Context mContext) {
        this.adsArrayList = adsArrayList;
        this.mContext = mContext;
    }

    /**
     * @return 返回所有view的总数
     */
    @Override
    public int getCount() {
        //当前的轮播图滑到第一页就不能往右滑了，滑到最后一页也不能往左滑，为了实现循环效果（其实是伪循环），
        //这里指定view总数为Integer的最大值
        return Integer.MAX_VALUE;
    }

    /**
     * @param view   当前滑动的view
     * @param object 将要进入的新创建的view，由instantiateItem方法创建
     * @return true:表示当前滑动的view就是要进入viewpager的view，所以不再去创建，使用缓存；false:去重新创建
     */
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    /**
     * 此方法用来将数据设置给view，类似于BaseAdapger的getView方法
     * 注：由于viewpager最多只加载3个界面，不需要viewHolder
     *
     * @param container viewpager的引用
     * @param position  当前滑动的view的位置
     * @return 所要加载的view
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Log.e(TAG, "instantiateItem: position == " + position);
        View view = View.inflate(mContext, R.layout.item_viewpager_ads, null);
        ImageView imgAds = view.findViewById(R.id.img_ads);
        imgAds.setImageResource(adsArrayList.get(position % (adsArrayList.size())).getAdsImgId());
        container.addView(view);//把渲染好的图片加载到viewpager中
        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //查看源码即可发现，destroyItem这个方法必须重写，因为它默认调用的destroyItem方法直接抛异常了，所以这里把它注掉
        //super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
