package customizedView.demo.com.beans;

public class Ads {
    private String message;
    private int adsImgId;

    public Ads(String message, int adsImgId) {
        this.message = message;
        this.adsImgId = adsImgId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAdsImgId() {
        return adsImgId;
    }

    public void setAdsImgId(int adsImgId) {
        this.adsImgId = adsImgId;
    }
}
