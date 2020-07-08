package com.example.studynote.Blog.websocket;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.studynote.R;

import org.jetbrains.annotations.NotNull;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * 模拟websocket服务器
 */
public class MockServerActivity extends Activity {
    private final String TAG = "jimu";
    private WebSocket mWebSocket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                initMockServer();
            }
        }).start();

    }


    /**
     * 初始化虚拟服务器
     */
    private void initMockServer() {

        MockWebServer mMockWebServer = new MockWebServer();
        MockResponse response = new MockResponse()
                .withWebSocketUpgrade(new WebSocketListener() {
                    @Override
                    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
                        super.onOpen(webSocket, response);
                        //有客户端连接时回调
                        Log.e(TAG, "服务器收到客户端连接成功回调：");
                        mWebSocket = webSocket;
                        mWebSocket.send("我是服务器，你好呀");
                    }

                    @Override
                    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
                        super.onMessage(webSocket, text);

                        Log.e(TAG, "服务器收到消息：" + text);
                    }

                    @Override
                    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
                        super.onClosed(webSocket, code, reason);
                        Log.e(TAG, "onClosed：");
                    }
                });

        mMockWebServer.enqueue(response);

        //获取连接url，初始化websocket客户端
        String websocketUrl = "ws://" + mMockWebServer.getHostName() + ":" + mMockWebServer.getPort() + "/";
        WSManager.getInstance().init(websocketUrl);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebSocket != null) {
            mWebSocket.close(1001, "");
        }

    }
}
