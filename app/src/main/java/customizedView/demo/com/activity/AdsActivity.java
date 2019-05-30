package customizedView.demo.com.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import customizedView.demo.com.R;
import customizedView.demo.com.adapter.AdsPagerAdapter;
import customizedView.demo.com.beans.Ads;
import customizedView.demo.com.utils.Constants;

public class AdsActivity extends AppCompatActivity {
    private static final String TAG = "AdsActivity";
    private ViewPager mAdsViewpager;
    private ArrayList<Ads> adsArrayList = new ArrayList<Ads>();
    private TextView mTvMessage;
    private LinearLayout mDotsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ads);
        initViews();
        initListener();
        initData();
    }

    private void initViews() {
        mAdsViewpager = findViewById(R.id.vp_ads);
        mTvMessage = findViewById(R.id.tv_ads);
        mDotsLayout = findViewById(R.id.ll_dots);
    }

    private void initListener() {
        mAdsViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.e(Constants.TAG, "onPageScrolled: i ==" + i + ",v == " + v + ",i1==" + i1);

            }

            @Override
            public void onPageSelected(int i) {
                Log.e(Constants.TAG, "onPageSelected: i == " + i);
                updataMessageAndDot(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.e(Constants.TAG, "onPageScrollStateChanged: i == " + i);
            }
        });
    }

    private void initData() {

        Ads ads1 = new Ads("巩俐不低俗，我就不能低俗", R.mipmap.a);
        Ads ads2 = new Ads("朴树又回来了，再唱经典老歌引万人大合唱！", R.mipmap.b);
        Ads ads3 = new Ads("揭秘北京电影如何升级", R.mipmap.c);
        Ads ads4 = new Ads("乐视网TV版大派送", R.mipmap.d);
        Ads ads5 = new Ads("热血屌丝的反杀", R.mipmap.e);

        adsArrayList.add(ads1);
        adsArrayList.add(ads2);
        adsArrayList.add(ads3);
        adsArrayList.add(ads4);
        adsArrayList.add(ads5);

        mAdsViewpager.setAdapter(new AdsPagerAdapter(adsArrayList, this));
        initDots();
        updataMessageAndDot(0);//默认显示第一页数据
    }

    /**
     * 当viewpager滑动时，更新广告词和点的颜色
     */
    private void updataMessageAndDot(int currentPage) {
        //更新广告词
        //int currentItem = mAdsViewpager.getCurrentItem();
        // Log.e(Constants.TAG, "updataMessageAndDot: currentItem == " + currentItem);
        Log.e(Constants.TAG, "updataMessageAndDot: int currentPage == " + currentPage);
        mTvMessage.setText(adsArrayList.get(currentPage).getMessage());

        //更新dot
        for (int i = 0; i < mDotsLayout.getChildCount(); i++) {
            //为每个点设置选中状态，只有当前展示的page和子view的索引一致时，才设置选中状态为true
            mDotsLayout.getChildAt(i).setSelected(i == currentPage);
        }

    }

    //初始化dot
    private void initDots() {
        for (int i = 0; i < adsArrayList.size(); i++) {
            View view = new View(this);
            //因为我们在布局文件里面指定的是LinearLayout，所以这里要导入LinearLayout.LayoutParams
            LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(12, 12);
            //除第一个点外，其他点设置左边距为5dp
            dotParams.leftMargin = 8;

            view.setLayoutParams(dotParams);
            view.setBackground(getDrawable(R.drawable.selector_dot));
            mDotsLayout.addView(view);//相当于在LinearLayout里面添加view
        }


    }
}
