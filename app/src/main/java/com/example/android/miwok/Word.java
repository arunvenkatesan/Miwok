package com.example.android.miwok;

import android.content.Context;

public class Word {

    private String mEnglishTranslation;
    private String mMiwokTranslation;
    private int mresourceId = IMAGENOTFOUND;
    private int mAudioId;

    private static final int IMAGENOTFOUND = -1;

    public Word(String englishTranslation, String miwokTranslation){
        mEnglishTranslation= englishTranslation;
       mMiwokTranslation = miwokTranslation;
    }

    public Word(String englishTranslation, String miwokTranslation,int resourceId,int audioId){
        mEnglishTranslation= englishTranslation;
        mMiwokTranslation = miwokTranslation;
        mresourceId = resourceId;
        mAudioId = audioId;
    }
    public String getEnglishTranslation() {

        return mEnglishTranslation;
    }

    public String getMiwokTranslation() {

        return mMiwokTranslation;
    }

    public int getResourceId() {

        return mresourceId;
    }

    public int getmAudioId() {

        return mAudioId;
    }

    public boolean hasImage() {
        return mresourceId != IMAGENOTFOUND;
    }
}
