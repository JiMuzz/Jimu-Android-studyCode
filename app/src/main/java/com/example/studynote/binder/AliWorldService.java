package com.example.studynote.binder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class AliWorldService extends Service {

    private static final String TAG = "lz1";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IMsgManager.Stub() {
        @Override
        public String getMsg() throws RemoteException {
            String tellMsg="犬夜叉...是我";
            Log.e(TAG, "阿篱：" + tellMsg);
            return tellMsg;
        }

        @Override
        public void tell(String msg) throws RemoteException {
            Log.e(TAG, "我是阿篱，我收到了你说的:" + msg);
        }
    };
}
