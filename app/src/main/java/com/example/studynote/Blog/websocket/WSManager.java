package com.example.studynote.Blog.websocket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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

    private final String TAG = "jimu";
    private final int DATE_NORMAL = 0;

    private static Handler sDelivery;
    private static ArrayList<WeakReference<WebSocketDataListener>> sWeakRefListeners;
    private static WSManager sInstance;

    private WebSocket mWebSocket;
    private OkHttpClient mClient;

    //连接的websocket地址
    private String mWbSocketUrl;

    private boolean isReceivePong;

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
    public void init(String url) {
        mWbSocketUrl = url;
        //测试url
        mWbSocketUrl = "ws://echo.websocket.org";
        Log.e(TAG, "mWbSocketUrl=" + mWbSocketUrl);
        mClient = new OkHttpClient.Builder()
                .pingInterval(10, TimeUnit.SECONDS)
                .build();
        connect();
    }

    public void connect() {
        Request request = new Request.Builder()
                .url(mWbSocketUrl)
                .build();
        mWebSocket = mClient.newWebSocket(request, new WsListener());
    }


    class WsListener extends WebSocketListener {
        @Override
        public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosed(webSocket, code, reason);
            Log.e(TAG, "onClosed！");
            //断线重连
            if (code == 1001) {
                Log.e(TAG, "断线重连！");
                connect();
            }

        }

        @Override
        public void onClosing(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            Log.e(TAG, "onFailure！" + t.getMessage());
        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
            super.onMessage(webSocket, text);
            Log.e(TAG, "客户端收到消息:" + text);

            if (text.contains("pong")) {
                //简易写法，是否为pong包
                isReceivePong = true;
                return;
            }

            onWSDataChanged(DATE_NORMAL, text);


        }

        @Override
        public void onMessage(@NotNull WebSocket webSocket, @NotNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
            super.onOpen(webSocket, response);
            Log.e(TAG, "连接成功！");

            mWebSocket = webSocket;
            //测试发消息
            webSocket.send("我是客户端，你好啊");

            //主动发送心跳包
            isReceivePong = true;
            heartHandler.sendEmptyMessage(10);

        }

    }

    // 发送心跳包
    Handler heartHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what != 10) return false;

            final String message = "{\"action\":\"ping\"}";
            if (isReceivePong) {
                send(message);
                isReceivePong = false;
                heartHandler.sendEmptyMessageDelayed(10, 10000);
            } else {
                //没有收到pong命令，进行重连
                disconnect(1001, "断线重连");
            }
            return false;
        }
    });


    /**
     * 遍历监听者，发送消息
     *
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
     *
     * @param message
     */
    public void send(final String message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    /**
     * 发送消息
     *
     * @param message
     */
    public void send(final ByteString message) {
        if (mWebSocket != null) {
            mWebSocket.send(message);
        }
    }

    /**
     * 主动断开连接
     *
     * @param code
     * @param reason
     */
    public void disconnect(int code, String reason) {
        if (mWebSocket != null)
            mWebSocket.close(code, reason);
    }


    /**
     * 注册监听者
     *
     * @param listener
     */
    public void registerWSDataListener(WebSocketDataListener listener) {
        if (!sWeakRefListeners.contains(listener)) {
            sWeakRefListeners.add(new WeakReference<>(listener));
        }
    }

    /**
     * 解绑监听
     *
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
