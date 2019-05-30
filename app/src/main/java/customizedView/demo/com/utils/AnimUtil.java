package customizedView.demo.com.utils;

import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class AnimUtil {
    private static final String TAG = "MenuActivity";
    private static float fromDegrees;
    private static float toDegrees;
    public static int AnimationCount;//记录正在执行的动画的数量
    /**
     * @param flag 如果是true，则展示菜单
     * @param rl   执行动画的布局
     */
    /**
     * @param flag        如果是true，则展示菜单
     * @param rl          执行动画的布局
     * @param startOffset 该值用于指定延迟多长时间开启动画
     */
    public static void showOrHideMenu(boolean flag, RelativeLayout rl, long startOffset) {
        if (flag) {
            fromDegrees = -180f;
            toDegrees = -360f;
        } else {
            fromDegrees = 0f;
            toDegrees = -180f;
        }
        //由于补间动画只是改变了View的显示效果，并不会真正的改变View的属性，通俗地讲，我们可以认为控件还在原来的地方，只是看不到了而已，
        // 但是可以响应点击事件。那么既然我们看不到控件了，就必须在布局隐藏后，使其上面的控件不能再被点击
        for (int i = 0; i < rl.getChildCount(); i++) {
            rl.getChildAt(i).setEnabled(flag);
        }
        /**
         * @param rl          布局
         * @param fromDegrees 该值表示从哪个角度开始
         * @param toDegrees   该值表示到哪个角度结束
         * @param pivotXType  该值X轴基于哪个控件
         * @param pivotXValue 该值X轴基于指定控件的值
         * @param pivotYType  该值Y轴基于哪个控件
         * @param pivotYValue 该值Y轴基于指定控件的值
         * 后面四个参数是用于确定基于哪个点旋转
         */
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, RotateAnimation.RELATIVE_TO_SELF,
                0.5f, RotateAnimation.RELATIVE_TO_SELF, 1f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);//true表示动画结束后保持当时的状态，如果不设置，默认false
        rotateAnimation.setStartOffset(startOffset);
        /*Q201905260159 begin*/
        rotateAnimation.setAnimationListener(new MyAnimationListener());
        /*Q201905260159 end*/
        rl.startAnimation(rotateAnimation);
    }

    /*Q201905260159 begin*/
    static class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {//动画开启时执行
            AnimationCount++;
            Log.e(TAG, "onAnimationEnd: AnimationCount znk ++   =="+AnimationCount);
        }

        @Override
        public void onAnimationEnd(Animation animation) {//动画结束时执行
            AnimationCount--;
            Log.e(TAG, "onAnimationEnd: AnimationCount znk --   =="+AnimationCount);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {//动画重复时执行

        }
    }
    /*Q201905260159 end*/
}
