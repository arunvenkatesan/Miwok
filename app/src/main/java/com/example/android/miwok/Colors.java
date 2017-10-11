package com.example.android.miwok;

/**
 * Created by swetha on 7/26/17.
 */

public class Colors {

    private String defaultColor;
    private String miwokColor;
    private int mResourceId = IMAGENOTFOUND;
    private int mAudioId;

    private static final int IMAGENOTFOUND = -1;

    public Colors(String engColor, String mcolor){
        defaultColor = engColor;
        miwokColor = mcolor;
    }

    public Colors(String engColor, String mcolor,int resourceId,int audioId){
        defaultColor = engColor;
        miwokColor = mcolor;
        mResourceId= resourceId;
        mAudioId=audioId;
    }

    public String getDefaultColor() {

        return defaultColor;
    }

    public String getMiwokColor() {

        return miwokColor;
    }

    public int getmResourceId() {

        return mResourceId;
    }

    public int getmAudioId() {

        return mAudioId;
    }

    public boolean hasImage() {

        return mResourceId !=IMAGENOTFOUND;
    }
}
