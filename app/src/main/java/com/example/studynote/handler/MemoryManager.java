package com.example.studynote.handler;

import android.content.Context;

public class MemoryManager {

    private static MemoryManager mManager;
    private Context mContext;

    private MemoryManager(Context mContext) {
        this.mContext=mContext;
    }

    public static MemoryManager getInstance(Context context) {
        if (mManager == null) {
            synchronized (MemoryManager.class) {
                mManager = new MemoryManager(context);
            }
        }
        return mManager;
    }
}
