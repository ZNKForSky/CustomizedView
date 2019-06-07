package customizedView.demo.com.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import customizedView.demo.com.R;
import customizedView.demo.com.adapter.UserInfoHistoryAdapter;
import customizedView.demo.com.beans.UserInfo;
import customizedView.demo.com.utils.Constants;
import customizedView.demo.com.utils.UIUtils;

public class SpinnerActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdtAccount;//账号信息输入框
    private ImageView mIvArrow;//下拉箭头
    private ListView mLvHistory;//用户登录信息历史记录
    private ArrayList<UserInfo> userInfosList = new ArrayList<UserInfo>();
    private PopupWindow popupWindow;
    private int popupWindowHeight;
    private WindowManager systemService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        initViews();

    }

    private void initViews() {
        mEdtAccount = findViewById(R.id.edt_account);
        mIvArrow = findViewById(R.id.iv_down_arrow);
        mLvHistory = new ListView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListeners();
        initData();
    }

    private void initListeners() {
        mIvArrow.setOnClickListener(this);
    }

    private void initData() {//造点假数据
        if (userInfosList != null) {
            userInfosList.add(new UserInfo("小军", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小欢", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小博", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小托", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小涛", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小科", R.mipmap.ic_user));
            userInfosList.add(new UserInfo("小鑫", R.mipmap.ic_user));
            mLvHistory.setAdapter(new UserInfoHistoryAdapter(this, userInfosList));
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_down_arrow://点击下拉箭头
                if (popupWindow == null) {
                    Log.e(Constants.TAG, "onClick: getWindowHeight == " + UIUtils.getWindowHeightOrWidth(this, Constants.WINDOW_HEIGHT));
                    Log.e(Constants.TAG, "onClick: getWindowWidth == " + UIUtils.getWindowHeightOrWidth(this, Constants.WINDOW_WIDTH));
                    Log.e(Constants.TAG, "onClick: getRelHeight == " + UIUtils.getRelHeight(this));
                    popupWindowHeight = UIUtils.getWindowHeightOrWidth(this, Constants.WINDOW_HEIGHT) / 3;
                    popupWindow = new PopupWindow(mLvHistory, mEdtAccount.getWidth(), popupWindowHeight);
                    popupWindow.showAsDropDown(mEdtAccount);
                    systemService = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                }
                break;
        }
    }
}
