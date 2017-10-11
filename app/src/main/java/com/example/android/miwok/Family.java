package com.example.android.miwok;


public class Family {

    private String mDefaultFamilyName;
    private String mMiwokFamilyName;
    private  int mResourceId = IMAGENOTFOUND;
    private int mAudioId;

    private static final int IMAGENOTFOUND =-1;

    public Family(String defaultFamilyName, String miwokFamilyName){
        mDefaultFamilyName  = defaultFamilyName;
        mMiwokFamilyName  = miwokFamilyName;
    }

    public Family(String defaultFamilyName, String miwokFamilyName, int resourceId,int audioId){
        mDefaultFamilyName  = defaultFamilyName;
        mMiwokFamilyName  = miwokFamilyName;
        mResourceId = resourceId;
        mAudioId = audioId;
    }

    public String getmDefaultFamilyName() {

        return mDefaultFamilyName;
    }

    public String getmMiwokFamilyName() {

        return mMiwokFamilyName;
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
