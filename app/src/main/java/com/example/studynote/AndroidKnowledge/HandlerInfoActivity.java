package com.example.studynote.AndroidKnowledge;


import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 * handler
 * <p>
 * 提到handler，大家都想到些什么呢，切换线程？延时操作实现定时器？
 * 今天我们来说一说handler
 */
public class HandlerInfoActivity extends Activity {


    /**
     * 说下用法，定义一个handler，重写handleMessage方法处理消息，
     * 用send方法通知handler
     * <p>
     * 但是主线程和其他线程用法却有点不一样！
     * <p>
     * <p>
     * 其实下面线程里面的用法才是完整的，
     * Looper.prepare()方法其实是创建looper，并且绑定到该线程
     * 然后定义handler
     * 最后记得开始loop()，该方法意思就是这个looper会不断从MessageQueue中获取message并且发送给对应的hander
     * <p>
     * looper相当于一个工具人，handler说要发送消息啦，工具人就把消息放到消息队列里面进行管理
     * 同时他会不停的loop，从消息队列里面拿出队头消息给到当初的那个对应的handler，
     * 然后handler通过handlemessage进行处理
     * <p>
     * <p>
     * 所以呢，handler内部其实就是三部分
     * 1、looper，关联线程并且负责从消息队列拿出消息分发给handler
     * 2、messageQueue，消息队列，负责消息存储管理
     * 3、handler，负责发送和处理消息
     * <p>
     * <p>
     * 原理就这么多，下面说几个重要的知识点
     */
    public class RealClient {
        public void main(String[] args) {

            Handler handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }
            };
            handler.sendEmptyMessage(0);
            handler.sendEmptyMessageDelayed(0, 1000);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    Handler handler = new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                        }
                    };
                    Looper.loop();
                }
            }).start();


        }
    }


    /**
     * 知识点一：主线程的looper呢？？？
     */
    public void mainThreadHander() {

        /**
         * 看看主线程里面的方法：android.app.ActivityThread
         *
         * 顺便说下，ActivityThread的main方法是整个app的最开始执行的方法，是app的入口
         *
         *
         * Looper.prepareMainLooper();
         * ActivityThread thread = new ActivityThread();
         * //在attach方法中会完成Application对象的初始化，然后调用Application的onCreate()方法
         * thread.attach(false);
         * if (sMainThreadHandler == null) {
         *     sMainThreadHandler = thread.getHandler();
         *   }
         *  //...
         *  Looper.loop();
         *  throw new RuntimeException("Main thread loop unexpectedly exited");
         */

        Looper.prepareMainLooper();
        Looper.loop();

        /**
         * 最重要就是这两句，调用了prepareMainLooper创建了主线程的looper，然后调用loop方法开始死循环
         *
         * 为啥是死循环呢？看看代码呢
         *
         *
         //Looper
         public static void loop() {
         //...
         for (;;) {
         // 不断从 MessageQueue 获取 消息
         Message msg = queue.next();
         //退出 Looper
         if (msg == null) {
         // No message indicates that the message queue is quitting.
         return;
         }
         //...

         }
         }

         */


    }


    /**
     * 知识点2：为什么死循环都可以被写出来啊？不会oom吗？
     * 这也是腾讯的一年面试题
     */
    public void WhyNotOOM() {
        /**
         * 说白了，其实死循环也是有意为之
         * 线程在可执行代码执行完后，就会终止，而主线程肯定需要一直运行，所以死循环就能保证这一点
         * 死循环的话，其他的事就交给新线程来处理就不会卡住了
         *
         *
         * 而activity的生命周期是怎么实现在死循环体外正常执行的呢？
         * 答：其实就是通过这个handler的，比如onPause方法，当主线程Looper在loop的时候，收到暂停的消息时，
         * 就会把消息分发给主线程的handleMessage处理，然后最后会调用到activity的onPause方法
         *
         * 建议去知乎看一下这个答案，说的很详细：https://www.zhihu.com/question/34652589/answer/90344494
         *
         */


    }

    /**
     * 知识点3：内存泄漏！！！
     * <p>
     * 首先为什么会发送内存泄漏？
     * handler作为内部类会持有外部类的引用(为啥？转到InnerClassOutClass可知一二)，当发送延迟消息时，就有可能发生处理消息的时候，
     * activity已经销毁了，从而导致内存泄漏
     * <p>
     * 怎么解决？
     * 答：定义静态内部类,并且在ondestory里面移除所有消息
     * 直接移除不就行了？还需要静态内部类？
     * 答：ondestory不一定执行的嘛
     *
     * <p>
     * （其实所有的内部类内存泄漏都是一样的处理方法，
     * 内部类持有外部类引用相关内容可以看com.example.studynote.JavaKnowledge.InnerClassOutClass）
     */

    private static class MemoryLeakSafeHandler extends Handler {

        private WeakReference<HandlerInfoActivity> ref;

        public MemoryLeakSafeHandler(HandlerInfoActivity activity) {
            this.ref = new WeakReference(activity);
        }

        @Override
        public void handleMessage(final Message msg) {
            HandlerInfoActivity activity = ref.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }

    MemoryLeakSafeHandler handler;

    public void handleMessage(Message msg) {

    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
