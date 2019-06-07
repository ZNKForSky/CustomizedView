package customizedView.demo.com.utils;

import android.app.Activity;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;

public class UIUtils {

    /**
     * 获取屏幕的宽/高(屏幕展示高度)
     * 注：
     * 如果手机没有底部虚拟导航栏则：此高度==屏幕完整高度
     * 如果手机有底部虚拟导航栏则：此高度==屏幕完整高度-导航栏高度
     *
     * @param activity 需要获取窗体宽或高的activity引用
     * @param flag     取值范围：Constants.WINDOW_HEIGHT和Constants.WINDOW_WIDTH，分别表示获取高度和宽度
     * @return 根据参数flag，返回窗体高度或宽度
     */
    public static int getWindowHeightOrWidth(Activity activity, int flag) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        //取得窗口属性
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口高度
        int screenHeight = dm.heightPixels;
        //窗口的宽度
        int screenWidth = dm.widthPixels;
        //可以通过DisplayMetrics类来获取当前窗口的一些信息,DisplayMetrics对象中，取得的宽高维度是以像素为单位(Pixel)，“像素”所指的是“绝对像素”而非“相对像素”,
        //绝对像素就是设备的物理像素.PS:手机分辨率就是设备像素
        Log.e(Constants.TAG, dm.toString());
        return flag == 0 ? screenHeight : screenWidth;
    }

    /**
     * 获取屏幕完整高度(屏幕展示高度+导航栏)[针对具有导航栏的手机]
     */
    public static int getRelHeight(Activity activity) {

        DisplayMetrics dm = new DisplayMetrics();
        int realScreenH = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {//好像是4.2之后才有的虚拟导航栏，目前市面上绝大多数手机都有导航栏
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(dm);
            realScreenH = dm.heightPixels;
            return realScreenH;
        }
        return getWindowHeightOrWidth(activity,Constants.WINDOW_HEIGHT);
    }

}
