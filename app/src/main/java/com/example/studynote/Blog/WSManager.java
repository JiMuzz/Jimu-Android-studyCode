package com.example.studynote.Blog;

import android.os.Handler;
import android.util.Log;

import com.example.studynote.MainApplication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * WSManager
 * WebSocket管理类
 */
public class WSManager {

    private final String TAG = this.getClass().getSimpleName();
    private final int DATE_NORMAL = 0;

    private static Handler sDelivery;
    private static ArrayList<WeakReference<WebSocketDataListener>> sWeakRefListeners;
    private static WSManager sInstance;

    private WebSocket mWebSocket;
    private OkHttpClient mClient;

    //连接的websocket地址
    private String mWbSocketUrl;

    public static WSManager getInstance() {
        if (sInstance == null) {
            synchronized (WSManager.class) {
                if (sInstance == null) {
                    sInstance = new WSManager();
                    sWeakRefListeners = new ArrayList<>();
                    sDelivery = new Handler(MainApplication.getInstance().getMainLooper());
                }
            }
        }

        return sInstance;
    }


    /**
     * 初始化WebSocket
     */
    public void init() {
        mWbSocketUrl = "";
        mClient = new OkHttpClient.Builder()
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .connectTimeout(3, TimeUnit.SECONDS)
                .pingInterval(3, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(mWbSocketUrl)
                .build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
    }


    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            Log.e(TAG,"收到消息！");
            onWSDataChanged(DATE_NORMAL, text);
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            Log.e(TAG,"连接成功！");

            //每隔五秒发送心跳包
            final String message ="{\"msg\":\"ping\"}";
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    send(message);
                }
            },5000);

        }
    }


    /**
     * 遍历监听者，发送消息
     * @param type
     * @param info
     */
    public static void onWSDataChanged(final int type, final String info) {
        Iterator<WeakReference<WebSocketDataListener>> iterator = sWeakRefListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<WebSocketDataListener> ref = iterator.next();
            if (ref == null) {
                break;
            }
            final WebSocketDataListener listener = ref.get();
            if (listener == null) {
                iterator.remove();
            } else {
                // To fresh UI
                sDelivery.post(new Runnable() {
                    @Override
                    public void run() {
                        if (listener != null) {
                            listener.onWebSocketData(type, info);
                        }
                    }
                });
            }
        }
    }

    /**
     * 发送消息
     * @param message
     */
    public void send(final String message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    /**
     * 主动断开连接
     * @param code
     * @param reason
     */
    public void disconnect(int code, String reason) {
        if (mWebSocket != null)
            mWebSocket.close(code, reason);
    }


    /**
     * 注册监听者
     * @param listener
     */
    public void registerWSDataListener(WebSocketDataListener listener) {
        if (!sWeakRefListeners.contains(listener)){
            sWeakRefListeners.add(new WeakReference<>(listener));
        }
    }

    /**
     * 解绑监听
     * @param listener
     */
    public void unregisterWSDataListener(WebSocketDataListener listener) {
        Iterator<WeakReference<WebSocketDataListener>> iterator = sWeakRefListeners.iterator();
        while (iterator.hasNext()) {
            WeakReference<WebSocketDataListener> ref = iterator.next();
            if (ref == null) {
                break;
            }
            if (ref.get() == null) {
                iterator.remove();
            }
            if (ref.get() == listener) {
                iterator.remove();
                break;
            }
        }
    }


    public interface WebSocketDataListener {
        void onWebSocketData(int type, String data);
    }
}
