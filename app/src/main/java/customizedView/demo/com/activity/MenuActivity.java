package customizedView.demo.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import customizedView.demo.com.R;
import customizedView.demo.com.utils.AnimUtil;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MenuActivity";

    private RelativeLayout rl_level1, rl_level2, rl_level3;
    private ImageView mainMenu, secondaryMenu;
    private boolean mainMenuState = true;//主菜单的显示状态，默认显示
    private boolean secondaryMenuState = true;//二级菜单的显示状态，默认显示
    private boolean allMenuState = true;//所有菜单的显示状态，只要有一个菜单显示，值就是true，默认显示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
        initListener();

    }

    private void initViews() {
        rl_level1 = findViewById(R.id.rl_menu_level1);
        rl_level2 = findViewById(R.id.rl_menu_level2);
        rl_level3 = findViewById(R.id.rl_menu_level3);

        mainMenu = findViewById(R.id.icon_menu_main);
        secondaryMenu = findViewById(R.id.icon_menu_secondary);
    }

    private void initListener() {
        mainMenu.setOnClickListener(this);
        secondaryMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!executeAnimIfNeed()) {//不需要执行动画，直接return
            return;
        }
        switch (v.getId()) {
            case R.id.icon_menu_main:
                long startOffset = 0;
                //如果菜单是展示状态，就隐藏它
                if (secondaryMenuState) {
                    showOrHideSecondaryMenu(false, startOffset);
                    startOffset += 200;
                }
                Log.e(TAG, "onClick: " + startOffset);
                showOrHideMainMenu(!mainMenuState, startOffset);
                break;
            case R.id.icon_menu_secondary:
                showOrHideSecondaryMenu(!secondaryMenuState, 0);
                break;
            default:
                break;
        }
        Log.e(TAG, "onClick: secondaryMenuState == " + secondaryMenuState);
    }

    /**
     * 展示主菜单
     */
    private void showOrHideMainMenu(boolean isShow, long startOffset) {
        AnimUtil.showOrHideMenu(isShow, rl_level2, startOffset);
        mainMenuState = isShow;
    }

    /**
     * 展示二级菜单
     */
    private void showOrHideSecondaryMenu(boolean isShow, long startOffset) {
        AnimUtil.showOrHideMenu(isShow, rl_level3, startOffset);
        secondaryMenuState = isShow;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!executeAnimIfNeed()) {//不需要执行动画，直接return
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {//点击Menu键走这里的逻辑
            Log.e(TAG, "onKeyDown:AnimUtil.AnimationCount == " + AnimUtil.AnimationCount);
            int startOffset = 0;
            if (allMenuState) {
                if (secondaryMenuState) {
                    showOrHideSecondaryMenu(false, startOffset);
                    startOffset += 200;
                }
                if (mainMenuState) {
                    showOrHideMainMenu(false, startOffset);
                    startOffset += 200;
                }
                AnimUtil.showOrHideMenu(false, rl_level1, startOffset);
            } else {
                AnimUtil.showOrHideMenu(true, rl_level1, startOffset);
                showOrHideMainMenu(true, 200);
                showOrHideSecondaryMenu(true, 400);
            }
            allMenuState = !allMenuState;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*Q201905260159 begin*/
    private boolean executeAnimIfNeed() {
        Log.e(TAG, "onClick:AnimationCount before ====== " + AnimUtil.AnimationCount);
        if (AnimUtil.AnimationCount != 0) {//说明有动画在执行,即不能再执行动画了
            Log.e(TAG, "onClick:AnimationCount after====== " + AnimUtil.AnimationCount);
            return false;
        }
        return true;
    }
    /*Q201905260159 end*/

}
