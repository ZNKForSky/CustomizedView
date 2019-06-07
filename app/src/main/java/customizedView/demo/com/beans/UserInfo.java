package customizedView.demo.com.beans;

public class UserInfo {
    private String userName;//用户名
    private int userPhoto;//用户头像

    public UserInfo(String userName, int userPhoto) {
        this.userName = userName;
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(int userPhoto) {
        this.userPhoto = userPhoto;
    }
}
