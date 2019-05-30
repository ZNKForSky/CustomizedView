package customizedView.demo.com.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import customizedView.demo.com.R;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private static final String TAG = "znk";
    private ListView mListView;
    private String[] views = {"播放器菜单", "轮播图"};
    private ArrayAdapter<String> arrayAdapter;
    private TextView tv;
    private final int FLAG_JUMP_TO_MENU = 0;
    private final int FLAG_JUMP_TO_SLIDESHOW = 1;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
        initListener();
    }

    private void initViews() {
        mListView = findViewById(R.id.lv_main);
    }

    private void initData() {
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, views);
        Log.e("znk", "initData:mListView " + mListView);
        if (mListView != null) {
            mListView.setAdapter(arrayAdapter);
        }
    }


    private void initListener() {
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        tv = view.findViewById(android.R.id.text1);//说明这里的view是当前点击的子条目
//        tv.setText("我被点击了 ");
        if (intent == null) {
            intent = new Intent();
        }
        switch (position) {
            case FLAG_JUMP_TO_MENU:
                intent.setClass(this, MenuActivity.class);
                break;
            case FLAG_JUMP_TO_SLIDESHOW:
                intent.setClass(this, AdsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
