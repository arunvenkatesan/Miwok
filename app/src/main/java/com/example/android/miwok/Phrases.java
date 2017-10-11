package com.example.android.miwok;

public class Phrases {

    private String mDefaultPhrase;
    private String mMiwokPhrase;
    private int mAudioId;
    private int mimageFind = NOIMAGE_FOUND;

    private static final int NOIMAGE_FOUND =-1;

    public Phrases(String defaultPhrase, String miwokPhrase,int audioId){
        mDefaultPhrase = defaultPhrase;
        mMiwokPhrase = miwokPhrase;
        mAudioId = audioId;
    }

    public Phrases(String defaultPhrase, String miwokPhrase,int audioId, int imageFind){
        mDefaultPhrase = defaultPhrase;
        mMiwokPhrase = miwokPhrase;
        mAudioId = audioId;
        mimageFind = imageFind;

    }

    public String getmDefaultPhrase() {

        return mDefaultPhrase;
    }

    public String getmMiwokPhrase() {

        return mMiwokPhrase;
    }

    public int getmAudioId() {
        return mAudioId;
    }

    public int getMimageFind() {

        return mimageFind;
    }

    public boolean hasImage() {
       if( mimageFind != NOIMAGE_FOUND)
           return true;
        else
            return false;
   }
}
