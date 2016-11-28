package com.example.android.miwok;

/**
 * Created by CE on 2016-11-18.
 */

public class Word {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String miwokTranslation, defaultTranslation;
    private int resID;

    public Word(String defaultTranslation, String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.resID = NO_IMAGE_PROVIDED;
    }

    public Word(String defaultTranslation, String miwokTranslation, int resID) {
        this.miwokTranslation = miwokTranslation;
        this.defaultTranslation = defaultTranslation;
        this.resID = resID;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public int getResID() {
        return resID;
    }

    public boolean hasImage() {
        return resID != NO_IMAGE_PROVIDED;
    }
}
