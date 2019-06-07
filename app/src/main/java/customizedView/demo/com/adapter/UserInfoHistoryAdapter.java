package customizedView.demo.com.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import customizedView.demo.com.R;
import customizedView.demo.com.beans.UserInfo;
import customizedView.demo.com.utils.Constants;

public class UserInfoHistoryAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<UserInfo> mUserInfoList;

    public UserInfoHistoryAdapter(Context mContext, ArrayList<UserInfo> mUserInfoList) {
        this.mContext = mContext;
        this.mUserInfoList = mUserInfoList;
    }

    @Override
    public int getCount() {
        return mUserInfoList == null ? 0 : mUserInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserHistoryInfoHolder userHistoryInfoHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_account_info, null);
            userHistoryInfoHolder = new UserHistoryInfoHolder();
            userHistoryInfoHolder.mIvUserPhoto = convertView.findViewById(R.id.iv_user_photo);
            userHistoryInfoHolder.mTvUserName = convertView.findViewById(R.id.tv_user_name);
            userHistoryInfoHolder.mIbDelete = convertView.findViewById(R.id.ib_delete_history);


        } else {
            userHistoryInfoHolder = (UserHistoryInfoHolder) convertView.getTag();
        }

        userHistoryInfoHolder.mIvUserPhoto.setImageResource(mUserInfoList.get(position).getUserPhoto());
        userHistoryInfoHolder.mTvUserName.setText(mUserInfoList.get(position).getUserName());
        convertView.setTag(userHistoryInfoHolder);

        userHistoryInfoHolder.mIbDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInfoList.remove(position);
                Log.e(Constants.TAG, "onClick: mUserInfoList" + mUserInfoList.size());
                Log.e(Constants.TAG, "onClick: "+Thread.currentThread());//Thread[main,5,main] 说明getView方法是在主线程，故可以刷新UI
                notifyDataSetChanged();
            }

        });
        return convertView;
    }

    private class UserHistoryInfoHolder {
        public TextView mTvUserName;
        public ImageView mIvUserPhoto;
        public ImageButton mIbDelete;
    }

}
