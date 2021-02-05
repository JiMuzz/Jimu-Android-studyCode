package com.example.studynote.binder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.studynote.R;

public class QycWorldActivity extends Activity {
    private static final String TAG = "lz1";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qyc);

        Intent i = new Intent(this, AliWorldService.class);
        bindService(i, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IMsgManager msgManager = IMsgManager.Stub.asInterface(service);
            try {
                String tellMsg="阿篱，是你吗";
                Log.e(TAG, "犬夜叉：" + tellMsg);
                msgManager.tell(tellMsg);


                String msg = msgManager.getMsg();
                Log.e(TAG, "我是犬夜叉，我收到了你说的：" + msg);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
